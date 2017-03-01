package com.github.sailarize.form;

import java.util.Arrays;

import org.junit.Test;

import com.github.sailarize.asserts.AssertSerialization;

/**
 * Test that any serialization implementation must run (extend) in order to
 * ensure a valid serialization of a {@link MultiSelectInput}.
 * 
 * @author agusmunioz
 *
 */
public abstract class MultiSelectInputSerializationTest {

    /**
     * Test for a select input with minimum information.
     */
    @Test
    public void base() {

        MultiSelectInput input = new MultiSelectInput("input.field");
        input.setOptions(Arrays.asList(new Option("RED"), new Option("BLUE")));

        String resource = this.serialize(input);

        AssertSerialization.assertEquals("Unexpected serialization of a select input with a list of options.",
                "form_multi_select_input-base", resource);

    }

    /**
     * Test for a select input with titles in its options.
     */
    @Test
    public void titles() {

        MultiSelectInput input = new MultiSelectInput("input.field");
        input.setOptions(Arrays.asList(new Option("Red", "RED"), new Option("Blue", "BLUE")));

        String resource = this.serialize(input);

        AssertSerialization.assertEquals("Unexpected serialization of a select input with value and titles.",
                "form_multi_select_input-titles", resource);

    }

    /**
     * Test for a select input with a selected option.
     */
    @Test
    public void selected() {

        MultiSelectInput input = new MultiSelectInput("input.field");
        input.setOptions(Arrays.asList(new Option("RED", true), new Option("BLUE")));

        String resource = this.serialize(input);

        AssertSerialization.assertEquals("Unexpected serialization of a select input with a selected value.",
                "form_multi_select_input-selected", resource);

    }

    /**
     * Test for a full completed select input.
     */
    @Test
    public void full() {

        MultiSelectInput input = new MultiSelectInput("input.field");
        input.setOptions(Arrays.asList(new Option("Red", "RED", true), new Option("Blue", "BLUE", false),
                new Option("Green", "GREEN")));
        input.setId("aSelect");
        input.setTitle("Title");

        String resource = this.serialize(input);

        AssertSerialization.assertEquals("Unexpected serialization of a form input with id.",
                "form_multi_select_input-full", resource);
    }

    protected abstract String serialize(MultiSelectInput input);
}
