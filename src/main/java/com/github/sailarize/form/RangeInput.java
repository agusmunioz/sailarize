package com.github.sailarize.form;

public class RangeInput extends ValueInput {

    private Range range;

    public RangeInput(String name) {
        super(name);
    }

    /**
     * Creates an initialized {@link RangeInput}.
     * 
     * @param id
     *            the input id for direct access.
     * 
     * @param name
     *            the input name, used as the parameter name when submitting.
     * 
     * @param value
     *            the input value.
     * 
     * @param range
     *            the range limit.
     */
    public RangeInput(String id, String name, Object value, Range range) {
        super(id, name, value);
        this.range = range;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

}