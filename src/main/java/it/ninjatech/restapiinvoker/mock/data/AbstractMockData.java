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
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

/**
 * <p>
 * Abstract class of all mock data objects.
 * </p>
 *
 * @param <T> Type of the body.
 * 
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public abstract class AbstractMockData<T> {

	/**
	 * Returns the HTTP status.
	 *
	 * @return HTTP status
	 */
	public abstract HttpStatus getHttpStatus();

	/**
	 * Returns the HTTP headers.
	 *
	 * @return HTTP headers
	 */
	public abstract HttpHeaders getHttpHeaders();

	/**
	 * Returns the body.
	 *
	 * @return Body
	 */
	public abstract T getBody();

	/**
	 * Returns the response entity.
	 *
	 * @return Response entity
	 */
	public final ResponseEntity<T> getResponseEntity() {
		ResponseEntity<T> result = null;

		HttpStatus httpStatus = getHttpStatus();
		BodyBuilder bodyBuilder = ResponseEntity.status(httpStatus == null ? HttpStatus.OK : httpStatus);
		HttpHeaders httpHeaders = getHttpHeaders();
		if (httpHeaders != null) {
			bodyBuilder.headers(httpHeaders);
		}
		T body = getBody();
		result = body == null ? bodyBuilder.build() : bodyBuilder.body(body);

		return result;
	}

}
