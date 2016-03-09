package com.github.sailarize.form;

import org.junit.Test;

import com.github.sailarize.asserts.AssertSerialization;

/**
 * Test that any serialization implementation must run (extend) in order to ensure a
 * valid serialization of a  {@link FormInput}.
 * 
 * @author agusmunioz
 *
 */
public abstract class FormInputSerializationTest {

	/**
	 * Test for a form input with minimum information.
	 */
	@Test
	public void base() {

		FormInput input = new FormInput("input.field");

		String resource = this.serialize(input);
		
		AssertSerialization.assertEquals("Unexpected serialization of a base form input.", "form_input-base", resource);

	}

	/**
	 * Test for a form input with id.
	 */
	@Test
	public void id() {

		FormInput input = new FormInput("input.field");
		input.setId("field");

		String resource = this.serialize(input);
		
		AssertSerialization.assertEquals("Unexpected serialization of a form input with id.", "form_input-id", resource);
	}

	/**
	 * Test for a form input with a value set.
	 */
	@Test
	public void value() {

		FormInput input = new FormInput("input.field");
		input.setValue(3);

		String resource = this.serialize(input);
		
		AssertSerialization.assertEquals("Unexpected serialization of a form input with value.", "form_input-value",
				resource);

	}

	/**
	 * Test for a form input with a mask.
	 */
	@Test
	public void mask() {

		FormInput input = new FormInput("input.field");
		input.setMask("dd/MM/yyyy");

		String resource = this.serialize(input);
		
		AssertSerialization.assertEquals("Unexpected serialization of a form input with a mask.", "form_input-mask",
				resource);

	}

	/**
	 * Test for a complete form input.
	 */
	@Test
	public void full() {

		FormInput input = new FormInput("input.field");
		input.setId("field");
		input.setValue(3);
		input.setMask("dd/MM/yyyy");

		String resource = this.serialize(input);
		
		AssertSerialization.assertEquals("Unexpected serialization of a full form input.", "form_input-full", resource);

	}
	
	protected abstract String serialize(FormInput input);
}
