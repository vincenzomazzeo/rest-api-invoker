package it.ninjatech.restapiinvoker.mock.retriever;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import it.ninjatech.restapiinvoker.mock.data.AbstractMockData;
import it.ninjatech.restapiinvoker.mock.data.InMemoryMockData;

public class InMemoryMockDataRetriever<T> implements MockDataRetriever<T> {

	private final HttpHeaders httpHeaders;
	private HttpStatus httpStatus;
	private T body;

	public InMemoryMockDataRetriever() {
		this.httpHeaders = new HttpHeaders();
	}

	@Override
	public AbstractMockData<T> retrieve() {
		return new InMemoryMockData<>(this.httpStatus,
		                              this.httpHeaders.isEmpty() ? null : this.httpHeaders,
		                              this.body);
	}

	public InMemoryMockDataRetriever<T> httpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;

		return this;
	}

	public InMemoryMockDataRetriever<T> httpHeaders(HttpHeaders httpHeaders) {
		this.httpHeaders.addAll(httpHeaders);

		return this;
	}

	public InMemoryMockDataRetriever<T> httpHeader(String headerName, String headerValue) {
		this.httpHeaders.add(headerName, headerValue);

		return this;
	}

	public InMemoryMockDataRetriever<T> httpHeader(String headerName, String... headerValues) {
		this.httpHeaders.addAll(headerName, Arrays.asList(headerValues));

		return this;
	}

	public InMemoryMockDataRetriever<T> body(T body) {
		this.body = body;

		return this;
	}

}
