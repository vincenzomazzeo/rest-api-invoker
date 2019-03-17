/*
MIT License

Copyright (c) 2019 Vincenzo Mazzeo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package it.ninjatech.restapiinvoker;

import org.springframework.aop.framework.ProxyFactory;

import it.ninjatech.restapiinvoker.client.HttpRestClient;

/**
 * <p>
 * Factory of the Invokers.
 * </p>
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public final class RestApiInvokerFactory {

	/**
	 * Makes a new Invoker creating a Spring proxy around the provided REST Api
	 * class. The proxy intercepts all the calls to the methods of the class,
	 * retrieving the data of the call and performing it using the provided
	 * {@link HttpRestClient}.
	 *
	 * @param <T> Type of the REST Api class
	 * @param restApi REST Api class
	 * @param httpRestClient HTTP Rest Client
	 * @param basePath Base path of the REST Api's
	 * @return The built proxy
	 */
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

	/**
	 * Hidden constructor.
	 */
	private RestApiInvokerFactory() {
	}

}
