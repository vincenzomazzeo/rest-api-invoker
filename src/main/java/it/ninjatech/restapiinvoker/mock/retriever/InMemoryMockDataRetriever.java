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
package it.ninjatech.restapiinvoker.mock.retriever;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import it.ninjatech.restapiinvoker.mock.data.AbstractMockData;
import it.ninjatech.restapiinvoker.mock.data.InMemoryMockData;

/**
 * <p>
 * Implementation of the {@link MockDataRetriever} that holds the mock data in
 * memory.
 * </p>
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @param <T> Type of the body
 * @since 1.0.0
 */
public class InMemoryMockDataRetriever<T> implements MockDataRetriever<T> {

	/** HTTP headers. */
	private final HttpHeaders httpHeaders;

	/** HTTP status. */
	private HttpStatus httpStatus;

	/** Body. */
	private T body;

	/**
	 * Constructs a new InMemoryMockDataRetriever.
	 */
	public InMemoryMockDataRetriever() {
		this.httpHeaders = new HttpHeaders();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.ninjatech.restapiinvoker.mock.retriever.MockDataRetriever#retrieve()
	 */
	@Override
	public AbstractMockData<T> retrieve() {
		return new InMemoryMockData<>(this.httpStatus,
		                              this.httpHeaders.isEmpty() ? null : this.httpHeaders,
		                              this.body);
	}

	/**
	 * Sets the HTTP status.
	 *
	 * @param httpStatus HTTP status
	 * @return This instance
	 */
	public InMemoryMockDataRetriever<T> httpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;

		return this;
	}

	/**
	 * Adds the HTTP headers.
	 *
	 * @param httpHeaders HTTP headers
	 * @return This instance
	 */
	public InMemoryMockDataRetriever<T> httpHeaders(HttpHeaders httpHeaders) {
		this.httpHeaders.addAll(httpHeaders);

		return this;
	}

	/**
	 * Adds the HTTP header.
	 *
	 * @param headerName Header name
	 * @param headerValue Header value
	 * @return This instance
	 */
	public InMemoryMockDataRetriever<T> httpHeader(String headerName, String headerValue) {
		this.httpHeaders.add(headerName, headerValue);

		return this;
	}

	/**
	 * Adds the HTTP header.
	 *
	 * @param headerName Header name
	 * @param headerValues Header values
	 * @return This instance
	 */
	public InMemoryMockDataRetriever<T> httpHeader(String headerName, String... headerValues) {
		this.httpHeaders.addAll(headerName, Arrays.asList(headerValues));

		return this;
	}

	/**
	 * Sets the body.
	 *
	 * @param body Body
	 * @return This instance
	 */
	public InMemoryMockDataRetriever<T> body(T body) {
		this.body = body;

		return this;
	}

}
