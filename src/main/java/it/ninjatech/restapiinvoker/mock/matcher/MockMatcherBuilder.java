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
package it.ninjatech.restapiinvoker.mock.matcher;

import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

/**
 * <p>
 * Builder for {@link MockMatcher}.
 * </p>
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public class MockMatcherBuilder {

	/**
	 * Makes a new builder.
	 *
	 * @return Mock matcher builder
	 */
	public static MockMatcherBuilder builder() {
		return new MockMatcherBuilder();
	}

	/** Mock matcher. */
	private final DefaultMockMatcher mockMatcher;

	/**
	 * Constructs a new MockMatcherBuilder.
	 */
	private MockMatcherBuilder() {
		this.mockMatcher = new DefaultMockMatcher();
	}

	/**
	 * Builds the {@link MockMatcher}.
	 *
	 * @return Mock matcher
	 */
	public MockMatcher build() {
		return this.mockMatcher;
	}

	/**
	 * Returns the {@link StringMatcherBuilder} for base path.
	 *
	 * @return {@link StringMatcherBuilder}
	 */
	public StringMatcherBuilder basePath() {
		return new StringMatcherBuilder(this, m -> this.mockMatcher.setBasePathMatcher(m));
	}

	/**
	 * Returns the {@link StringMatcherBuilder} for path.
	 *
	 * @return {@link StringMatcherBuilder}
	 */
	public StringMatcherBuilder path() {
		return new StringMatcherBuilder(this, m -> this.mockMatcher.setPathMatcher(m));
	}

	/**
	 * Returns the {@link HttpMethodMatcherBuilder} for HTTP method.
	 *
	 * @return {@link HttpMethodMatcherBuilder}
	 */
	public HttpMethodMatcherBuilder httpMethod() {
		return new HttpMethodMatcherBuilder(this, m -> this.mockMatcher.setHttpMethodMatcher(m));
	}

	/**
	 * Returns the {@link MultiValueMapMatcherBuilder} for HTTP headers.
	 *
	 * @return {@link MultiValueMapMatcherBuilder}
	 */
	public MultiValueMapMatcherBuilder<HttpHeaders> httpHeaders() {
		return new MultiValueMapMatcherBuilder<>(this, m -> this.mockMatcher.setHttpHeadersMatcher(m));
	}

	/**
	 * Returns the {@link MapMatcherBuilder} for path params.
	 *
	 * @return {@link MapMatcherBuilder}
	 */
	public MapMatcherBuilder pathParams() {
		return new MapMatcherBuilder(this, m -> this.mockMatcher.setPathParamsMatcher(m));
	}

	/**
	 * Returns the {@link MultiValueMapMatcherBuilder} for query params.
	 *
	 * @return {@link MultiValueMapMatcherBuilder}
	 */
	public MultiValueMapMatcherBuilder<MultiValueMap<String, String>> queryParams() {
		return new MultiValueMapMatcherBuilder<>(this, m -> this.mockMatcher.setQueryParamsMatcher(m));
	}

	/**
	 * Returns the {@link BodyMatcherBuilder} for body.
	 *
	 * @return {@link BodyMatcherBuilder}
	 */
	public BodyMatcherBuilder body() {
		return new BodyMatcherBuilder(this, m -> this.mockMatcher.setBodyMatcher(m));
	}

	/**
	 * Returns the {@link ResponseTypeMatcherBuilder} for response type.
	 *
	 * @return {@link ResponseTypeMatcherBuilder}
	 */
	public ResponseTypeMatcherBuilder responseType() {
		return new ResponseTypeMatcherBuilder(this, m -> this.mockMatcher.setResponseTypeMatcher(m));
	}

}
