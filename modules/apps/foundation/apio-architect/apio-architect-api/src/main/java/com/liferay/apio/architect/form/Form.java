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

package com.liferay.apio.architect.form;

import static com.liferay.apio.architect.form.FormUtil.getOptionalBoolean;
import static com.liferay.apio.architect.form.FormUtil.getOptionalDate;
import static com.liferay.apio.architect.form.FormUtil.getOptionalDouble;
import static com.liferay.apio.architect.form.FormUtil.getOptionalLong;
import static com.liferay.apio.architect.form.FormUtil.getOptionalString;
import static com.liferay.apio.architect.form.FormUtil.getRequiredBoolean;
import static com.liferay.apio.architect.form.FormUtil.getRequiredDate;
import static com.liferay.apio.architect.form.FormUtil.getRequiredDouble;
import static com.liferay.apio.architect.form.FormUtil.getRequiredLong;
import static com.liferay.apio.architect.form.FormUtil.getRequiredString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Holds information about the form of an operation. The {@link #get(Map)}
 * method can be used (providing the HTTP request body) to extract the form
 * values as detailed on the {@link Builder}.
 *
 * <p>
 * You should always use a {@link Builder} to create instances of this class.
 * </p>
 *
 * @author Alejandro Hernández
 * @param  <T> the type used to store the {@code Form} information
 * @review
 */
public class Form<T> {

	/**
	 * Returns the information that this {@code Form} holds as the class
	 * specified when creating it with the {@code Builder}.
	 *
	 * @param  body the HTTP request body
	 * @return the information that this {@code Form} holds as a {@code T}
	 *         instance
	 * @review
	 */
	public T get(Map<String, Object> body) {
		T t = _supplier.get();

		_optionalBooleans.forEach(getOptionalBoolean(body, t));
		_optionalDates.forEach(getOptionalDate(body, t));
		_optionalDoubles.forEach(getOptionalDouble(body, t));
		_optionalLongs.forEach(getOptionalLong(body, t));
		_optionalStrings.forEach(getOptionalString(body, t));
		_requiredBooleans.forEach(getRequiredBoolean(body, t));
		_requiredDates.forEach(getRequiredDate(body, t));
		_requiredDoubles.forEach(getRequiredDouble(body, t));
		_requiredLongs.forEach(getRequiredLong(body, t));
		_requiredStrings.forEach(getRequiredString(body, t));

		return t;
	}

	/**
	 * Populates and creates a {@code Form} of type {@code T}.
	 *
	 * @param  <T> the type used to store the {@code Form} information
	 * @review
	 */
	public static class Builder<T> {

		/**
		 * Adds a supplier that provides an instance of the class where the form
		 * values are going to be stored.
		 *
		 * @param  supplier the supplier that provides the class
		 * @return the updated builder
		 * @review
		 */
		public FieldStep constructor(Supplier<T> supplier) {
			_form._supplier = supplier;

			return new FieldStep();
		}

		public class FieldStep {

			/**
			 * Requests an optional boolean from the HTTP request body. Calls
			 * the provided consumer with the store instance (provided with the
			 * {@link #constructor(Supplier)} method and the field value if the
			 * field is present. Throws a {@link
			 * javax.ws.rs.BadRequestException} if the field is found but it
			 * isn't a boolean.
			 *
			 * @param  key the field's key
			 * @param  biConsumer the consumer to call if the field is found
			 * @return the updated builder
			 * @review
			 */
			public FieldStep addOptionalBoolean(
				String key, BiConsumer<T, Boolean> biConsumer) {

				_form._optionalBooleans.put(
					key, t -> aBoolean -> biConsumer.accept(t, aBoolean));

				return this;
			}

			/**
			 * Requests an optional date from the HTTP request body. Calls the
			 * provided consumer with the store instance (provided with the
			 * {@link #constructor(Supplier)} method and the field value if the
			 * field is present. Throws a {@link
			 * javax.ws.rs.BadRequestException} if the field is found but it
			 * isn't a date.
			 *
			 * @param  key the field's key
			 * @param  biConsumer the consumer to call if the field is found
			 * @return the updated builder
			 * @review
			 */
			public FieldStep addOptionalDate(
				String key, BiConsumer<T, Date> biConsumer) {

				_form._optionalDates.put(
					key, t -> date -> biConsumer.accept(t, date));

				return this;
			}

			/**
			 * Requests an optional double number from the HTTP request body.
			 * Calls the provided consumer with the store instance (provided
			 * with the {@link #constructor(Supplier)} method and the field
			 * value if the field is present. Throws a {@link
			 * javax.ws.rs.BadRequestException} if the field is found but it
			 * isn't a double number.
			 *
			 * @param  key the field's key
			 * @param  biConsumer the consumer to call if the field is found
			 * @return the updated builder
			 * @review
			 */
			public FieldStep addOptionalDouble(
				String key, BiConsumer<T, Double> biConsumer) {

				_form._optionalDoubles.put(
					key, t -> aDouble -> biConsumer.accept(t, aDouble));

				return this;
			}

			/**
			 * Requests an optional long number from the HTTP request body.
			 * Calls the provided consumer with the store instance (provided
			 * with the {@link #constructor(Supplier)} method and the field
			 * value if the field is present. Throws a {@link
			 * javax.ws.rs.BadRequestException} if the field is found but it
			 * isn't a long number.
			 *
			 * @param  key the field's key
			 * @param  biConsumer the consumer to call if the field is found
			 * @return the updated builder
			 * @review
			 */
			public FieldStep addOptionalLong(
				String key, BiConsumer<T, Long> biConsumer) {

				_form._optionalLongs.put(
					key, t -> aLong -> biConsumer.accept(t, aLong));

				return this;
			}

			/**
			 * Requests an optional string from the HTTP request body. Calls the
			 * provided consumer with the store instance (provided with the
			 * {@link #constructor(Supplier)} method and the field value if the
			 * field is present. Throws a {@link
			 * javax.ws.rs.BadRequestException} if the field is found but it
			 * isn't a string.
			 *
			 * @param  key the field's key
			 * @param  biConsumer the consumer to call if the field is found
			 * @return the updated builder
			 * @review
			 */
			public FieldStep addOptionalString(
				String key, BiConsumer<T, String> biConsumer) {

				_form._optionalStrings.put(
					key, t -> string -> biConsumer.accept(t, string));

				return this;
			}

			/**
			 * Requests a mandatory boolean from the HTTP request body. Calls
			 * the provided consumer with the store instance (provided with the
			 * {@link #constructor(Supplier)} method and the field value. Throws
			 * a {@link javax.ws.rs.BadRequestException} if the field is not
			 * found, or it's found but it isn't a boolean.
			 *
			 * @param  key the field's key
			 * @param  biConsumer the consumer to call
			 * @return the updated builder
			 * @review
			 */
			public FieldStep addRequiredBoolean(
				String key, BiConsumer<T, Boolean> biConsumer) {

				_form._requiredBooleans.put(
					key, t -> aBoolean -> biConsumer.accept(t, aBoolean));

				return this;
			}

			/**
			 * Requests a mandatory date from the HTTP request body. Calls the
			 * provided consumer with the store instance (provided with the
			 * {@link #constructor(Supplier)} method and the field value. Throws
			 * a {@link javax.ws.rs.BadRequestException} if the field is not
			 * found, or it's found but it isn't a date.
			 *
			 * @param  key the field's key
			 * @param  biConsumer the consumer to call
			 * @return the updated builder
			 * @review
			 */
			public FieldStep addRequiredDate(
				String key, BiConsumer<T, Date> biConsumer) {

				_form._requiredDates.put(
					key, t -> date -> biConsumer.accept(t, date));

				return this;
			}

			/**
			 * Requests a mandatory double number from the HTTP request body.
			 * Calls the provided consumer with the store instance (provided
			 * with the {@link #constructor(Supplier)} method and the field
			 * value. Throws a {@link javax.ws.rs.BadRequestException} if the
			 * field is not found, or it's found but it isn't a double number.
			 *
			 * @param  key the field's key
			 * @param  biConsumer the consumer to call
			 * @return the updated builder
			 * @review
			 */
			public FieldStep addRequiredDouble(
				String key, BiConsumer<T, Double> biConsumer) {

				_form._requiredDoubles.put(
					key, t -> aDouble -> biConsumer.accept(t, aDouble));

				return this;
			}

			/**
			 * Requests a mandatory long number from the HTTP request body.
			 * Calls the provided consumer with the store instance (provided
			 * with the {@link #constructor(Supplier)} method and the field
			 * value. Throws a {@link javax.ws.rs.BadRequestException} if the
			 * field is not found, or it's found but it isn't a long number.
			 *
			 * @param  key the field's key
			 * @param  biConsumer the consumer to call
			 * @return the updated builder
			 * @review
			 */
			public FieldStep addRequiredLong(
				String key, BiConsumer<T, Long> biConsumer) {

				_form._requiredLongs.put(
					key, t -> aLong -> biConsumer.accept(t, aLong));

				return this;
			}

			/**
			 * Requests a mandatory string from the HTTP request body. Calls the
			 * provided consumer with the store instance (provided with the
			 * {@link #constructor(Supplier)} method and the field value. Throws
			 * a {@link javax.ws.rs.BadRequestException} if the field is not
			 * found, or it's found but it isn't a string.
			 *
			 * @param  key the field's key
			 * @param  biConsumer the consumer to call
			 * @return the updated builder
			 * @review
			 */
			public FieldStep addRequiredString(
				String key, BiConsumer<T, String> biConsumer) {

				_form._requiredStrings.put(
					key, t -> string -> biConsumer.accept(t, string));

				return this;
			}

			/**
			 * Constructs the {@link Form} instance with the information
			 * provided to the builder.
			 *
			 * @return the {@code Form} instance
			 */
			public Form<T> build() {
				return _form;
			}

		}

		private final Form<T> _form = new Form<>();

	}

	private Form() {
	}

	private final Map<String, Function<T, Consumer<Boolean>>>
		_optionalBooleans = new HashMap<>();
	private final Map<String, Function<T, Consumer<Date>>> _optionalDates =
		new HashMap<>();
	private final Map<String, Function<T, Consumer<Double>>> _optionalDoubles =
		new HashMap<>();
	private final Map<String, Function<T, Consumer<Long>>> _optionalLongs =
		new HashMap<>();
	private final Map<String, Function<T, Consumer<String>>> _optionalStrings =
		new HashMap<>();
	private final Map<String, Function<T, Consumer<Boolean>>>
		_requiredBooleans = new HashMap<>();
	private final Map<String, Function<T, Consumer<Date>>> _requiredDates =
		new HashMap<>();
	private final Map<String, Function<T, Consumer<Double>>> _requiredDoubles =
		new HashMap<>();
	private final Map<String, Function<T, Consumer<Long>>> _requiredLongs =
		new HashMap<>();
	private final Map<String, Function<T, Consumer<String>>> _requiredStrings =
		new HashMap<>();
	private Supplier<T> _supplier;

}