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
 * Abstract class for all the mock matcher builder.
 * </p>
 *
 * @param <V> Type
 * 
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public abstract class AbstractMatcherBuilder<V> {

	/**
	 * {@link MatcherSetter}.
	 *
	 * @param <V> Type
	 */
	@FunctionalInterface
	protected static interface MatcherSetter<V> {

		/**
		 * Sets the matcher.
		 *
		 * @param matcher Matcher
		 */
		public void set(Matcher<V> matcher);

	}

	/** Mock matcher builder. */
	protected final MockMatcherBuilder mockMatcherBuilder;

	/** Matcher setter. */
	protected final MatcherSetter<V> matcherSetter;

	/**
	 * Constructs a new AbstractMatcherBuilder.
	 *
	 * @param mockMatcherBuilder Mock matcher builder
	 * @param matcherSetter Matcher setter
	 */
	protected AbstractMatcherBuilder(MockMatcherBuilder mockMatcherBuilder, MatcherSetter<V> matcherSetter) {
		this.mockMatcherBuilder = mockMatcherBuilder;
		this.matcherSetter = matcherSetter;
	}

	/**
	 * Sets the matcher as pass-through.
	 *
	 * @return This instance
	 */
	public abstract MockMatcherBuilder all();

	/**
	 * Sets the matcher using the provided predicate.
	 *
	 * @param matcher Matcher
	 * @return This instance
	 */
	public abstract MockMatcherBuilder predicate(Matcher<V> matcher);

	/**
	 * Sets the matcher to check the equivalence with the provided value.
	 *
	 * @param value Value
	 * @return This instance
	 */
	public abstract MockMatcherBuilder isEqualsTo(V value);

}
