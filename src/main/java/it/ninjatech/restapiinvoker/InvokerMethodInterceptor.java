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

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import it.ninjatech.restapiinvoker.client.HttpRestClient;
import it.ninjatech.restapiinvoker.invocationdata.InvocationData;
import it.ninjatech.restapiinvoker.invocationdata.InvocationDataRetriever;

/**
 * <p>
 * Implementation of the {@link MethodInterceptor} used to intercept the calls
 * to the proxied methods.
 * </p>
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public class InvokerMethodInterceptor implements MethodInterceptor {

	/** HTTP Rest Client. */
	private final HttpRestClient httpRestClient;

	/** Base path. */
	private final String basePath;

	/**
	 * Constructs a new InvokerMethodInterceptor.
	 *
	 * @param httpRestClient HTTP Rest Client
	 * @param basePath Base path
	 */
	public InvokerMethodInterceptor(HttpRestClient httpRestClient, String basePath) {
		this.httpRestClient = httpRestClient;
		this.basePath = basePath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.
	 * MethodInvocation)
	 */
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		InvocationData invocationData = InvocationDataRetriever.retrieve(this.basePath, methodInvocation);

		return this.httpRestClient.exchange(invocationData);
	}

}
