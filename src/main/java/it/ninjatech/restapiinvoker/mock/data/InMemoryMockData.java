package it.ninjatech.restapiinvoker.mock.data;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public final class InMemoryMockData<T> extends AbstractMockData<T> {

	private final HttpStatus httpStatus;
	private final HttpHeaders httpHeaders;
	private final T body;

	public InMemoryMockData(T body) {
		this(null, null, body);
	}

	public InMemoryMockData(HttpStatus httpStatus, HttpHeaders httpHeaders, T body) {
		this.httpStatus = httpStatus;
		this.httpHeaders = httpHeaders;
		this.body = body;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	@Override
	public HttpHeaders getHttpHeaders() {
		return this.httpHeaders;
	}

	@Override
	public T getBody() {
		return this.body;
	}

}
