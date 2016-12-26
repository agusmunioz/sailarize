package com.github.sailarize.form;

/**
 * Models a {@link SelectInput} where only one option can be selected.
 * 
 * @author agusmunioz
 *
 */
public class SingleSelectInput extends SelectInput {

	/**
	 * Creates an initialized {@link SingleSelectInput}.
	 * 
	 * @param name
	 *            the input name.
	 */
	public SingleSelectInput(String name) {
		super(name);
	}

	@Override
	public <T> T behave(SelectBehavioral<T> behavioural) {
		return behavioural.single(this);
	}

}
