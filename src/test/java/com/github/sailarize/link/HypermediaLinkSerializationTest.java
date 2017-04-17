package com.github.sailarize.link;

import org.junit.Test;

import com.github.sailarize.asserts.AssertSerialization;

/**
 * Test that any serialization implementation must run (extend) in order to
 * ensure a valid serialization of a {@link HypermediaLink}.
 * 
 * @author agusmunioz
 *
 */
public abstract class HypermediaLinkSerializationTest {

    /**
     * Test the serialization of a link with only rel and href values.
     */
    @Test
    public void baseLink() {

        HypermediaLink link = new HypermediaLink();
        link.setHref("www.sailarize.com/alink");
        link.setRel("rel");

        String resource = this.serialize(link);

        AssertSerialization.assertEquals("Unexpected serialization of a base link.", "links-base", resource);
    }

    /**
     * Test the serialization of a link with a title.
     */
    @Test
    public void title() {

        HypermediaLink link = new HypermediaLink();
        link.setHref("www.sailarize.com/alink");
        link.setRel("rel");
        link.setTitle("aTitle");

        String resource = this.serialize(link);

        AssertSerialization.assertEquals("Unexpected serialization of a link with title.", "links-title", resource);
    }

    /**
     * Test the serialization of a link with a type.
     */
    @Test
    public void type() {

        HypermediaLink link = new HypermediaLink();
        link.setHref("www.sailarize.com/alink");
        link.setRel("rel");
        link.setType("application/vnd.sail+json;version=1.0");

        String resource = this.serialize(link);

        AssertSerialization.assertEquals("Unexpected serialization of a link with type.", "links-type", resource);

    }

    /**
     * Test the serialization of a link with data fields.
     */
    @Test
    public void data() {

        HypermediaLink link = new HypermediaLink();
        link.setHref("www.sailarize.com/alink");
        link.setRel("rel");
        link.addData("one", "1");
        link.addData("two", "2");

        String resource = this.serialize(link);

        AssertSerialization.assertEquals("Unexpected serialization of a link with data.", "links-data", resource);

    }

    /**
     * Test the serialization of a link with headers.
     */
    @Test
    public void headers() {

        HypermediaLink link = new HypermediaLink();
        link.setHref("www.sailarize.com/alink");
        link.setRel("rel");
        link.addHeader("one", "1");
        link.addHeader("two", "2");

        String resource = this.serialize(link);

        AssertSerialization.assertEquals("Unexpected serialization of a link with headers.", "links-headers", resource);

    }

    /**
     * Test the serialization of a link with a fusion group.
     */
    @Test
    public void fusion() {

        HypermediaLink link = new HypermediaLink();
        link.setHref("www.sailarize.com/alink");
        link.setRel("rel");
        link.setFusion("fusion");

        String resource = this.serialize(link);

        AssertSerialization.assertEquals("Unexpected serialization of a link with fusion.", "links-fusion", resource);
    }

    /**
     * Test the serialization of a link with a residue.
     */
    @Test
    public void residue() {

        HypermediaLink link = new HypermediaLink();
        link.setHref("www.sailarize.com/alink");
        link.setRel("rel");
        link.setResidue("param=value");

        String resource = this.serialize(link);

        AssertSerialization.assertEquals("Unexpected serialization of a link with fusion.", "links-residue", resource);
    }

    /**
     * Test the serialization of a link with all fields and information set.
     */
    @Test
    public void full() {

        HypermediaLink link = new HypermediaLink();
        link.setHref("www.sailarize.com/alink");
        link.setRel("rel");
        link.setTitle("aTitle");
        link.setType("application/vnd.sail+json;version=1.0");
        link.addData("one", "1");
        link.addData("two", "2");
        link.addHeader("one", "1");
        link.addHeader("two", "2");

        String resource = this.serialize(link);

        AssertSerialization.assertEquals("Unexpected serialization of a full link.", "links-full", resource);

    }

    protected abstract String serialize(HypermediaLink link);
}
