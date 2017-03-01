package com.github.sailarize.form;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Models a {@link FormInput} with selection options for the input value.
 * 
 * @author agusmunioz
 *
 */
public abstract class SelectInput extends FormInput {

    private Collection<Option> options;

    /**
     * Creates an intiazlied {@link SelectInput}.
     * 
     * @param name
     *            the input name.
     */
    public SelectInput(String name) {
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
     * Adds an option to the select.
     * 
     * @param option
     *            the option.
     *
     * @return the input to keep adding.
     */
    public SelectInput add(Option option) {

        if (this.options == null) {
            this.options = new LinkedList<Option>();
        }

        this.options.add(option);

        return this;
    }

    public abstract <T> T behave(SelectBehavioral<T> behavioural);
}
