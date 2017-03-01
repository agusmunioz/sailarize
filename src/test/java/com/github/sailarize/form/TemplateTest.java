package com.github.sailarize.form;

import org.junit.Assert;
import org.junit.Test;

public class TemplateTest {

    /**
     * Test a template print.
     */
    @Test
    public void eval() {

        String template = "{#var1}!{#var2}-{#var3}";

        String instance = "VAL1!VAL2-VAL3";

        String expression = "@{#var1}-@{#var2}";

        String expected = "@VAL1-@VAL2";

        String actual = Template.template(template).eval(expression, instance);

        Assert.assertEquals("Unexpected printing.", expected, actual);

        expression = "@{#var2}-@{#var3}";

        expected = "@VAL2-@VAL3";

        actual = Template.template(template).eval(expression, instance);

        Assert.assertEquals("Unexpected printing.", expected, actual);

        expression = "{#var1}+@{#var2}-@{#var3}";

        expected = "VAL1+@VAL2-@VAL3";

        actual = Template.template(template).eval(expression, instance);

        Assert.assertEquals("Unexpected printing.", expected, actual);
    }

    /**
     * Test a template without repeated characters.
     */
    @Test
    public void startsWithText() {

        String template = "START-{#var1}!{#var2}-{#var3}";

        String instance = "START-VAL1!VAL2-VAL3";

        String expression = "@{#var2}-@{#var3}";

        String expected = "@VAL2-@VAL3";

        String actual = Template.template(template).eval(expression, instance);

        Assert.assertEquals("Unexpected printing template with text at start and no text at end.", expected, actual);

    }

    /**
     * Test a template without repeated characters.
     */
    @Test
    public void endsWithText() {

        String template = "{#var1}!{#var2}-{#var3}-END";

        String instance = "VAL1!VAL2-VAL3-END";

        String expression = "@{#var1}-@{#var3}";

        String expected = "@VAL1-@VAL3";

        String actual = Template.template(template).eval(expression, instance);

        Assert.assertEquals("Unexpected printing template with text at end and no text at start.", expected, actual);

    }

    /**
     * Test a template without repeated characters.
     */
    @Test
    public void startsAndEndsWithText() {

        String template = "START-{#var1}!{#var2}-{#var3}-END";

        String instance = "START-VAR1!VAR2-VAR3-END";

        String expression = "@{#var2}-@{#var3}";

        String expected = "@VAR2-@VAR3";

        String actual = Template.template(template).eval(expression, instance);

        Assert.assertEquals("Unexpected printing with not repeated characters in the template.", expected, actual);

    }

    /**
     * Test a template with only one variable.
     */
    @Test
    public void oneVariable() {

        String template = "Hello {#name}, how are you?";

        String instance = "Hello Sail, how are you?";

        String expression = "Bye: {#name}";

        String expected = "Bye: Sail";

        String result = Template.template(template).eval(expression, instance);

        Assert.assertEquals("Unexpected printing wiht only one variable", expected, result);

    }

    /**
     * Test the use of {#instance} variable.
     */
    @Test
    public void instanceVariable() {

        String template = "";

        String instance = "Sail";

        String expression = "Hello {#value}";

        String expected = "Hello Sail";

        String result = Template.template(template).eval(expression, instance);

        Assert.assertEquals("Unexpected printing wiht instance variable only", expected, result);
    }

    /**
     * Test the use of {#instance} variable.
     */
    @Test
    public void instanceVariableCombined() {

        String template = "Name: {#name}";

        String instance = "Name: Sail";

        String expression = "Hello {#name} ({#value})";

        String expected = "Hello Sail (Name: Sail)";

        String result = Template.template(template).eval(expression, instance);

        Assert.assertEquals("Unexpected printing wiht instance variable only", expected, result);
    }

    @Test
    public void separatorRepeated() {

        String template = "{#first},{#second},{#third},{#fourth}";

        String instance = "900,100,200,800";

        String expression = "{#fourth}, ${#second}-${#third}";

        String expected = "800, $100-$200";

        String result = Template.template(template).eval(expression, instance);

        Assert.assertEquals(expected, result);

    }
}
