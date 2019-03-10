package it.ninjatech.restapiinvoker;

import org.springframework.aop.framework.ProxyFactory;

import it.ninjatech.restapiinvoker.client.HttpRestClient;

public final class RestApiInvokerFactory {

	@SuppressWarnings("unchecked")
	public static <T> T makeRestApiInvoker(Class<T> restApi, HttpRestClient httpRestClient, String basePath) {
		T result = null;

		try {
			InvokerMethodInterceptor invokerMethodInterceptor = new InvokerMethodInterceptor(httpRestClient, basePath);
			ProxyFactory proxyFactory = restApi.isInterface() ? new ProxyFactory(new Class[] { restApi })
			                                                  : new ProxyFactory(restApi.newInstance());
			proxyFactory.addAdvice(invokerMethodInterceptor);
			result = (T) proxyFactory.getProxy();
		}
		catch (Exception e) {
			throw new RuntimeException(String.format("Rest API Invoker creation failed for %s", restApi.getName()), e);
		}

		return result;
	}

	private RestApiInvokerFactory() {
	}

}
