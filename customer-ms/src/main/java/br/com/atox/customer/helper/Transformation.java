package br.com.atox.customer.helper;

/**
 * Transformator
 */
@FunctionalInterface
public interface Transformation<T, R> {
	
	R transform (T t);

}
