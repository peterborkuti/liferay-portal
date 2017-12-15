/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.apio.architect.functional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import com.liferay.apio.architect.function.ThrowableFunction;
import com.liferay.apio.architect.test.result.TryMatchers;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;

import org.junit.Test;

/**
 * @author Alejandro Hernández
 */
public class ThrowableFunctionTest {

	@Test
	public void testOnInvokingAndThenShouldExecuteBothFunctions() {
		Try<String> stringTry = Try.success("Live ");

		ThrowableFunction<String, String> firstFunction =
			string -> string + "long and ";

		ThrowableFunction<String, String> secondFunction =
			string -> string + "prosper";

		MatcherAssert.assertThat(
			stringTry.map(firstFunction.andThen(secondFunction)),
			Is.is(
				TryMatchers.aTryWithValueThat(
					equalTo("Live long and prosper"))));
	}

	@Test
	public void testOnInvokingComposeShouldExecuteBothFunctions() {
		Try<String> stringTry = Try.success("3");

		ThrowableFunction<String, Integer> firstFunction = Integer::parseInt;

		ThrowableFunction<Integer, Integer> secondFunction =
			integer -> integer + 3;

		MatcherAssert.assertThat(
			stringTry.map(secondFunction.compose(firstFunction)),
			Is.is(TryMatchers.aTryWithValueThat(equalTo(6))));
	}

}