package com.github.sailarize.url;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for {@link Filter}.
 * 
 * @author agusmunioz
 * 
 */
public class FilterTest {

    /**
     * Tests {@link Filter#equals(Object)} is reflexive.
     */
    @Test
    public void reflexive() {

        Filter filter = new Filter("name", "value");

        Assert.assertTrue("Equals not reflexive for Filter", filter.equals(filter));

    }

    /**
     * Tests {@link Filter#equals(Object)} is symmetric.
     */
    @Test
    public void symmetric() {

        Filter one = new Filter("name", "value");

        Filter two = new Filter("name", "value");

        Assert.assertTrue("Equals not symetrics", one.equals(two) && two.equals(one));

    }

    /**
     * Tests (and just test, not demonstrates) {@link Filter#equals(Object)} is
     * transitive.
     */
    @Test
    public void transitive() {

        Filter one = new Filter("name", "value");

        Filter two = new Filter("name", "value");

        Filter three = new Filter("name", "value");

        Filter different = new Filter("hola", "chau");

        Assert.assertTrue("Equals not transitive", one.equals(two) && two.equals(three) && one.equals(three));

        Assert.assertTrue("Equals not transitive", one.equals(two) && !two.equals(different) && !one.equals(different));
    }

    /**
     * Tests a Filter instance is not equals to null.
     */
    @Test
    public void notEqualsToNull() {

        Filter filter = new Filter("name", "value");

        Assert.assertFalse("A Filter is equals to null", filter.equals(null));

    }

    /**
     * Tests {@link Filter}s that have null values in all their fields are
     * equals.
     */
    @Test
    public void nullFieldsEquals() {

        Filter one = new Filter(null, null);

        Filter two = new Filter(null, null);

        Assert.assertTrue("Filters with  null fields are not equals", one.equals(two));

    }

    /**
     * Tests {@link Filter}s that have different names are not equals.
     */
    @Test
    public void differentName() {

        Filter one = new Filter("Hola", "1");

        Filter two = new Filter("Chau", "1");

        Assert.assertFalse("Filters with different names are equals", one.equals(two));

    }

    /**
     * Tests {@link Filter}s that have different values (field value) are not
     * equals.
     */
    @Test
    public void differentValue() {

        Filter one = new Filter("Hola", "1");

        Filter two = new Filter("Hola", "2");

        Assert.assertFalse("Filters with different value in value field are equals", one.equals(two));

    }

    /**
     * Tests {@link Filter}s that have different values in all its fields, are
     * not equals.
     */
    @Test
    public void allDifferent() {

        Filter one = new Filter("Hola", "1");

        Filter two = new Filter("Chau", "2");

        Assert.assertFalse("Filters with different values in all fields are equals", one.equals(two));

    }

    /**
     * Tests {@link Filter}s are not equals when one of them has a null name.
     * 
     */
    @Test
    public void nullName() {

        Filter one = new Filter("Hola", "1");

        Filter two = new Filter(null, "2");

        Assert.assertFalse("Filters equals when the second has a null name", one.equals(two));

        Assert.assertFalse("Filters equals when the second has a null name", two.equals(one));
    }

    /**
     * Tests {@link Filter}s are not equals when the second filter has a null
     * value.
     */
    @Test
    public void nullValue() {

        Filter one = new Filter("Hola", "1");

        Filter two = new Filter("Chau", null);

        Assert.assertFalse("Filters equals when the second has a null value", one.equals(two));

    }

    /**
     * Tests a {@link Filter} is not equals to an instance of a different class.
     */
    @Test
    public void differentClass() {

        Filter one = new Filter("Hola", "1");

        Assert.assertFalse("Filter equals to an instace of a different class", one.equals(new Integer(2)));
    }

    /**
     * Tests {@link Filter#hashCode()}s are equals for equals instances.
     */
    @Test
    public void hashCodeWithEqualsInstances() {

        Filter one = new Filter("Hola", "1");

        Filter two = new Filter("Hola", "1");

        Assert.assertEquals("Equals instances have different hashCode", one.hashCode(), two.hashCode());

    }

    /**
     * Tests {@link Filter#hashCode()}s are not equals for different instances.
     * This is not required for hashCode, but optimizes the performance in hash
     * tables.
     */
    @Test
    public void hashCodeWithDifferentInstances() {

        Filter one = new Filter("Hola", "1");

        Filter two = new Filter("Chau", "1");

        Assert.assertNotEquals("Different instances have equals hashCode", one.hashCode(), two.hashCode());

    }
}
