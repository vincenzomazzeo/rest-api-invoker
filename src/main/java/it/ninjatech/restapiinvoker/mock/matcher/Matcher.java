package it.ninjatech.restapiinvoker.mock.matcher;

@FunctionalInterface
public interface Matcher<T> {

	public boolean matches(T value);

}
