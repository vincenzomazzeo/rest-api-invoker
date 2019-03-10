package it.ninjatech.restapiinvoker.mock.matcher;

import java.lang.reflect.Type;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

import it.ninjatech.restapiinvoker.invocationdata.InvocationData;

public class DefaultMockMatcher implements MockMatcher {

	private Matcher<String> basePathMatcher;
	private Matcher<String> pathMatcher;
	private Matcher<HttpMethod> httpMethodMatcher;
	private Matcher<HttpHeaders> httpHeadersMatcher;
	private Matcher<Map<String, String>> pathParamsMatcher;
	private Matcher<MultiValueMap<String, String>> queryParamsMatcher;
	private Matcher<Object> bodyMatcher;
	private Matcher<Type> responseTypeMatcher;

	public DefaultMockMatcher() {
		this.basePathMatcher = v -> true;
		this.pathMatcher = v -> true;
		this.httpMethodMatcher = v -> true;
		this.httpHeadersMatcher = v -> true;
		this.pathParamsMatcher = v -> true;
		this.queryParamsMatcher = v -> true;
		this.bodyMatcher = v -> true;
		this.responseTypeMatcher = v -> true;
	}

	@Override
	public boolean matches(InvocationData invocationData) {
		return this.basePathMatcher.matches(invocationData.getBasePath())
		       && this.pathMatcher.matches(invocationData.getEndpoint())
		       && this.httpMethodMatcher.matches(invocationData.getHttpMethod())
		       && this.httpHeadersMatcher.matches(invocationData.getHeaders())
		       && this.pathParamsMatcher.matches(invocationData.getPathParams())
		       && this.queryParamsMatcher.matches(invocationData.getQueryParams())
		       && this.bodyMatcher.matches(invocationData.getRequestBody())
		       && this.responseTypeMatcher.matches(invocationData.getResponseType());
	}

	protected void setBasePathMatcher(Matcher<String> basePathMatcher) {
		this.basePathMatcher = basePathMatcher;
	}

	protected void setPathMatcher(Matcher<String> pathMatcher) {
		this.pathMatcher = pathMatcher;
	}

	protected void setHttpMethodMatcher(Matcher<HttpMethod> httpMethodMatcher) {
		this.httpMethodMatcher = httpMethodMatcher;
	}

	protected void setHttpHeadersMatcher(Matcher<HttpHeaders> httpHeadersMatcher) {
		this.httpHeadersMatcher = httpHeadersMatcher;
	}

	protected void setPathParamsMatcher(Matcher<Map<String, String>> pathParamsMatcher) {
		this.pathParamsMatcher = pathParamsMatcher;
	}

	protected void setQueryParamsMatcher(Matcher<MultiValueMap<String, String>> queryParamsMatcher) {
		this.queryParamsMatcher = queryParamsMatcher;
	}

	protected void setBodyMatcher(Matcher<Object> bodyMatcher) {
		this.bodyMatcher = bodyMatcher;
	}

	protected void setResponseTypeMatcher(Matcher<Type> responseTypeMatcher) {
		this.responseTypeMatcher = responseTypeMatcher;
	}

}
