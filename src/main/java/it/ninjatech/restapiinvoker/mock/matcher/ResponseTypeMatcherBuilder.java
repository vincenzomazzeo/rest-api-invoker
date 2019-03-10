package it.ninjatech.restapiinvoker.mock.matcher;

import java.lang.reflect.Type;

public final class ResponseTypeMatcherBuilder extends AbstractMatcherBuilder<Type> {

	protected ResponseTypeMatcherBuilder(MockMatcherBuilder mockMatcherBuilder, MatcherSetter<Type> matcherSetter) {
		super(mockMatcherBuilder, matcherSetter);
	}

	@Override
	public MockMatcherBuilder all() {
		this.matcherSetter.set(v -> true);

		return this.mockMatcherBuilder;
	}

	@Override
	public MockMatcherBuilder predicate(Matcher<Type> matcher) {
		this.matcherSetter.set(matcher);

		return this.mockMatcherBuilder;
	}

	@Override
	public MockMatcherBuilder isEqualsTo(Type value) {
		this.matcherSetter.set(v -> value.equals(v));

		return this.mockMatcherBuilder;
	}

}
