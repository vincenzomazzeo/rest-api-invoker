package it.ninjatech.restapiinvoker.mock.matcher;

import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

public class MockMatcherBuilder {

	public static MockMatcherBuilder builder() {
		return new MockMatcherBuilder();
	}

	private final DefaultMockMatcher mockMatcher;

	private MockMatcherBuilder() {
		this.mockMatcher = new DefaultMockMatcher();
	}

	public MockMatcher build() {
		return this.mockMatcher;
	}

	public StringMatcherBuilder basePath() {
		return new StringMatcherBuilder(this, m -> this.mockMatcher.setBasePathMatcher(m));
	}

	public StringMatcherBuilder path() {
		return new StringMatcherBuilder(this, m -> this.mockMatcher.setPathMatcher(m));
	}

	public HttpMethodMatcherBuilder httpMethod() {
		return new HttpMethodMatcherBuilder(this, m -> this.mockMatcher.setHttpMethodMatcher(m));
	}

	public MultiValueMapMatcherBuilder<HttpHeaders> httpHeaders() {
		return new MultiValueMapMatcherBuilder<>(this, m -> this.mockMatcher.setHttpHeadersMatcher(m));
	}

	public MapMatcherBuilder pathParams() {
		return new MapMatcherBuilder(this, m -> this.mockMatcher.setPathParamsMatcher(m));
	}

	public MultiValueMapMatcherBuilder<MultiValueMap<String, String>> queryParams() {
		return new MultiValueMapMatcherBuilder<>(this, m -> this.mockMatcher.setQueryParamsMatcher(m));
	}

	public BodyMatcherBuilder body() {
		return new BodyMatcherBuilder(this, m -> this.mockMatcher.setBodyMatcher(m));
	}

	public ResponseTypeMatcherBuilder responseType() {
		return new ResponseTypeMatcherBuilder(this, m -> this.mockMatcher.setResponseTypeMatcher(m));
	}

}
