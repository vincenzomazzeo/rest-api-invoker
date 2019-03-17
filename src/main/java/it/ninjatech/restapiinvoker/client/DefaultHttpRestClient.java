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
package it.ninjatech.restapiinvoker.client;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import it.ninjatech.restapiinvoker.invocationdata.InvocationData;

/**
 * <p>
 * Default implementation of the {@link HttpRestClient} that uses
 * {@link HttpClient} to perform the calls.
 * </p>
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public class DefaultHttpRestClient implements HttpRestClient {

	/**
	 * Builds the URI of the call.
	 * 
	 * @param basePath Base path
	 * @param endpoint Endpoint
	 * @param pathParams Path parameters
	 * @param queryParams Query parameters
	 * @return URI of the call
	 */
	private static URI buildUri(String basePath, String endpoint, Map<String, String> pathParams, MultiValueMap<String, String> queryParams) {
		return UriComponentsBuilder.fromHttpUrl(basePath)
		                           .path(endpoint)
		                           .queryParams(queryParams)
		                           .buildAndExpand(pathParams)
		                           .toUri();
	}

	/** {@link RestTemplate}. */
	private final RestTemplate restTemplate;

	/**
	 * Constructs a new {@link DefaultHttpRestClient}.
	 * 
	 * @param httpClient {@link HttpClient}
	 */
	public DefaultHttpRestClient(HttpClient httpClient) {
		this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
	}

	/**
	 * Constructs a new {@link DefaultHttpRestClient}.
	 * 
	 * @param httpClient {@link HttpClient}
	 * @param interceptors List of {@link ClientHttpRequestInterceptor}
	 */
	public DefaultHttpRestClient(HttpClient httpClient,
	                             List<ClientHttpRequestInterceptor> interceptors) {
		this(httpClient);
		this.restTemplate.setInterceptors(interceptors);
	}

	/**
	 * Constructs a new {@link DefaultHttpRestClient}.
	 * 
	 * @param httpClient {@link HttpClient}
	 * @param interceptors List of {@link ClientHttpRequestInterceptor}
	 * @param messageConverters List of {@link HttpMessageConverter}
	 */
	public DefaultHttpRestClient(HttpClient httpClient,
	                             List<ClientHttpRequestInterceptor> interceptors,
	                             List<HttpMessageConverter<?>> messageConverters) {
		this(httpClient, interceptors);
		this.restTemplate.setMessageConverters(messageConverters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.ninjatech.restapiinvoker.client.HttpRestClient#exchange(it.ninjatech.
	 * restapiinvoker.invocationdata.InvocationData)
	 */
	@Override
	public Object exchange(InvocationData invocationData) {
		URI uri = buildUri(invocationData.getBasePath(),
		                   invocationData.getEndpoint(),
		                   invocationData.getPathParams(),
		                   invocationData.getQueryParams());
		HttpEntity<?> requestEntity = new HttpEntity<>(invocationData.getRequestBody(), invocationData.getHeaders());

		Type responseType = invocationData.getResponseType();
		if (responseType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) responseType;
			if (parameterizedType.getRawType().equals(ResponseEntity.class)) {
				responseType = parameterizedType.getActualTypeArguments()[0];
			}
		}

		ParameterizedTypeReference<?> responseTypeReference = new ResponseTypeReference<>(responseType);

		return this.restTemplate.exchange(uri, invocationData.getHttpMethod(), requestEntity, responseTypeReference);
	}

}
