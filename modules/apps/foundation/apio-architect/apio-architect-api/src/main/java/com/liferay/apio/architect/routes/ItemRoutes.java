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

package com.liferay.apio.architect.routes;

import static com.liferay.apio.architect.routes.RoutesBuilderUtil.provide;
import static com.liferay.apio.architect.routes.RoutesBuilderUtil.provideConsumer;

import com.liferay.apio.architect.alias.IdentifierFunction;
import com.liferay.apio.architect.alias.ProvideFunction;
import com.liferay.apio.architect.alias.form.FormBuilderFunction;
import com.liferay.apio.architect.alias.routes.DeleteItemConsumer;
import com.liferay.apio.architect.alias.routes.GetItemFunction;
import com.liferay.apio.architect.alias.routes.UpdateItemFunction;
import com.liferay.apio.architect.consumer.PentaConsumer;
import com.liferay.apio.architect.consumer.TetraConsumer;
import com.liferay.apio.architect.consumer.TriConsumer;
import com.liferay.apio.architect.form.Form;
import com.liferay.apio.architect.function.HexaFunction;
import com.liferay.apio.architect.function.PentaFunction;
import com.liferay.apio.architect.function.TetraFunction;
import com.liferay.apio.architect.function.TriFunction;
import com.liferay.apio.architect.single.model.SingleModel;
import com.liferay.apio.architect.uri.Path;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Holds information about the routes supported for a {@link
 * com.liferay.apio.architect.router.ItemRouter}.
 *
 * <p>
 * This interface's methods return functions to get the different endpoints of
 * the item resource. You should always use a {@link Builder} to create
 * instances of this interface.
 * </p>
 *
 * @author Alejandro Hernández
 * @param  <T> the model's type
 * @see    Builder
 * @review
 */
public class ItemRoutes<T> {

	public ItemRoutes(Builder<T, ?> builder) {
		_deleteItemConsumer = builder._deleteItemConsumer;
		_singleModelFunction = builder._singleModelFunction;
		_updateItemFunction = builder._updateItemFunction;
	}

	/**
	 * Returns the function used to delete the item, if the endpoint was added
	 * through the {@link Builder} and the function therefore exists. Returns
	 * {@code Optional#empty()} otherwise.
	 *
	 * @return the function used to delete the item, if the function exists;
	 *         {@code Optional#empty()} otherwise
	 */
	public Optional<DeleteItemConsumer> getDeleteConsumerOptional() {
		return Optional.ofNullable(_deleteItemConsumer);
	}

	/**
	 * Returns the function used to obtain the item, if the endpoint was added
	 * through the {@link Builder} and the function therefore exists. Returns
	 * {@code Optional#empty()} otherwise.
	 *
	 * @return the function used to obtain the item, if the function exists;
	 *         {@code Optional#empty()} otherwise
	 */
	public Optional<GetItemFunction<T>> getItemFunctionOptional() {
		return Optional.ofNullable(_singleModelFunction);
	}

	/**
	 * Returns the function used to update the item, if the endpoint was added
	 * through the {@link Builder} and the function therefore exists. Returns
	 * {@code Optional#empty()} otherwise.
	 *
	 * @return the function used to update the item, if the function exists;
	 *         {@code Optional#empty()} otherwise
	 */
	public Optional<UpdateItemFunction<T>> getUpdateItemFunctionOptional() {
		return Optional.ofNullable(_updateItemFunction);
	}

	/**
	 * Creates the {@link ItemRoutes} of a {@link
	 * com.liferay.apio.architect.router.ItemRouter}.
	 *
	 * @param  <T> the model's type
	 * @param  <S> the model identifier's type ({@link Long}, {@link String},
	 *         etc.)
	 * @review
	 */
	@SuppressWarnings("unused")
	public static class Builder<T, S> {

		public Builder(
			Class<T> modelClass, ProvideFunction provideFunction,
			IdentifierFunction identifierFunction) {

			_modelClass = modelClass;
			_provideFunction = provideFunction;
			_identifierFunction = identifierFunction;
		}

