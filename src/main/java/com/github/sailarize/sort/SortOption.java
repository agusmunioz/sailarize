package com.github.sailarize.sort;

/**
 * Models a sort option for resources list sorting.
 * 
 * @author agusmunioz
 * 
 */
public interface SortOption {

	/**
	 * The option value, used for indicating the type of sort.
	 * 
	 * @return the value.
	 */
	String getValue();

	/**
	 * Indicates the sort direction (ascending or descending)
	 * 
	 * @return the value for the sort direction.
	 */
	String getDirection();

	/**
	 * The sort title.
	 * 
	 * @return a user friendly text that describes the sort. It is not required.
	 */
	String getTitle();

}
