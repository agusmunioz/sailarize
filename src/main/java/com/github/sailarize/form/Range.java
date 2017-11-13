package com.github.sailarize.form;

public class Range {

    private String start;
    private String end;
    private String step;

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
     * @param start
     *            the input initial range limit.
     * 
     * @param end
     *            the input final range limit.
     */
    public Range(String start, String end, String step) {
        this.start = start;
        this.end = end;
        this.step = step;
    }

    public Object getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public Object getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
    
}