package it.ninjatech.restapiinvoker.mock.retriever;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JacksonMockDataRetriever<T> implements MockDataRetriever<T> {

	protected final ObjectMapper objectMapper;

	public JacksonMockDataRetriever(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

}
