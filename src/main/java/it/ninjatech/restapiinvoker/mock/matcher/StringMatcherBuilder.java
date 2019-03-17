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

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * Implementation of the {@link AbstractMatcherBuilder} for a generic string.
 * </p>
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public final class StringMatcherBuilder extends AbstractMatcherBuilder<String> {

	/**
	 * Constructs a new StringMatcherBuilder.
	 *
	 * @param mockMatcherBuilder Mock matcher builder
	 * @param matcherSetter Matcher setter
	 */
	protected StringMatcherBuilder(MockMatcherBuilder mockMatcherBuilder, MatcherSetter<String> matcherSetter) {
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
	public MockMatcherBuilder predicate(Matcher<String> matcher) {
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
	public MockMatcherBuilder isEqualsTo(String value) {
		this.matcherSetter.set(v -> StringUtils.equals(value, v));

		return this.mockMatcherBuilder;
	}

	/**
	 * Sets the matcher to match if the value of the matcher is equals, ignoring
	 * case, to the provided value.
	 *
	 * @param value Value
	 * @return Mock matcher builder
	 */
	public MockMatcherBuilder isEqualsIgnoreCaseTo(String value) {
		this.matcherSetter.set(v -> StringUtils.equalsIgnoreCase(value, v));

		return this.mockMatcherBuilder;
	}

	/**
	 * Sets the matcher to match if the value of the matcher starts with the
	 * provided value.
	 *
	 * @param value Value
	 * @return Mock matcher builder
	 */
	public MockMatcherBuilder startsWith(String value) {
		this.matcherSetter.set(v -> (StringUtils.startsWith(value, v)));

		return this.mockMatcherBuilder;
	}

	/**
	 * Sets the matcher to match if the value of the matcher starts, ignoring case,
	 * with the provided value.
	 *
	 * @param value Value
	 * @return Mock matcher builder
	 */
	public MockMatcherBuilder startsWithIgnoreCase(String value) {
		this.matcherSetter.set(v -> (StringUtils.startsWithIgnoreCase(value, v)));

		return this.mockMatcherBuilder;
	}

}
