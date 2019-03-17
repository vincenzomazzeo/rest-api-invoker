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
import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * Implementation of the {@link AbstractMatcherBuilder} for a generic
 * {@link Map}.
 * </p>
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public final class MapMatcherBuilder extends AbstractMatcherBuilder<Map<String, String>> {

	/**
	 * Constructs a new MapMatcherBuilder.
	 *
	 * @param mockMatcherBuilder Mock matcher builder
	 * @param matcherSetter Matcher setter
	 */
	protected MapMatcherBuilder(MockMatcherBuilder mockMatcherBuilder, MatcherSetter<Map<String, String>> matcherSetter) {
		super(mockMatcherBuilder, matcherSetter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.ninjatech.restapiinvoker.mock.matcher.AbstractMatcherBuilder#all()
	 */
	@Override
	public MockMatcherBuilder all() {
		this.matcherSetter.set(m -> true);

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
	public MockMatcherBuilder predicate(Matcher<Map<String, String>> matcher) {
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
	public MockMatcherBuilder isEqualsTo(Map<String, String> value) {
		this.matcherSetter.set(v -> v != null && value != null && value.equals(v));

		return this.mockMatcherBuilder;
	}

	/**
	 * Sets the matcher to match if the map of the matcher contains the provided
	 * map.
	 *
	 * @param value Value
	 * @return Mock matcher builder
	 */
	public MockMatcherBuilder contains(Map<String, String> value) {
		this.matcherSetter.set(v -> v != null && value != null && v.entrySet().containsAll(value.entrySet()));

		return this.mockMatcherBuilder;
	}

	/**
	 * Sets the matcher to match if the map of the matcher contains the provided
	 * key.
	 *
	 * @param key Key
	 * @return Mock matcher builder
	 */
	public MockMatcherBuilder containsKey(String key) {
		this.matcherSetter.set(v -> v != null && key != null && v.containsKey(key));

		return this.mockMatcherBuilder;
	}

	/**
	 * Sets the matcher to match if the map of the matcher contains all the provided
	 * keys.
	 *
	 * @param keys Keys
	 * @return Mock matcher builder
	 */
	public MockMatcherBuilder containsKeys(Collection<String> keys) {
		this.matcherSetter.set(v -> v != null && keys != null && v.keySet().containsAll(keys));

		return this.mockMatcherBuilder;
	}

	/**
	 * Sets the matcher to match if the map of the matcher contains all the provided
	 * keys.
	 *
	 * @param keys Keys
	 * @return Mock matcher builder
	 */
	public MockMatcherBuilder containsKeys(String... keys) {
		return this.containsKeys(Arrays.asList(keys));
	}

	/**
	 * Sets the matcher to match if the map of the matcher contains all and only the
	 * provided keys.
	 *
	 * @param keys Keys
	 * @return Mock matcher builder
	 */
	public MockMatcherBuilder containsOnlyKeys(Collection<String> keys) {
		this.matcherSetter.set(v -> v != null && keys != null && v.size() == keys.size() && v.keySet().containsAll(keys));

		return this.mockMatcherBuilder;
	}

	/**
	 * Sets the matcher to match if the map of the matcher contains all and only the
	 * provided keys.
	 *
	 * @param keys Keys
	 * @return Mock matcher builder
	 */
	public MockMatcherBuilder containsOnlyKeys(String... keys) {
		return this.containsOnlyKeys(Arrays.asList(keys));
	}

	/**
	 * Sets the matcher to match if the map of the matcher contains the provided
	 * value.
	 *
	 * @param value Value
	 * @return Mock matcher builder
	 */
	public MockMatcherBuilder containsValue(String value) {
		this.matcherSetter.set(v -> v != null && value != null && v.containsValue(value));

		return this.mockMatcherBuilder;
	}

	/**
	 * Sets the matcher to match if the map of the matcher contains all the provided
	 * values.
	 *
	 * @param values Values
	 * @return Mock matcher builder
	 */
	public MockMatcherBuilder containsValues(String... values) {
		this.matcherSetter.set(v -> v != null && v.values().containsAll(Arrays.asList(values)));

		return this.mockMatcherBuilder;
	}

	/**
	 * Sets the matcher to match if the map of the matcher is null or empty.
	 *
	 * @return Mock matcher builder
	 */
	public MockMatcherBuilder isNullOrEmpty() {
		this.matcherSetter.set(v -> v == null || v.isEmpty());

		return this.mockMatcherBuilder;
	}

}
