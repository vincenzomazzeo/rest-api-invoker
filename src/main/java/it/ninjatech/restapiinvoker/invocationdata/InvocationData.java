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

public final class InvocationData {

	private String basePath;
	private String endpoint;
	private HttpMethod httpMethod;
	private final HttpHeaders headers;
	private final Map<String, String> pathParams;
	private final MultiValueMap<String, String> queryParams;
	private Object requestBody;
	private Type responseType;

	protected InvocationData() {
		this.headers = new HttpHeaders();
		this.pathParams = new HashMap<>();
		this.queryParams = new LinkedMultiValueMap<>();
	}

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

	public String getBasePath() {
		return this.basePath;
	}

	public String getEndpoint() {
		return this.endpoint;
	}

	public HttpMethod getHttpMethod() {
		return this.httpMethod;
	}

	public HttpHeaders getHeaders() {
		return this.headers;
	}

	public Map<String, String> getPathParams() {
		return this.pathParams;
	}

	public MultiValueMap<String, String> getQueryParams() {
		return this.queryParams;
	}

	public Object getRequestBody() {
		return this.requestBody;
	}

	public Type getResponseType() {
		return this.responseType;
	}

	protected void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	protected void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	protected void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	protected void addHeader(String key, String value) {
		this.headers.add(key, value);
	}

	protected void addHeaders(String key, List<String> headers) {
		this.headers.addAll(key, headers);
	}

	protected void addPathParam(String key, String value) {
		this.pathParams.put(key, value);
	}

	protected void addQueryParam(String key, String value) {
		this.queryParams.add(key, value);
	}

	protected void addQueryParams(String key, List<String> values) {
		this.queryParams.addAll(key, values);
	}

	protected void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}

	protected void setResponseType(Type responseType) {
		this.responseType = responseType;
	}

}
