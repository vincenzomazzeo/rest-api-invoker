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
package it.ninjatech.restapiinvoker.mock.data;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * Implementation of {@link AbstractMockData} for in memory data.
 * </p>
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @param <T> Type of the body.
 * @since 1.0.0
 */
public final class InMemoryMockData<T> extends AbstractMockData<T> {

	/** HTTP status. */
	private final HttpStatus httpStatus;

	/** HTTP headers. */
	private final HttpHeaders httpHeaders;

	/** Body. */
	private final T body;

	/**
	 * Constructs a new InMemoryMockData.
	 *
	 * @param body Body
	 */
	public InMemoryMockData(T body) {
		this(null, null, body);
	}

	/**
	 * Constructs a new InMemoryMockData.
	 *
	 * @param httpStatus HTTP status
	 * @param httpHeaders HTTP headers
	 * @param body Body
	 */
	public InMemoryMockData(HttpStatus httpStatus, HttpHeaders httpHeaders, T body) {
		this.httpStatus = httpStatus;
		this.httpHeaders = httpHeaders;
		this.body = body;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.ninjatech.restapiinvoker.mock.data.AbstractMockData#getHttpStatus()
	 */
	@Override
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.ninjatech.restapiinvoker.mock.data.AbstractMockData#getHttpHeaders()
	 */
	@Override
	public HttpHeaders getHttpHeaders() {
		return this.httpHeaders;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.ninjatech.restapiinvoker.mock.data.AbstractMockData#getBody()
	 */
	@Override
	public T getBody() {
		return this.body;
	}

}
