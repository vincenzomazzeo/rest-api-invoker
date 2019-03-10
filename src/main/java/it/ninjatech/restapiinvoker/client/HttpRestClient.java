package it.ninjatech.restapiinvoker.client;

import it.ninjatech.restapiinvoker.invocationdata.InvocationData;

public interface HttpRestClient {

	public Object exchange(InvocationData invocationData);

}
