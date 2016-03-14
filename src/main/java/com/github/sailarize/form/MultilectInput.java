package com.github.sailarize.form;

import java.util.Collection;
import java.util.LinkedList;

public class MultilectInput extends FormInput{
	
	private Collection<Option> options;

	/**
	 * Creates an intiazlied {@link SelectInput}.
	 * 
	 * @param name
	 *            the input name.
	 */
	public MultilectInput(String name) {
		super(name);

	}

	/**
	 * The selection options.
	 * 
	 * @return the options.
	 */
	public Collection<Option> getOptions() {
		return options;
	}

	/**
	 * Sets the selection options.
	 * 
	 * @param select
	 *            a list of options.
	 */
	public void setOptions(Collection<Option> select) {
		this.options = select;
	}

	/**
	 * Adds an option to the multilect.
	 * 
	 * @param option
	 *            the option.
	 *
	 * @return the input to keep adding.
	 */
	public MultilectInput add(Option option) {

		if (this.options == null) {
			this.options = new LinkedList<Option>();
		}

		this.options.add(option);

		return this;
	}
}
