package com.github.sailarize.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for {@link ToStringBuilder}.
 * 
 * @author agusmunioz
 * 
 */

public class ToStringBuilderTest {

    /**
     * Test the {@link ToStringBuilder#toString(Object)} method with an instance
     * of a class that extends Object.
     */
    @Test
    public void simpleClass() {

        MockOne one = new MockOne("1", 1);

        String toString = ToStringBuilder.toString(one);

        String expected = "MockOne: [id: 1, number: 1]";

        Assert.assertEquals("Unexpected toString for simple class.", expected, toString);
    }

    /**
     * Test the {@link ToStringBuilder#toString(Object)} method with an instance
     * of a class that belongs to a hierarchy.
     */
    @Test
    public void hierarchy() {

        MockTwo one = new MockTwo("1", 1, "Hola");

        String toString = ToStringBuilder.toString(one);

        String expected = "MockTwo: [text: Hola, id: 1, number: 1]";

        Assert.assertEquals("Unexpected toString for a hierarchy.", expected, toString);
    }

    /**
     * Test the {@link ToStringBuilder#toString(Object)} method with an instance
     * of a class that has no fields.
     */
    @Test
    public void noFields() {

        String toString = ToStringBuilder.toString(new NoFields());

        String expected = "NoFields: []";

        Assert.assertEquals("Unexpected toString for a class with no fields.", expected, toString);
    }
}

@SuppressWarnings("unused")
class MockOne {

    private String id;

    private Integer number;

    public MockOne(String id, Integer number) {
        super();
        this.id = id;
        this.number = number;
    }

}

@SuppressWarnings("unused")
class MockTwo extends MockOne {

    private String text;

    public MockTwo(String id, Integer number, String text) {
        super(id, number);
        this.text = text;
    }
}

class NoFields {

}