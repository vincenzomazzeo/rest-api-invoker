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

import java.lang.reflect.Type;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

import it.ninjatech.restapiinvoker.invocationdata.InvocationData;

/**
 * <p>
 * Default implementation of the {@link MockMatcher}.
 * </p>
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public class DefaultMockMatcher implements MockMatcher {

	/** Base path matcher. */
	private Matcher<String> basePathMatcher;

	/** Path matcher. */
	private Matcher<String> pathMatcher;

	/** HTTP method matcher. */
	private Matcher<HttpMethod> httpMethodMatcher;

	/** HTTP headers matcher. */
	private Matcher<HttpHeaders> httpHeadersMatcher;

	/** Path params matcher. */
	private Matcher<Map<String, String>> pathParamsMatcher;

	/** Query params matcher. */
	private Matcher<MultiValueMap<String, String>> queryParamsMatcher;

	/** Body matcher. */
	private Matcher<Object> bodyMatcher;

	/** Response type matcher. */
	private Matcher<Type> responseTypeMatcher;

	/**
	 * Constructs a new DefaultMockMatcher.
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.ninjatech.restapiinvoker.mock.matcher.MockMatcher#matches(it.ninjatech.
	 * restapiinvoker.invocationdata.InvocationData)
	 */
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

	/**
	 * Sets the base path matcher.
	 *
	 * @param basePathMatcher Base path matcher
	 */
	protected void setBasePathMatcher(Matcher<String> basePathMatcher) {
		this.basePathMatcher = basePathMatcher;
	}

	/**
	 * Sets the path matcher.
	 *
	 * @param pathMatcher Path matcher
	 */
	protected void setPathMatcher(Matcher<String> pathMatcher) {
		this.pathMatcher = pathMatcher;
	}

	/**
	 * Sets the HTTP method matcher.
	 *
	 * @param httpMethodMatcher HTTP method matcher
	 */
	protected void setHttpMethodMatcher(Matcher<HttpMethod> httpMethodMatcher) {
		this.httpMethodMatcher = httpMethodMatcher;
	}

	/**
	 * Sets the http headers matcher.
	 *
	 * @param httpHeadersMatcher HTTP headers matcher
	 */
	protected void setHttpHeadersMatcher(Matcher<HttpHeaders> httpHeadersMatcher) {
		this.httpHeadersMatcher = httpHeadersMatcher;
	}

	/**
	 * Sets the path params matcher.
	 *
	 * @param pathParamsMatcher Path params matcher
	 */
	protected void setPathParamsMatcher(Matcher<Map<String, String>> pathParamsMatcher) {
		this.pathParamsMatcher = pathParamsMatcher;
	}

	/**
	 * Sets the query params matcher.
	 *
	 * @param queryParamsMatcher Query params matcher
	 */
	protected void setQueryParamsMatcher(Matcher<MultiValueMap<String, String>> queryParamsMatcher) {
		this.queryParamsMatcher = queryParamsMatcher;
	}

	/**
	 * Sets the body matcher.
	 *
	 * @param bodyMatcher Body matcher
	 */
	protected void setBodyMatcher(Matcher<Object> bodyMatcher) {
		this.bodyMatcher = bodyMatcher;
	}

	/**
	 * Sets the response type matcher.
	 *
	 * @param responseTypeMatcher Response type matcher
	 */
	protected void setResponseTypeMatcher(Matcher<Type> responseTypeMatcher) {
		this.responseTypeMatcher = responseTypeMatcher;
	}

}
