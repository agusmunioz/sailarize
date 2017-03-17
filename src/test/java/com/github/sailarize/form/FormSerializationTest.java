package com.github.sailarize.form;

import java.util.HashMap;

import org.junit.Test;

import com.github.sailarize.asserts.AssertSerialization;
import com.github.sailarize.http.Http;

/**
 * Test that any serialization implementation must run (extend) in order to
 * ensure a valid serialization of a {@link Form}.
 * 
 * @author agusmunioz
 *
 */
public abstract class FormSerializationTest {

    /**
     * Test the serialization of a form with the minimum information required.
     */
    @Test
    public void base() {

        Form form = new Form();
        form.setId("form");
        form.setMethod(Http.GET);
        form.setAction("www.sailarize.com/forms");

        String resource = this.serialize(form);

        AssertSerialization.assertEquals("Unexpected serialization of a base form", "forms-base", resource);

    }

    /**
     * Test the serialization of a form with a title.
     */
    @Test
    public void title() {

        Form form = new Form();
        form.setId("form");
        form.setMethod(Http.PATCH);
        form.setAction("www.sailarize.com/forms");
        form.setTitle("Title");

        String resource = this.serialize(form);

        AssertSerialization.assertEquals("Unexpected serialization of a form with a title", "forms-title", resource);

    }

    /**
     * Test the serialization of a form with inputs.
     */
    @Test
    public void inputs() {

        Form form = new Form();
        form.setId("form");
        form.setMethod(Http.PATCH);
        form.setAction("www.sailarize.com/forms");
        form.add(new ValueInput("a.field"));
        form.add(new ValueInput("another.field"));

        String resource = this.serialize(form);

        AssertSerialization.assertEquals("Unexpected serialization of a form with inputs", "forms-inputs", resource);

    }

    /**
     * Test the serialization of a form with headers.
     */
    @Test
    public void headers() {

        Form form = new Form();
        form.setId("form");
        form.setMethod(Http.PATCH);
        form.setAction("www.sailarize.com/forms");
        form.addHeader("one", "1");
        form.addHeader("two", "2");

        String resource = this.serialize(form);

        AssertSerialization.assertEquals("Unexpected serialization of a form with headers", "forms-headers", resource);

    }

    /**
     * Test the serialization of a form with body.
     */
    @Test
    public void body() {

        Form form = new Form();
        form.setId("form");
        form.setMethod(Http.POST);
        form.setAction("www.sailarize.com/forms");
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("id", "234234");
        form.setBody(body);

        String resource = this.serialize(form);

        AssertSerialization.assertEquals("Unexpected serialization of a form with body", "forms-body", resource);
    }

    /**
     * Test the serialization of a form with data fields.
     */
    @Test
    public void data() {

        Form form = new Form();
        form.setId("form");
        form.setMethod(Http.GET);
        form.setAction("www.sailarize.com/forms");
        form.addData("value", 2);

        String resource = this.serialize(form);

        AssertSerialization.assertEquals("Unexpected serialization of a form with headers", "forms-data", resource);

    }

    /**
     * Test the serialization of a full form.
     */
    @Test
    public void full() {

        Form form = new Form();
        form.setId("form");
        form.setMethod(Http.PATCH);
        form.setAction("www.sailarize.com/forms");
        form.setTitle("Title");
        form.addData("value", 2);
        form.addHeader("one", "1");
        form.addHeader("two", "2");
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("id", "234234");
        form.setBody(body);
        form.add(new ValueInput("a.field"));
        form.add(new ValueInput("another.field"));
        String resource = this.serialize(form);

        AssertSerialization.assertEquals("Unexpected serialization of a full form", "forms-full", resource);

    }

    protected abstract String serialize(Form form);
}
