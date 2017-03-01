package com.github.sailarize.media;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for {@link ImageBuilder}
 * 
 * @author gonzalogtesta
 *
 */
public class ImageBuilderTest {

    @Test
    public void stringImagePath() {
        Image img = ImageBuilder.rel("test").host("server.test").path("/image/path").build();

        Assert.assertEquals("Malformed image url", "server.test/image/path", img.getUrl());

    }

    @Test
    public void hostFromProperties() {
        Image img = ImageBuilder.rel("test").host("host.test").path("/image/path").build();

        Assert.assertEquals("Malformed image url", "cdn.test.com/image/path", img.getUrl());

    }

    @Test
    public void pathFromProperties() {
        Image img = ImageBuilder.rel("test").host("server.test").path("paths.test").build();

        Assert.assertEquals("Malformed image url", "server.test/url/path/to/image", img.getUrl());

    }

    @Test
    public void fullPathFromProperties() {
        Image img = ImageBuilder.rel("test").host("host.test").path("paths.test").build();

        Assert.assertEquals("Malformed image url", "cdn.test.com/url/path/to/image", img.getUrl());

    }
}
