package it.ninjatech.restapiinvoker.mock.retriever;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.ninjatech.restapiinvoker.mock.data.AbstractMockData;
import it.ninjatech.restapiinvoker.mock.data.JacksonMockData;

public class ClasspathJacksonMockDataRetriever<T> extends JacksonMockDataRetriever<T> {

	private final ClassPathResource classPathResource;
	private final Class<T> type;

	public ClasspathJacksonMockDataRetriever(ObjectMapper objectMapper, String resource, Class<T> type) {
		super(objectMapper);

		this.classPathResource = new ClassPathResource(resource);
		this.type = type;
	}

	@Override
	public AbstractMockData<T> retrieve() {
		try {
			return this.objectMapper.readValue(this.classPathResource.getInputStream(),
			                                   this.objectMapper.getTypeFactory().constructParametricType(JacksonMockData.class, this.type));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
