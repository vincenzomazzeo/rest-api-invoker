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

import java.util.Arrays;
import java.util.EnumSet;

import org.springframework.http.HttpMethod;

/**
 * <p>
 * Implementation of the {@link AbstractMatcherBuilder} for HTTP Method data.
 * </p>
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public final class HttpMethodMatcherBuilder extends AbstractMatcherBuilder<HttpMethod> {

	/**
	 * Constructs a new HttpMethodMatcherBuilder.
	 *
	 * @param mockMatcherBuilder Mock matcher builder
	 * @param matcherSetter Matcher setter
	 */
	protected HttpMethodMatcherBuilder(MockMatcherBuilder mockMatcherBuilder, MatcherSetter<HttpMethod> matcherSetter) {
		super(mockMatcherBuilder, matcherSetter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.ninjatech.restapiinvoker.mock.matcher.AbstractMatcherBuilder#all()
	 */
	@Override
	public MockMatcherBuilder all() {
		this.matcherSetter.set(v -> true);

		return this.mockMatcherBuilder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.ninjatech.restapiinvoker.mock.matcher.AbstractMatcherBuilder#predicate(it.
	 * ninjatech.restapiinvoker.mock.matcher.Matcher)
	 */
	@Override
	public MockMatcherBuilder predicate(Matcher<HttpMethod> matcher) {
		this.matcherSetter.set(matcher);

		return this.mockMatcherBuilder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.ninjatech.restapiinvoker.mock.matcher.AbstractMatcherBuilder#isEqualsTo(
	 * java.lang.Object)
	 */
	@Override
	public MockMatcherBuilder isEqualsTo(HttpMethod value) {
		this.matcherSetter.set(v -> value.equals(v));

		return this.mockMatcherBuilder;
	}

	/**
	 * Sets the matcher to match any of the provided HTTP methods.
	 *
	 * @param httpMethods HTTP methods
	 * @return Mock matcher builder
	 */
	public MockMatcherBuilder any(HttpMethod... httpMethods) {
		this.matcherSetter.set(v -> EnumSet.copyOf(Arrays.asList(httpMethods)).contains(v));

		return this.mockMatcherBuilder;
	}

	/**
	 * Sets the matcher to match not the provided HTTP methods.
	 *
	 * @param httpMethods HTTP methods
	 * @return Mock matcher builder
	 */
	public MockMatcherBuilder not(HttpMethod... httpMethods) {
		this.matcherSetter.set(v -> EnumSet.complementOf(EnumSet.copyOf(Arrays.asList(httpMethods))).contains(v));

		return this.mockMatcherBuilder;
	}

}
