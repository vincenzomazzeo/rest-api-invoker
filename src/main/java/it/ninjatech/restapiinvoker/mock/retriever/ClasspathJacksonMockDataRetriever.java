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
package it.ninjatech.restapiinvoker.mock.retriever;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.ninjatech.restapiinvoker.mock.data.AbstractMockData;
import it.ninjatech.restapiinvoker.mock.data.JacksonMockData;

/**
 * <p>
 * Implementation of {@link JacksonMockDataRetriever} that retrieves the data
 * from a JSON file present in the class path.
 * </p>
 *
 * @param <T> Type of the body
 * 
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public class ClasspathJacksonMockDataRetriever<T> extends JacksonMockDataRetriever<T> {

	/** Class path resource. */
	private final ClassPathResource classPathResource;

	/** Type of the body. */
	private final Class<T> type;

	/**
	 * Constructs a new ClasspathJacksonMockDataRetriever.
	 *
	 * @param objectMapper Object mapper
	 * @param resource Resource path
	 * @param type Type of the body
	 */
	public ClasspathJacksonMockDataRetriever(ObjectMapper objectMapper, String resource, Class<T> type) {
		super(objectMapper);

		this.classPathResource = new ClassPathResource(resource);
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.ninjatech.restapiinvoker.mock.retriever.MockDataRetriever#retrieve()
	 */
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
