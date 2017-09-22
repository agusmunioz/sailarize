package com.github.sailarize.form;

public class ValueInputBuilder {

    private String name;
    private String id;
    private String title;
    private String mask;
    private Object value;

    private ValueInputBuilder(String name) {
        this.name = name;
    }

    public static ValueInputBuilder name(String name) {
        return new ValueInputBuilder(name);
    }

    public ValueInputBuilder id(String id) {
        this.id = id;
        return this;
    }

    public ValueInputBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ValueInputBuilder mask(String mask) {
        this.mask = mask;
        return this;
    }

    public ValueInputBuilder value(Object value) {
        this.value = value;
        return this;
    }

    public ValueInput build() {
        ValueInput input = new ValueInput(this.name);
        input.setId(this.id);
        input.setTitle(this.title);
        input.setMask(this.mask);
        input.setValue(this.value);
        return input;
    }

}