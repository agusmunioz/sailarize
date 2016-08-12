package com.github.sailarize.form;

import org.junit.Test;

import com.github.sailarize.asserts.AssertSerialization;

/**
 * Test that any serialization implementation must run (extend) in order to
 * ensure a valid serialization of a {@link ValueInput}.
 * 
 * @author agusmunioz
 *
 */
public abstract class ValueInputSerializationTest {

	/**
	 * Test for a form input with minimum information.
	 */
	@Test
	public void base() {

		ValueInput input = new ValueInput("input.field");

		String resource = this.serialize(input);

		AssertSerialization.assertEquals("Unexpected serialization of a base form input.", "form_value_input-base", resource);

	}

	/**
	 * Test for a form input with id.
	 */
	@Test
	public void id() {

		ValueInput input = new ValueInput("input.field");
		input.setId("field");

		String resource = this.serialize(input);

		AssertSerialization.assertEquals("Unexpected serialization of a form input with id.", "form_value_input-id",
				resource);
	}

	/**
	 * Test for a form input with a value set.
	 */
	@Test
	public void value() {

		ValueInput input = new ValueInput("input.field");
		input.setValue(3);

		String resource = this.serialize(input);

		AssertSerialization.assertEquals("Unexpected serialization of a form input with value.", "form_value_input-value",
				resource);

	}

	/**
	 * Test for a form input with a mask.
	 */
	@Test
	public void mask() {

		ValueInput input = new ValueInput("input.field");
		input.setMask("dd/MM/yyyy");

		String resource = this.serialize(input);

		AssertSerialization.assertEquals("Unexpected serialization of a form input with a mask.", "form_value_input-mask",
				resource);

	}

	/**
	 * Test for a form input with a mask.
	 */
	@Test
	public void title() {

		ValueInput input = new ValueInput("input.field");
		input.setTitle("Title");

		String resource = this.serialize(input);

		AssertSerialization.assertEquals("Unexpected serialization of a form input with a mask.", "form_value_input-title",
				resource);

	}

	/**
	 * Test for a complete form input.
	 */
	@Test
	public void full() {

		ValueInput input = new ValueInput("input.field");
		input.setId("field");
		input.setValue(3);
		input.setTitle("Title");
		input.setMask("dd/MM/yyyy");

		String resource = this.serialize(input);

		AssertSerialization.assertEquals("Unexpected serialization of a full form input.", "form_value_input-full", resource);

	}

	protected abstract String serialize(ValueInput input);
}
