package br.com.atox.seller.helper;

/**
 * Transformator
 */
@FunctionalInterface
public interface Transformation<T, R> {
	
	R transform (T t);

}
