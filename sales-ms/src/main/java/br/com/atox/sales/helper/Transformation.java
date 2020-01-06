package br.com.atox.sales.helper;

/**
 * Transformator
 */
@FunctionalInterface
public interface Transformation<T, R> {
	
	R transform (T t);

}
