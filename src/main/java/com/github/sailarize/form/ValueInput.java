package com.github.sailarize.form;

/**
 * Models a {@link FormInput} with a simple value.
 * 
 * @author agusmunioz
 * 
 */
public class ValueInput extends FormInput {

    private Object value;

    private String mask;

    public ValueInput(String name) {
        super(name);
    }

    /**
     * Creates an initialized {@link ValueInput}.
     * 
     * @param id
     *            the input id for direct access.
     * 
     * @param name
     *            the input name, used as the parameter name when submitting.
     * 
     * @param value
     *            the input value.
     */
    public ValueInput(String name, Object value) {

        this(name);
        this.value = value;
    }

    /**
     * Creates an initialized {@link ValueInput}.
     * 
     * @param id
     *            the input id for direct access.
     * 
     * @param name
     *            the input name, used as the parameter name when submit.
     */
    public ValueInput(String id, String name) {

        super(id, name);
    }

    /**
     * Creates an initialized {@link ValueInput}.
     * 
     * @param id
     *            the input id for direct access.
     * 
     * @param name
     *            the input name, used as the parameter name when submitting.
     * 
     * @param value
     *            the input value.
     */
    public ValueInput(String id, String name, Object value) {

        this(id, name);
        this.value = value;
    }

    /**
     * Gets the input value.
     * 
     * @return the value.
     */
    public Object getValue() {

        return value;
    }

    /**
     * Sets the input value.
     * 
     * @param value
     *            a value.
     */
    public void setValue(Object value) {

        this.value = value;
    }

    /**
     * Gets the input mask used for validating the input value.
     * 
     * @return the mask or null if not set.
     */
    public String getMask() {

        return mask;
    }

    /**
     * Sets the input mask used for validation the input value.
     * 
     * @param mask
     *            a regular expression.
     */
    public void setMask(String mask) {

        this.mask = mask;
    }

}
