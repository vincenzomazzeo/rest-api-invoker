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

public class DefaultHttpRestClient implements HttpRestClient {

	private static URI buildUri(String basePath, String endpoint, Map<String, String> pathParams, MultiValueMap<String, String> queryParams) {
		return UriComponentsBuilder.fromHttpUrl(basePath)
		                           .path(endpoint)
		                           .queryParams(queryParams)
		                           .buildAndExpand(pathParams)
		                           .toUri();
	}

	private final RestTemplate restTemplate;

	public DefaultHttpRestClient(HttpClient httpClient) {
		this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
	}

	public DefaultHttpRestClient(HttpClient httpClient,
	                             List<ClientHttpRequestInterceptor> interceptors) {
		this(httpClient);
		this.restTemplate.setInterceptors(interceptors);
	}

	public DefaultHttpRestClient(HttpClient httpClient,
	                             List<ClientHttpRequestInterceptor> interceptors,
	                             List<HttpMessageConverter<?>> messageConverters) {
		this(httpClient, interceptors);
		this.restTemplate.setMessageConverters(messageConverters);
	}

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
