package it.ninjatech.restapiinvoker.mock.matcher;

import java.util.Arrays;
import java.util.EnumSet;

import org.springframework.http.HttpMethod;

public final class HttpMethodMatcherBuilder extends AbstractMatcherBuilder<HttpMethod> {

	protected HttpMethodMatcherBuilder(MockMatcherBuilder mockMatcherBuilder, MatcherSetter<HttpMethod> matcherSetter) {
		super(mockMatcherBuilder, matcherSetter);
	}

	@Override
	public MockMatcherBuilder all() {
		this.matcherSetter.set(v -> true);

		return this.mockMatcherBuilder;
	}

	@Override
	public MockMatcherBuilder predicate(Matcher<HttpMethod> matcher) {
		this.matcherSetter.set(matcher);

		return this.mockMatcherBuilder;
	}

	@Override
	public MockMatcherBuilder isEqualsTo(HttpMethod value) {
		this.matcherSetter.set(v -> value.equals(v));

		return this.mockMatcherBuilder;
	}

	public MockMatcherBuilder any(HttpMethod... httpMethods) {
		this.matcherSetter.set(v -> EnumSet.copyOf(Arrays.asList(httpMethods)).contains(v));

		return this.mockMatcherBuilder;
	}

	public MockMatcherBuilder not(HttpMethod... httpMethods) {
		this.matcherSetter.set(v -> EnumSet.complementOf(EnumSet.copyOf(Arrays.asList(httpMethods))).contains(v));

		return this.mockMatcherBuilder;
	}

}
