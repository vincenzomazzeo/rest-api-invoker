package it.ninjatech.restapiinvoker.mock.data;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JacksonMockData<T> extends AbstractMockData<T> {

	@JsonProperty
	private HttpStatus httpStatus;
	@JsonProperty
	private HttpHeaders httpHeaders;
	@JsonProperty
	private T body;

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
