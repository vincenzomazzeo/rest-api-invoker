package it.ninjatech.restapiinvoker.mock.matcher;

import it.ninjatech.restapiinvoker.invocationdata.InvocationData;

public interface MockMatcher {

	public boolean matches(InvocationData invocationData);

}
