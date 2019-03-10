package it.ninjatech.restapiinvoker.mock.matcher;

public final class BodyMatcherBuilder extends AbstractMatcherBuilder<Object> {

	protected BodyMatcherBuilder(MockMatcherBuilder mockMatcherBuilder, MatcherSetter<Object> matcherSetter) {
		super(mockMatcherBuilder, matcherSetter);
	}

	@Override
	public MockMatcherBuilder all() {
		this.matcherSetter.set(b -> true);

		return this.mockMatcherBuilder;
	}

	@Override
	public MockMatcherBuilder predicate(Matcher<Object> matcher) {
		this.matcherSetter.set(matcher);

		return this.mockMatcherBuilder;
	}

	@Override
	public MockMatcherBuilder isEqualsTo(Object value) {
		this.matcherSetter.set(b -> value.equals(b));

		return this.mockMatcherBuilder;
	}

}
