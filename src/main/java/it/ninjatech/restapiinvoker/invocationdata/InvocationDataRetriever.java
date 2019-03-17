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

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * Utility class used to retrieve the data to be used for the REST call.
 * </p>
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public final class InvocationDataRetriever {

	/**
	 * Retrieves the data.
	 *
	 * @param basePath Base path
	 * @param invocation Invocation data
	 * @return {@link InvocationData}
	 */
	public static InvocationData retrieve(String basePath, MethodInvocation invocation) {
		InvocationData result = new InvocationData();

		Method method = invocation.getMethod();

		result.setBasePath(basePath);
		processRequestMappingAnnotation(result, method.getDeclaringClass(), method);
		processArguments(result, method.getParameters(), invocation.getArguments());
		processReturnType(result, method);

		return result;
	}

	/**
	 * Processes request mapping annotations and adds the related data to the
	 * {@link InvocationData}.
	 *
	 * @param invocationData {@link InvocationData}
	 * @param type Type
	 * @param method Method
	 */
	private static void processRequestMappingAnnotation(InvocationData invocationData, Class<?> type, Method method) {
		RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
		if (methodRequestMapping == null) {
			throw new RuntimeException(String.format("%s annotation missing on method %s.%s", RequestMapping.class.getSimpleName(), type.getName(), method.getName()));
		}

		RequestMapping classRequestMapping = type.getAnnotation(RequestMapping.class);

		// HttpMethod
		HttpMethod httpMethod = getHttpMethod(methodRequestMapping);
		if (httpMethod == null) {
			throw new RuntimeException(String.format("Unable to retrieve RequestMapping HTTP Method from %s.%s", type.getName(), method.getName()));
		}
		invocationData.setHttpMethod(httpMethod);

		// Endpoint
		String endpoint = getEndpoint(classRequestMapping, methodRequestMapping);
		if (StringUtils.isBlank(endpoint)) {
			throw new RuntimeException(String.format("Unable to retrieve RequestMapping URI from %s.%s", type.getName(), method.getName()));
		}
		invocationData.setEndpoint(endpoint);

		// Content-Type
		String contentType = getConsumes(classRequestMapping, methodRequestMapping);
		if (StringUtils.isNotBlank(contentType)) {
			invocationData.addHeader(HttpHeaders.CONTENT_TYPE, contentType);
		}

		// Accept
		String accept = getProduces(classRequestMapping, methodRequestMapping);
		if (StringUtils.isNotBlank(accept)) {
			invocationData.addHeader(HttpHeaders.ACCEPT, accept);
		}
	}

	/**
	 * Processes arguments and adds the related data to the {@link InvocationData}.
	 *
	 * @param invocationData Invocation data
	 * @param argumentParameters Argument parameters
	 * @param argumentValues Argument values
	 */
	private static void processArguments(InvocationData invocationData, Parameter[] argumentParameters, Object[] argumentValues) {
		for (int i = 0, n = argumentParameters.length; i < n; i++) {
			Parameter argumentParameter = argumentParameters[i];
			Annotation[] annotations = argumentParameter.getAnnotations();
			String argumentName = argumentParameter.getName();

			if (annotations != null) {
				Object argumentValue = argumentValues[i];

				Optional<?> requestHeader = Arrays.stream(annotations).filter(a -> a.annotationType().equals(RequestHeader.class)).findFirst();
				if (requestHeader.isPresent()) {
					addRequestHeader(invocationData, (RequestHeader) requestHeader.get(), argumentName, argumentValue);
				}

				Optional<?> pathVariable = Arrays.stream(annotations).filter(a -> a.annotationType().equals(PathVariable.class)).findFirst();
				if (pathVariable.isPresent()) {
					addPathParam(invocationData, (PathVariable) pathVariable.get(), argumentName, argumentValue);
				}

				Optional<?> requestParam = Arrays.stream(annotations).filter(a -> a.annotationType().equals(RequestParam.class)).findFirst();
				if (requestParam.isPresent()) {
					addQueryParam(invocationData, (RequestParam) requestParam.get(), argumentName, argumentValue);
				}

				Optional<?> requestBody = Arrays.stream(annotations).filter(a -> a.annotationType().equals(RequestBody.class)).findFirst();
				if (requestBody.isPresent()) {
					invocationData.setRequestBody(argumentValue);
				}
			}
		}

		checkIfAllPathParamsAreResolved(invocationData);
	}

	/**
	 * Processes return type and adds the related data to the
	 * {@link InvocationData}.
	 *
	 * @param invocationData Invocation data
	 * @param method Method
	 */
	private static void processReturnType(InvocationData invocationData, Method method) {
		invocationData.setResponseType(method.getGenericReturnType());
	}

	/**
	 * Gets the HTTP method.
	 *
	 * @param methodRequestMapping Request mapping
	 * @return HTTP method
	 */
	private static HttpMethod getHttpMethod(RequestMapping methodRequestMapping) {
		return methodRequestMapping.method().length > 0 ? HttpMethod.resolve(methodRequestMapping.method()[0].name()) : null;
	}

	/**
	 * Gets the endpoint.
	 *
	 * @param classRequestMapping Request mapping
	 * @param methodRequestMapping Request mapping
	 * @return Endpoint
	 */
	private static String getEndpoint(RequestMapping classRequestMapping, RequestMapping methodRequestMapping) {
		StringBuilder result = new StringBuilder();

		if (classRequestMapping != null) {
			String uri = getUri(classRequestMapping);
			if (uri != null) {
				result.append(uri);
				if (!uri.endsWith("/")) {
					result.append("/");
				}
			}
		}

		if (methodRequestMapping != null) {
			String uri = getUri(methodRequestMapping);
			if (uri != null) {
				result.append(uri);
			}
		}

		return result.toString();
	}

	/**
	 * Gets the value of the consume header.
	 *
	 * @param classRequestMapping Request mapping
	 * @param methodRequestMapping Request mapping
	 * @return Value of the consume header
	 */
	private static String getConsumes(RequestMapping classRequestMapping, RequestMapping methodRequestMapping) {
		String result = null;

		String[] consumes = methodRequestMapping.consumes();
		if (consumes == null || consumes.length == 0) {
			consumes = classRequestMapping.consumes();
		}
		if (consumes != null && consumes.length > 0) {
			result = consumes[0];
		}

		return result;
	}

	/**
	 * Gets the value of the produce header.
	 *
	 * @param classRequestMapping Request mapping
	 * @param methodRequestMapping Request mapping
	 * @return Value of the produce header
	 */
	private static String getProduces(RequestMapping classRequestMapping, RequestMapping methodRequestMapping) {
		String result = null;

		String[] produces = methodRequestMapping.produces();
		if (produces == null || produces.length == 0) {
			produces = classRequestMapping.produces();
		}
		if (produces != null && produces.length > 0) {
			result = produces[0];
		}

		return result;
	}

	/**
	 * Gets the URI.
	 *
	 * @param requestMapping Request mapping
	 * @return URI
	 */
	private static String getUri(RequestMapping requestMapping) {
		String uri = null;

		String[] value = requestMapping.value();
		if (value.length > 0) {
			uri = value[0];
		}

		return uri;
	}

	/**
	 * Adds the request header to the {@link InvocationData}.
	 *
	 * @param invocationData {@link InvocationData}
	 * @param requestHeader Request header
	 * @param argumentName Argument name
	 * @param argumentValue Argument value
	 */
	private static void addRequestHeader(InvocationData invocationData, RequestHeader requestHeader, String argumentName, Object argumentValue) {
		// Header Key
		String key = requestHeader.value();
		if (StringUtils.isBlank(key)) {
			key = requestHeader.name();
			if (StringUtils.isBlank(key)) {
				key = argumentName;
			}
		}

		// Header Value
		if (argumentValue == null) {
			if (requestHeader.required()) {
				throw new RuntimeException(String.format("Missing values for required header '%s'", key));
			}
		}
		else {
			if (argumentValue instanceof List) {
				invocationData.addHeaders(key, ((List<?>) argumentValue).stream().map(Object::toString).collect(Collectors.toList()));
			}
			else {
				invocationData.addHeader(key, argumentValue.toString());
			}
		}
	}

	/**
	 * Adds the path param to the {@link InvocationData}.
	 *
	 * @param invocationData {@link InvocationData}
	 * @param pathVariable Path variable
	 * @param argumentName Argument name
	 * @param argumentValue Argument value
	 */
	private static void addPathParam(InvocationData invocationData, PathVariable pathVariable, String argumentName, Object argumentValue) {
		// Path Param Key
		String key = pathVariable.value();
		if (StringUtils.isBlank(key)) {
			key = pathVariable.name();
			if (StringUtils.isBlank(key)) {
				key = argumentName;
			}
		}

		// Path Param Value
		invocationData.addPathParam(key, argumentValue.toString());
	}

	/**
	 * Adds the query param to the {@link InvocationData}.
	 *
	 * @param invocationData {@link InvocationData}
	 * @param requestParam Request param
	 * @param argumentName Argument name
	 * @param argumentValue Argument value
	 */
	private static void addQueryParam(InvocationData invocationData, RequestParam requestParam, String argumentName, Object argumentValue) {
		// Query Param Key
		String key = requestParam.value();
		if (StringUtils.isBlank(key)) {
			key = requestParam.name();
			if (StringUtils.isBlank(key)) {
				key = argumentName;
			}
		}

		// Query Param Value
		String value;
		if (argumentValue == null) {
			if (requestParam.required()) {
				throw new RuntimeException(String.format("Missing values for required query parameter '%s'", key));
			}
		}
		else {
			if (argumentValue instanceof List) {
				invocationData.addQueryParams(key, ((List<?>) argumentValue).stream().map(e -> String.valueOf(e)).collect(Collectors.toList()));
			}
			else {
				value = String.valueOf(argumentValue);
				if (StringUtils.isBlank(value)) {
					value = requestParam.defaultValue();
				}
				invocationData.addQueryParam(key, value);
			}
		}
	}

	/**
	 * Checks if all path params are resolved.
	 *
	 * @param invocationData {@link InvocationData}
	 */
	private static void checkIfAllPathParamsAreResolved(InvocationData invocationData) {
		Pattern pattern = Pattern.compile("\\{(?<pathParam>.+)\\}");
		String[] pathSegments = invocationData.getEndpoint().split("/");

		Set<String> pathParams = Arrays.stream(pathSegments).map(e -> pattern.matcher(e)).filter(e -> e.matches()).map(e -> e.group("pathParam")).collect(Collectors.toSet());
		pathParams.removeAll(invocationData.getPathParams().keySet());

		if (!pathParams.isEmpty()) {
			throw new RuntimeException(String.format("Missing values for the following path param(s): %s", String.join(", ", pathParams)));
		}
	}

	/**
	 * Hidden constructor.
	 */
	private InvocationDataRetriever() {
	}

}
