package com.github.sailarize.link;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link RelBuilder}.
 * 
 * @author agusmunioz
 * 
 */
public class RelBuilderTest {

    /**
     * Test a rel build with no prefix.
     */
    @Test
    public void noPrefix() {

        String rel = RelBuilder.rel("", "rel");

        String expected = "rel";

        Assert.assertEquals("Unexpected rel with no prefix", expected, rel);
    }

    /**
     * Test a rel build with no prefix and the name all in upper case.
     */
    @Test
    public void noPrefixUpperCase() {

        String rel = RelBuilder.rel("", "REL");

        String expected = "rel";

        Assert.assertEquals("Unexpected rel with no prefix and all upper case", expected, rel);
    }

    /**
     * Test a rel build with prefix.
     */
    @Test
    public void withPrefix() {

        String rel = RelBuilder.rel("prefix", "rel");

        String expected = "prefixRel";

        Assert.assertEquals("Unexpected rel with prefix", expected, rel);
    }

    /**
     * Test a rel build with prefix and the name all in upper case.
     */
    @Test
    public void withPrefixAllUpperCase() {

        String rel = RelBuilder.rel("prefix", "REL");

        String expected = "prefixRel";

        Assert.assertEquals("Unexpected rel with prefix and all upper case", expected, rel);
    }

    /**
     * Test a rel build with no prefix and the name has only one letter.
     */
    @Test
    public void noPrefixOneLeter() {

        String rel = RelBuilder.rel("", "a");

        String expected = "a";

        Assert.assertEquals("Unexpected rel with no prefix a one letter", expected, rel);
    }

    /**
     * Test a rel build with no prefix and the name has only one letter in upper
     * case.
     */
    @Test
    public void noPrefixOneLeterUpperCase() {

        String rel = RelBuilder.rel("", "A");

        String expected = "a";

        Assert.assertEquals("Unexpected rel with no prefix a one letter", expected, rel);
    }

    /**
     * Test a rel build with prefix and the name has only one letter.
     */
    @Test
    public void withPrefixOneLetter() {

        String rel = RelBuilder.rel("prefix", "a");

        String expected = "prefixA";

        Assert.assertEquals("Unexpected rel with prefix a one letter", expected, rel);
    }

    /**
     * Test a rel build with prefix and the name has only one letter in upper
     * case.
     */
    @Test
    public void withPrefixOneLetterUpperCase() {

        String rel = RelBuilder.rel("prefix", "A");

        String expected = "prefixA";

        Assert.assertEquals("Unexpected rel with prefix a one letter in upper case", expected, rel);
    }

    /**
     * Test the rel build with a null prefix.
     */
    @Test
    public void nullPrefix() {

        String rel = RelBuilder.rel(null, "rel");

        String expected = "rel";

        Assert.assertEquals("Unexpected rel with a null prefix", expected, rel);
    }

    /**
     * Test the rel build with a null name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void nullName() {

        RelBuilder.rel("", null);
    }
}
