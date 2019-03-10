package it.ninjatech.restapiinvoker.client;

import java.lang.reflect.Type;

import org.springframework.core.ParameterizedTypeReference;

public class ResponseTypeReference<T> extends ParameterizedTypeReference<T> {

	private final Type type;

	protected ResponseTypeReference(Type type) {
		this.type = type;
	}

	@Override
	public Type getType() {
		return this.type;
	}

}
