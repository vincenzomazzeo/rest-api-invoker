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
package it.ninjatech.restapiinvoker.invocationdata;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * <p>
 * Instances of this class contain all the needed data to perform the REST call.
 * </p>
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public final class InvocationData {

	/** Base path. */
	private String basePath;

	/** Endpoint. */
	private String endpoint;

	/** HTTP method. */
	private HttpMethod httpMethod;

	/** Headers. */
	private final HttpHeaders headers;

	/** Path params. */
	private final Map<String, String> pathParams;

	/** Query params. */
	private final MultiValueMap<String, String> queryParams;

	/** Request body. */
	private Object requestBody;

	/** Response type. */
	private Type responseType;

	/**
	 * Constructs a new InvocationData.
	 */
	protected InvocationData() {
		this.headers = new HttpHeaders();
		this.pathParams = new HashMap<>();
		this.queryParams = new LinkedMultiValueMap<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append("[\n");
		result.append("  Base Path: ").append(this.basePath).append("\n");
		result.append("  Endpoint: ").append(this.endpoint).append("\n");
		result.append("  HTTP Method: ").append(this.httpMethod).append("\n");
		result.append("  HTTP Headers: ");
		if (this.headers.isEmpty()) {
			result.append("N/A\n");
		}
		else {
			this.headers.forEach((h, v) -> result.append(String.format("    \n%s -> %s", h, v.stream().collect(Collectors.joining(", ")))));
			result.append("\n");
		}
		result.append("  Path Params: ");
		if (this.pathParams.isEmpty()) {
			result.append("N/A\n");
		}
		else {
			this.pathParams.forEach((p, v) -> result.append(String.format("    \n%s -> %s", p, v)));
			result.append("\n");
		}
		result.append("  Query Params: ");
		if (this.queryParams.isEmpty()) {
			result.append("N/A\n");
		}
		else {
			this.queryParams.forEach((p, v) -> result.append(String.format("    \n%s -> %s", p, v.stream().collect(Collectors.joining(", ")))));
			result.append("\n");
		}
		result.append("  Body: N/P\n");
		result.append("  Response Type: ").append(this.responseType != null ? this.responseType.toString() : "N/A").append("\n");
		result.append("]");

		return result.toString();
	}

	/**
	 * Returns the base path.
	 *
	 * @return Base path
	 */
	public String getBasePath() {
		return this.basePath;
	}

	/**
	 * Returns the endpoint.
	 *
	 * @return Endpoint
	 */
	public String getEndpoint() {
		return this.endpoint;
	}

	/**
	 * Returns the HTTP method.
	 *
	 * @return HTTP method
	 */
	public HttpMethod getHttpMethod() {
		return this.httpMethod;
	}

	/**
	 * Returns the headers.
	 *
	 * @return Headers
	 */
	public HttpHeaders getHeaders() {
		return this.headers;
	}

	/**
	 * Returns the path params.
	 *
	 * @return Path params
	 */
	public Map<String, String> getPathParams() {
		return this.pathParams;
	}

	/**
	 * Returns the query params.
	 *
	 * @return Query params
	 */
	public MultiValueMap<String, String> getQueryParams() {
		return this.queryParams;
	}

	/**
	 * Returns the request body.
	 *
	 * @return Request body
	 */
	public Object getRequestBody() {
		return this.requestBody;
	}

	/**
	 * Returns the response type.
	 *
	 * @return Response type
	 */
	public Type getResponseType() {
		return this.responseType;
	}

	/**
	 * Sets the base path.
	 *
	 * @param basePath Base path
	 */
	protected void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	/**
	 * Sets the endpoint.
	 *
	 * @param endpoint Endpoint
	 */
	protected void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * Sets the HTTP method.
	 *
	 * @param httpMethod HTTP method
	 */
	protected void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	/**
	 * Adds a header.
	 *
	 * @param key Key
	 * @param value Value
	 */
	protected void addHeader(String key, String value) {
		this.headers.add(key, value);
	}

	/**
	 * Adds values to a headers.
	 *
	 * @param key Key
	 * @param headers Headers
	 */
	protected void addHeaders(String key, List<String> headers) {
		this.headers.addAll(key, headers);
	}

	/**
	 * Adds the path param.
	 *
	 * @param key Key
	 * @param value Value
	 */
	protected void addPathParam(String key, String value) {
		this.pathParams.put(key, value);
	}

	/**
	 * Adds the query param.
	 *
	 * @param key Key
	 * @param value Value
	 */
	protected void addQueryParam(String key, String value) {
		this.queryParams.add(key, value);
	}

	/**
	 * Adds values to a query params.
	 *
	 * @param key Key
	 * @param values Values
	 */
	protected void addQueryParams(String key, List<String> values) {
		this.queryParams.addAll(key, values);
	}

	/**
	 * Sets the request body.
	 *
	 * @param requestBody Request body
	 */
	protected void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}

	/**
	 * Sets the response type.
	 *
	 * @param responseType Response type
	 */
	protected void setResponseType(Type responseType) {
		this.responseType = responseType;
	}

}
