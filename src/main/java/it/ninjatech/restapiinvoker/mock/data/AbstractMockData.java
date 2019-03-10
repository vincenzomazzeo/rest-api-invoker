package it.ninjatech.restapiinvoker.mock.data;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

public abstract class AbstractMockData<T> {

	public abstract HttpStatus getHttpStatus();

	public abstract HttpHeaders getHttpHeaders();

	public abstract T getBody();

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
