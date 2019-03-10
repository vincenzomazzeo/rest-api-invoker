package it.ninjatech.restapiinvoker.mock.matcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public final class MapMatcherBuilder extends AbstractMatcherBuilder<Map<String, String>> {

	protected MapMatcherBuilder(MockMatcherBuilder mockMatcherBuilder, MatcherSetter<Map<String, String>> matcherSetter) {
		super(mockMatcherBuilder, matcherSetter);
	}

	@Override
	public MockMatcherBuilder all() {
		this.matcherSetter.set(m -> true);

		return this.mockMatcherBuilder;
	}

	@Override
	public MockMatcherBuilder predicate(Matcher<Map<String, String>> matcher) {
		this.matcherSetter.set(matcher);

		return this.mockMatcherBuilder;
	}

	@Override
	public MockMatcherBuilder isEqualsTo(Map<String, String> value) {
		this.matcherSetter.set(v -> value.equals(v));

		return this.mockMatcherBuilder;
	}

	public MockMatcherBuilder contains(Map<String, String> value) {
		this.matcherSetter.set(v -> {
			// TODO
		});

		return this.mockMatcherBuilder;
	}

	public MockMatcherBuilder containsKey(String key) {
		this.matcherSetter.set(v -> v != null && v.containsKey(key));

		return this.mockMatcherBuilder;
	}

	public MockMatcherBuilder containsKeys(Collection<String> keys) {
		this.matcherSetter.set(v -> {
			// TODO
		});

		return this.mockMatcherBuilder;
	}

	public MockMatcherBuilder containsKeys(String... keys) {
		return this.containsKeys(Arrays.asList(keys));
	}

	public MockMatcherBuilder containsOnlyKeys(Collection<String> keys) {
		this.matcherSetter.set(v -> {
			// TODO
		});

		return this.mockMatcherBuilder;
	}

	public MockMatcherBuilder containsOnlyKeys(String... keys) {
		return this.containsOnlyKeys(Arrays.asList(keys));
	}

	public MockMatcherBuilder containsValue(String value) {
		this.matcherSetter.set(v -> v != null && v.containsValue(value));

		return this.mockMatcherBuilder;
	}

	public MockMatcherBuilder containsValues(String... values) {
		this.matcherSetter.set(v -> v != null && v.values().containsAll(Arrays.asList(values)));

		return this.mockMatcherBuilder;
	}

	public MockMatcherBuilder isNullOrEmpty() {
		this.matcherSetter.set(v -> v == null || v.isEmpty());

		return this.mockMatcherBuilder;
	}

}
