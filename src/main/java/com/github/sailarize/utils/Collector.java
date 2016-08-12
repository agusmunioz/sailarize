package com.github.sailarize.utils;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Utility class for manipulating {@link Collection}.
 * 
 * @author agusmunioz
 * 
 */
public class Collector {

	/**
	 * Creates a new collection with the same elements but the rejected one.
	 * 
	 * @param elements
	 *            the complete collection of elements.
	 * 
	 * @param reject
	 *            the element to reject.
	 * 
	 * @return a new collection with the same elements but the rejected.
	 */
	public static <T> Collection<T> reject(Collection<T> elements, T reject) {

		LinkedList<T> collection = new LinkedList<T>(elements);
		collection.remove(reject);

		return collection;
	}
}
