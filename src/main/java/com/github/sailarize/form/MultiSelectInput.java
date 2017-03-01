package com.github.sailarize.form;

/**
 * Models a {@link SelectInput} that allows multiple selections.
 * 
 * @author agusmunioz
 *
 */
public class MultiSelectInput extends SelectInput {

    /**
     * Creates an initialized {@link MultiSelectInput}.
     * 
     * @param name
     *            the input name.
     */
    public MultiSelectInput(String name) {
        super(name);
    }

    @Override
    public <T> T behave(SelectBehavioral<T> behavioural) {

        return behavioural.multiselect(this);
    }

}
