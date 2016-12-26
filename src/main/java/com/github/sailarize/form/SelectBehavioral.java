package com.github.sailarize.form;

/**
 * Protocol for behaving based on the type of {@link SelectInput}. Works as a
 * double-dispatch.
 * 
 * @author agusmunioz
 *
 * @param <T>
 *            the type of the behaviour result.
 */
public interface SelectBehavioral<T> {

	/**
	 * Behaves based on a {@link SingleSelctInput} select.
	 * 
	 * @param select
	 *            the select input.
	 * 
	 * @return the result of behaving based on a single select.
	 */
	T single(SingleSelectInput select);

	/**
	 * Behaves based on a {@link MultiSelectInput} select.
	 * 
	 * @param select
	 *            the select input.
	 * 
	 * @return the result of behaving based on a multiselect.
	 */
	T multiselect(MultiSelectInput select);

	/**
	 * Behaves based on a {@link CloneSelectInput} select.
	 * 
	 * @param select
	 *            the select input.
	 * 
	 * @return the result of behaving based on a cloneselect.
	 */
	T cloneselect(CloneSelectInput cloneSelect);

}
