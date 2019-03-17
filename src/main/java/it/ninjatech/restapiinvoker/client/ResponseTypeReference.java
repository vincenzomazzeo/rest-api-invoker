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

import java.lang.reflect.Type;

import org.springframework.core.ParameterizedTypeReference;

/**
 * <p>
 * Implementation of {@link ParameterizedTypeReference}.
 * </p>
 *
 * @param <T> Type
 *
 * @author Vincenzo Mazzeo
 * @version 1.0
 * @since 1.0.0
 */
public class ResponseTypeReference<T> extends ParameterizedTypeReference<T> {

	/** Type. */
	private final Type type;

	/**
	 * Creates a new {@link ResponseTypeReference} with the passed {@link Type}.
	 * 
	 * @param type Type
	 */
	protected ResponseTypeReference(Type type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.core.ParameterizedTypeReference#getType()
	 */
	@Override
	public Type getType() {
		return this.type;
	}

}
