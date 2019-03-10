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

public class MockHttpRestClient implements HttpRestClient {

	private final Map<MockMatcher, MockDataRetriever<?>> mockMap;

	public MockHttpRestClient() {
		this.mockMap = new LinkedHashMap<>();
	}

	public MockHttpRestClient addMock(MockMatcher mockMatcher, MockDataRetriever<?> mockDataRetriever) {
		this.mockMap.put(mockMatcher, mockDataRetriever);

		return this;
	}

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
