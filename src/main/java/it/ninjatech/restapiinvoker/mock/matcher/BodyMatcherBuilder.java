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

/**
 * <p>
 * Implementation of the {@link AbstractMatcherBuilder} for a generic object.
 * </p>
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public final class BodyMatcherBuilder extends AbstractMatcherBuilder<Object> {

	/**
	 * Constructs a new BodyMatcherBuilder.
	 *
	 * @param mockMatcherBuilder Mock matcher builder
	 * @param matcherSetter Matcher setter
	 */
	protected BodyMatcherBuilder(MockMatcherBuilder mockMatcherBuilder, MatcherSetter<Object> matcherSetter) {
		super(mockMatcherBuilder, matcherSetter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.ninjatech.restapiinvoker.mock.matcher.AbstractMatcherBuilder#all()
	 */
	@Override
	public MockMatcherBuilder all() {
		this.matcherSetter.set(b -> true);

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
	public MockMatcherBuilder predicate(Matcher<Object> matcher) {
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
	public MockMatcherBuilder isEqualsTo(Object value) {
		this.matcherSetter.set(b -> value.equals(b));

		return this.mockMatcherBuilder;
	}

}
