package com.github.sailarize.form;

/**
 * Models a {@link SelectInput} that allows multiple selections.
 * 
 * @author agusmunioz
 *
 */
public class MultilectInput extends SelectInput {

	/**
	 * Creates an initialized {@link MultilectInput}.
	 * 
	 * @param name
	 *            the input name.
	 */
	public MultilectInput(String name) {
		super(name);
	}

	@Override
	public <T> T behave(SelectBehavioural<T> behavioural) {

		return behavioural.multilect(this);
	}

}
