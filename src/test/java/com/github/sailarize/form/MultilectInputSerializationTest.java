package com.github.sailarize.form;

import java.util.Arrays;

import org.junit.Test;

import com.github.sailarize.asserts.AssertSerialization;

/**
 * Test that any serialization implementation must run (extend) in order to
 * ensure a valid serialization of a {@link MultilectInput}.
 * 
 * @author agusmunioz
 *
 */
public abstract class MultilectInputSerializationTest {

	/**
	 * Test for a select input with minimum information.
	 */
	@Test
	public void base() {

		MultilectInput input = new MultilectInput("input.field");
		input.setOptions(Arrays.asList(new Option(null, "RED"), new Option(null, "BLUE")));

		String resource = this.serialize(input);

		AssertSerialization.assertEquals("Unexpected serialization of a select input with a list of options.",
				"form_multi_select_input-base", resource);

	}

	/**
	 * Test for a select input with titles in its options.
	 */
	@Test
	public void titles() {

		MultilectInput input = new MultilectInput("input.field");
		input.setOptions(Arrays.asList(new Option("Red", "RED"), new Option("Blue", "BLUE")));

		String resource = this.serialize(input);

		AssertSerialization.assertEquals("Unexpected serialization of a select input with value and titles.",
				"form_multi_select_input-titles", resource);

	}

	/**
	 * Test for a full completed select input.
	 */
	@Test
	public void full() {

		MultilectInput input = new MultilectInput("input.field");
		input.setOptions(Arrays.asList(new Option("Red", "RED"), new Option("Blue", "BLUE")));
		input.setId("aSelect");
		
		String resource = this.serialize(input);

		AssertSerialization.assertEquals("Unexpected serialization of a form input with id.", "form_multi_select_input-full",
				resource);
	}

	protected abstract String serialize(MultilectInput input);
}
