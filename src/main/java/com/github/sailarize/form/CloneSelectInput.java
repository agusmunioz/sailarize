package com.github.sailarize.form;

import java.util.Collection;

/**
 * A select that can be cloned to send a select values multiple times in the
 * same field.
 * 
 * @author agusmunioz
 *
 */
public class CloneSelectInput extends SelectInput {

    private Integer min;

    private Integer max;

    private Integer start;

    private Collection<Object> selected;

    public CloneSelectInput(String name) {
        super(name);
    }

    @Override
    public <T> T behave(SelectBehavioral<T> behavioural) {

        return behavioural.cloneselect(this);
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Collection<Object> getSelected() {
        return selected;
    }

    public void setSelected(Collection<Object> selected) {
        this.selected = selected;
    }

}