		/**
		 * Adds a route to an item function with one extra parameter.
		 *
		 * @param  biFunction the function that calculates the item
		 * @param  aClass the class of the item function's second parameter
		 * @return the updated builder
		 */
		public <A> Builder<T, S> addGetter(
			BiFunction<S, A, T> biFunction, Class<A> aClass) {

			_singleModelFunction = httpServletRequest -> path -> provide(
				_provideFunction, httpServletRequest, aClass,
				a -> biFunction.andThen(
					t -> new SingleModel<>(t, _modelClass)
				).apply(
					_getIdentifier(path), a
				));

			return this;
		}

		/**
		 * Adds a route to an item function with none extra parameters.
		 *
		 * @param  function the function that calculates the item
		 * @return the updated builder
		 */
		public Builder<T, S> addGetter(Function<S, T> function) {
			_singleModelFunction =
				httpServletRequest -> path -> function.andThen(
					t -> new SingleModel<>(t, _modelClass)
				).apply(
					_getIdentifier(path)
				);

			return this;
		}

		/**
		 * Adds a route to an item function with four extra parameters.
		 *
		 * @param  pentaFunction the function that calculates the item
		 * @param  aClass the class of the item function's second parameter
		 * @param  bClass the class of the item function's third parameter
		 * @param  cClass the class of the item function's fourth parameter
		 * @param  dClass the class of the item function's fifth parameter
		 * @return the updated builder
		 */
		public <A, B, C, D> Builder<T, S> addGetter(
			PentaFunction<S, A, B, C, D, T> pentaFunction, Class<A> aClass,
			Class<B> bClass, Class<C> cClass, Class<D> dClass) {

			_singleModelFunction = httpServletRequest -> path -> provide(
				_provideFunction, httpServletRequest, aClass, bClass, cClass,
				dClass,
				a -> b -> c -> d -> pentaFunction.andThen(
					t -> new SingleModel<>(t, _modelClass)
				).apply(
					_getIdentifier(path), a, b, c, d
				));

			return this;
		}

		/**
		 * Adds a route to an item function with three extra parameters.
		 *
		 * @param  tetraFunction the function that calculates the item
		 * @param  aClass the class of the item function's second parameter
		 * @param  bClass the class of the item function's third parameter
		 * @param  cClass the class of the item function's fourth parameter
		 * @return the updated builder
		 */
		public <A, B, C> Builder<T, S> addGetter(
			TetraFunction<S, A, B, C, T> tetraFunction, Class<A> aClass,
			Class<B> bClass, Class<C> cClass) {

			_singleModelFunction = httpServletRequest -> path -> provide(
				_provideFunction, httpServletRequest, aClass, bClass, cClass,
				a -> b -> c -> tetraFunction.andThen(
					t -> new SingleModel<>(t, _modelClass)
				).apply(
					_getIdentifier(path), a, b, c
				));

			return this;
		}

		/**
		 * Adds a route to an item function with two extra parameters.
		 *
		 * @param  triFunction the function that calculates the item
		 * @param  aClass the class of the item function's second parameter
		 * @param  bClass the class of the item function's third parameter
		 * @return the updated builder
		 */
		public <A, B> Builder<T, S> addGetter(
			TriFunction<S, A, B, T> triFunction, Class<A> aClass,
			Class<B> bClass) {

			_singleModelFunction = httpServletRequest -> path -> provide(
				_provideFunction, httpServletRequest, aClass, bClass,
				a -> b -> triFunction.andThen(
					t -> new SingleModel<>(t, _modelClass)
				).apply(
					_getIdentifier(path), a, b
				));

			return this;
		}

		/**
		 * Adds a route to a remover function with one extra parameter.
		 *
		 * @param  biConsumer the remover function that removes the item
		 * @param  aClass the class of the item remover function's second
		 *         parameter
		 * @return the updated builder
		 */
		public <A> Builder<T, S> addRemover(
			BiConsumer<S, A> biConsumer, Class<A> aClass) {

			_deleteItemConsumer = httpServletRequest -> path -> provideConsumer(
				_provideFunction, httpServletRequest, aClass,
				a -> biConsumer.accept(_getIdentifier(path), a));

			return this;
		}

