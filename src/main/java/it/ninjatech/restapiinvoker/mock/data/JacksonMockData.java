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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>
 * Implementation of {@link AbstractMockData} for JSON data.
 * </p>
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @param <T> Type of the body.
 * @since 1.0.0
 */
public class JacksonMockData<T> extends AbstractMockData<T> {

	/** HTTP status. */
	@JsonProperty
	private HttpStatus httpStatus;

	/** HTTP headers. */
	@JsonProperty
	private HttpHeaders httpHeaders;

	/** Body. */
	@JsonProperty
	private T body;

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
