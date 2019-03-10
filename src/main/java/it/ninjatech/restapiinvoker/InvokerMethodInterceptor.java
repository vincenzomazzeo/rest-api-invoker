package it.ninjatech.restapiinvoker;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import it.ninjatech.restapiinvoker.client.HttpRestClient;
import it.ninjatech.restapiinvoker.invocationdata.InvocationData;
import it.ninjatech.restapiinvoker.invocationdata.InvocationDataRetriever;

public class InvokerMethodInterceptor implements MethodInterceptor {

	private final HttpRestClient httpRestClient;
	private final String basePath;

	public InvokerMethodInterceptor(HttpRestClient httpRestClient, String basePath) {
		this.httpRestClient = httpRestClient;
		this.basePath = basePath;
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		InvocationData invocationData = InvocationDataRetriever.retrieve(this.basePath, methodInvocation);

		return this.httpRestClient.exchange(invocationData);
	}

}