		/**
		 * Adds a route to a remover function with none extra parameters.
		 *
		 * @param  consumer the remover function that removes the item
		 * @return the updated builder
		 */
		public Builder<T, S> addRemover(Consumer<S> consumer) {
			_deleteItemConsumer = httpServletRequest -> path -> consumer.accept(
				_getIdentifier(path));

			return this;
		}

		/**
		 * Adds a route to a remover function with four extra parameters.
		 *
		 * @param  pentaConsumer the remover function that removes the item
		 * @param  aClass the class of the item remover function's second
		 *         parameter
		 * @param  bClass the class of the item remover function's third
		 *         parameter
		 * @param  cClass the class of the item remover function's fourth
		 *         parameter
		 * @param  dClass the class of the item remover function's fifth
		 *         parameter
		 * @return the updated builder
		 */
		public <A, B, C, D> Builder<T, S> addRemover(
			PentaConsumer<S, A, B, C, D> pentaConsumer, Class<A> aClass,
			Class<B> bClass, Class<C> cClass, Class<D> dClass) {

			_deleteItemConsumer = httpServletRequest -> path -> provideConsumer(
				_provideFunction, httpServletRequest, aClass, bClass, cClass,
				dClass,
				a -> b -> c -> d -> pentaConsumer.accept(
					_getIdentifier(path), a, b, c, d));

			return this;
		}

		/**
		 * Adds a route to a remover function with three extra parameters.
		 *
		 * @param  tetraConsumer the remover function that removes the item
		 * @param  aClass the class of the item remover function's second
		 *         parameter
		 * @param  bClass the class of the item remover function's third
		 *         parameter
		 * @param  cClass the class of the item remover function's fourth
		 *         parameter
		 * @return the updated builder
		 */
		public <A, B, C> Builder<T, S> addRemover(
			TetraConsumer<S, A, B, C> tetraConsumer, Class<A> aClass,
			Class<B> bClass, Class<C> cClass) {

			_deleteItemConsumer = httpServletRequest -> path -> provideConsumer(
				_provideFunction, httpServletRequest, aClass, bClass, cClass,
				a -> b -> c -> tetraConsumer.accept(
					_getIdentifier(path), a, b, c));

			return this;
		}

		/**
		 * Adds a route to a remover function with two extra parameters.
		 *
		 * @param  triConsumer the remover function that removes the item
		 * @param  aClass the class of the item remover function's second
		 *         parameter
		 * @param  bClass the class of the item remover function's third
		 *         parameter
		 * @return the updated builder
		 */
		public <A, B> Builder<T, S> addRemover(
			TriConsumer<S, A, B> triConsumer, Class<A> aClass,
			Class<B> bClass) {

			_deleteItemConsumer = httpServletRequest -> path -> provideConsumer(
				_provideFunction, httpServletRequest, aClass, bClass,
				a -> b -> triConsumer.accept(_getIdentifier(path), a, b));

			return this;
		}

		/**
		 * Adds a route to a updater function with none extra parameters.
		 *
		 * @param  biFunction the updater function that removes the item
		 * @return the updated builder
		 */
		public <R> Builder<T, S> addUpdater(
			BiFunction<S, R, T> biFunction,
			FormBuilderFunction<R> formBuilderFunction) {

			Form<R> form = formBuilderFunction.apply(new Form.Builder<>());

			_updateItemFunction =
				httpServletRequest -> path -> body -> biFunction.andThen(
					t -> new SingleModel<>(t, _modelClass)
				).apply(
					_getIdentifier(path), form.get(body)
				);

			return this;
		}

		/**
		 * Adds a route to a updater function with four extra parameters.
		 *
		 * @param  hexaFunction the updater function that removes the item
		 * @param  aClass the class of the item updater function's third
		 *         parameter
		 * @param  bClass the class of the item updater function's fourth
		 *         parameter
		 * @param  cClass the class of the item updater function's fifth
		 *         parameter
		 * @param  dClass the class of the item updater function's sixth
		 *         parameter
		 * @return the updated builder
		 */
		public <A, B, C, D, R> Builder<T, S> addUpdater(
			HexaFunction<S, R, A, B, C, D, T> hexaFunction, Class<A> aClass,
			Class<B> bClass, Class<C> cClass, Class<D> dClass,
			FormBuilderFunction<R> formBuilderFunction) {

			Form<R> form = formBuilderFunction.apply(new Form.Builder<>());

			_updateItemFunction = httpServletRequest -> path -> body -> provide(
				_provideFunction, httpServletRequest, aClass, bClass, cClass,
				dClass,
				a -> b -> c -> d -> hexaFunction.andThen(
					t -> new SingleModel<>(t, _modelClass)
				).apply(
					_getIdentifier(path), form.get(body), a, b, c, d
				));

			return this;
		}

