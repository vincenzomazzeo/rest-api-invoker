package it.ninjatech.restapiinvoker.mock.matcher;

public abstract class AbstractMatcherBuilder<V> {

	@FunctionalInterface
	protected static interface MatcherSetter<V> {

		public void set(Matcher<V> matcher);

	}

	protected final MockMatcherBuilder mockMatcherBuilder;
	protected final MatcherSetter<V> matcherSetter;

	protected AbstractMatcherBuilder(MockMatcherBuilder mockMatcherBuilder, MatcherSetter<V> matcherSetter) {
		this.mockMatcherBuilder = mockMatcherBuilder;
		this.matcherSetter = matcherSetter;
	}

	public abstract MockMatcherBuilder all();

	public abstract MockMatcherBuilder predicate(Matcher<V> matcher);

	public abstract MockMatcherBuilder isEqualsTo(V value);

}
