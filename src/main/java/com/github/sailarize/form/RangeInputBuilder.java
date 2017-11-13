package com.github.sailarize.form;

public class RangeInputBuilder {

    private String name;
    private String id;
    private String title;
    private String mask;
    private Object value;

    private String start;
    private String end;
    private String step;

    private RangeInputBuilder(String name) {
        this.name = name;
    }

    public static RangeInputBuilder name(String name) {
        return new RangeInputBuilder(name);
    }

    public RangeInputBuilder id(String id) {
        this.id = id;
        return this;
    }

    public RangeInputBuilder title(String title) {
        this.title = title;
        return this;
    }

    public RangeInputBuilder mask(String mask) {
        this.mask = mask;
        return this;
    }

    public RangeInputBuilder value(Object value) {
        this.value = value;
        return this;
    }

    public RangeInputBuilder start(String start) {
        this.start = start;
        return this;
    }

    public RangeInputBuilder end(String end) {
        this.end = end;
        return this;
    }

    public RangeInputBuilder step(String step) {
        this.step = step;
        return this;
    }

    public RangeInput build() {
        RangeInput input = new RangeInput(this.name);
        input.setId(this.id);
        input.setTitle(this.title);
        input.setMask(this.mask);
        input.setValue(this.value);
        Range range = new Range(start, end, step);
        input.setRange(range);
        return input;
    }

}