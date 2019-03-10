package it.ninjatech.restapiinvoker.mock.matcher;

import org.apache.commons.lang3.StringUtils;

public final class StringMatcherBuilder extends AbstractMatcherBuilder<String> {

	protected StringMatcherBuilder(MockMatcherBuilder mockMatcherBuilder, MatcherSetter<String> matcherSetter) {
		super(mockMatcherBuilder, matcherSetter);
	}

	@Override
	public MockMatcherBuilder all() {
		this.matcherSetter.set(v -> true);

		return this.mockMatcherBuilder;
	}

	@Override
	public MockMatcherBuilder predicate(Matcher<String> matcher) {
		this.matcherSetter.set(matcher);

		return this.mockMatcherBuilder;
	}

	@Override
	public MockMatcherBuilder isEqualsTo(String value) {
		this.matcherSetter.set(v -> StringUtils.equals(value, v));

		return this.mockMatcherBuilder;
	}

	public MockMatcherBuilder isEqualsIgnoreCaseTo(String value) {
		this.matcherSetter.set(v -> StringUtils.equalsIgnoreCase(value, v));

		return this.mockMatcherBuilder;
	}

	public MockMatcherBuilder startsWith(String value) {
		this.matcherSetter.set(v -> (StringUtils.startsWith(value, v)));

		return this.mockMatcherBuilder;
	}

	public MockMatcherBuilder startsWithIgnoreCase(String value) {
		this.matcherSetter.set(v -> (StringUtils.startsWithIgnoreCase(value, v)));

		return this.mockMatcherBuilder;
	}

}