		/**
		 * Adds a route to a updater function with three extra parameters.
		 *
		 * @param  pentaFunction the updater function that removes the item
		 * @param  aClass the class of the item updater function's third
		 *         parameter
		 * @param  bClass the class of the item updater function's fourth
		 *         parameter
		 * @param  cClass the class of the item updater function's fifth
		 *         parameter
		 * @return the updated builder
		 */
		public <A, B, C, R> Builder<T, S> addUpdater(
			PentaFunction<S, R, A, B, C, T> pentaFunction, Class<A> aClass,
			Class<B> bClass, Class<C> cClass,
			FormBuilderFunction<R> formBuilderFunction) {

			Form<R> form = formBuilderFunction.apply(new Form.Builder<>());

			_updateItemFunction = httpServletRequest -> path -> body -> provide(
				_provideFunction, httpServletRequest, aClass, bClass, cClass,
				a -> b -> c -> pentaFunction.andThen(
					t -> new SingleModel<>(t, _modelClass)
				).apply(
					_getIdentifier(path), form.get(body), a, b, c
				));

			return this;
		}

		/**
		 * Adds a route to a updater function with two extra parameters.
		 *
		 * @param  tetraFunction the updater function that removes the item
		 * @param  aClass the class of the item updater function's third
		 *         parameter
		 * @param  bClass the class of the item updater function's fourth
		 *         parameter
		 * @return the updated builder
		 */
		public <A, B, R> Builder<T, S> addUpdater(
			TetraFunction<S, R, A, B, T> tetraFunction, Class<A> aClass,
			Class<B> bClass, FormBuilderFunction<R> formBuilderFunction) {

			Form<R> form = formBuilderFunction.apply(new Form.Builder<>());

			_updateItemFunction = httpServletRequest -> path -> body -> provide(
				_provideFunction, httpServletRequest, aClass, bClass,
				a -> b -> tetraFunction.andThen(
					t -> new SingleModel<>(t, _modelClass)
				).apply(
					_getIdentifier(path), form.get(body), a, b
				));

			return this;
		}

		/**
		 * Adds a route to a updater function with one extra parameter.
		 *
		 * @param  triFunction the updater function that removes the item
		 * @param  aClass the class of the item updater function's third
		 *         parameter
		 * @return the updated builder
		 */
		public <A, R> Builder<T, S> addUpdater(
			TriFunction<S, R, A, T> triFunction, Class<A> aClass,
			FormBuilderFunction<R> formBuilderFunction) {

			Form<R> form = formBuilderFunction.apply(new Form.Builder<>());

			_updateItemFunction = httpServletRequest -> path -> body -> provide(
				_provideFunction, httpServletRequest, aClass,
				a -> triFunction.andThen(
					t -> new SingleModel<>(t, _modelClass)
				).apply(
					_getIdentifier(path), form.get(body), a
				));

			return this;
		}

		/**
		 * Constructs the {@link ItemRoutes} instance with the information
		 * provided to the builder.
		 *
		 * @return the {@code Routes} instance
		 */
		public ItemRoutes<T> build() {
			return new ItemRoutes<>(this);
		}

		@SuppressWarnings("unchecked")
		private <V> V _getIdentifier(Path path) {
			return (V)_identifierFunction.apply(path);
		}

		private DeleteItemConsumer _deleteItemConsumer;
		private final IdentifierFunction _identifierFunction;
		private final Class<T> _modelClass;
		private final ProvideFunction _provideFunction;
		private GetItemFunction<T> _singleModelFunction;
		private UpdateItemFunction<T> _updateItemFunction;

	}

	private DeleteItemConsumer _deleteItemConsumer;
	private GetItemFunction<T> _singleModelFunction;
	private UpdateItemFunction<T> _updateItemFunction;

}