package com.github.sailarize.properties;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for {@link Paths}.
 * 
 * @author gonzalogtesta
 *
 */
public class PathsTest {

    /**
     * Test the return of a path with an existing key.
     */
    @Test
    public void existing() {

        String path = Paths.get("paths.test");

        Assert.assertEquals("Unexpected path from properties file.", "/url/path/to/image", path);
    }

    /**
     * Test a null is returned when a not existing key is used.
     */
    @Test
    public void notExisting() {

        String path = Paths.get("not.exist.key");

        Assert.assertNull("A path was returned with a nonexistent key.", path);
    }
}
