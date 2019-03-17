/*
MIT License

Copyright (c) 2019 Vincenzo Mazzeo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package it.ninjatech.restapiinvoker.client;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import it.ninjatech.restapiinvoker.invocationdata.InvocationData;
import it.ninjatech.restapiinvoker.mock.data.AbstractMockData;
import it.ninjatech.restapiinvoker.mock.matcher.MockMatcher;
import it.ninjatech.restapiinvoker.mock.retriever.MockDataRetriever;

/**
 * <p>
 * Implementation of {@link HttpRestClient} that mocks the calls.<br>
 * The client holds a map of all the registered mocks. Every registered mock is
 * composed of a {@link MockMatcher} and a {@link MockDataRetriever}.
 * For each call, the {@link InvocationData} passed is used against all the
 * registered {@link MockMatcher} to find the {@link MockDataRetriever} to use.
 * If more than one {@link MockMatcher} match with the passed
 * {@link InvocationData}, the oldest registered {@link MockDataRetriever} is
 * selected.
 * The found {@link MockDataRetriever} is then used to retrieve the real mock
 * which is returned to the caller.
 * </p>
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public class MockHttpRestClient implements HttpRestClient {

	/**
	 * Map containing the mocks.
	 */
	private final Map<MockMatcher, MockDataRetriever<?>> mockMap;

	/**
	 * Constructs a new {@link MockHttpRestClient}.
	 */
	public MockHttpRestClient() {
		this.mockMap = new LinkedHashMap<>();
	}

	/**
	 * Adds a new mock to the client.
	 * 
	 * @param mockMatcher Matcher of the mock.
	 * @param mockDataRetriever Retriever for the mock
	 * @return This instance
	 */
	public MockHttpRestClient addMock(MockMatcher mockMatcher, MockDataRetriever<?> mockDataRetriever) {
		this.mockMap.put(mockMatcher, mockDataRetriever);

		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.ninjatech.restapiinvoker.client.HttpRestClient#exchange(it.ninjatech.
	 * restapiinvoker.invocationdata.InvocationData)
	 */
	@Override
	public Object exchange(InvocationData invocationData) {
		Object result = null;

		Optional<Entry<MockMatcher, MockDataRetriever<?>>> entry = this.mockMap.entrySet().stream().filter(e -> e.getKey().matches(invocationData)).findFirst();
		if (!entry.isPresent()) {
			throw new RuntimeException(String.format("No mock found for %s", invocationData.toString()));
		}
		MockDataRetriever<?> mockRetriever = entry.get().getValue();
		AbstractMockData<?> mockData = mockRetriever.retrieve();
		Type responseType = invocationData.getResponseType();
		if (responseType instanceof ParameterizedType
		    && ((ParameterizedType) responseType).getRawType().equals(ResponseEntity.class)) {
			result = mockData.getResponseEntity();
		}
		else {
			result = mockData.getBody();
		}

		return result;
	}

}
