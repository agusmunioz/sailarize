package com.github.sailarize.form;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;

import com.github.sailarize.asserts.AssertSerialization;

/**
 * Test that any serialization implementation must run (extend) in order to
 * ensure a valid serialization of a {@link CloneSelectInput}
 * 
 * @author agusmunioz
 *
 */
public abstract class CloneSelectInputSerializationTest {

	/**
	 * Test for a select input with minimum information.
	 */
	@Test
	public void base() {

		CloneSelectInput input = new CloneSelectInput("input.field");
		input.setOptions(Arrays.asList(new Option("RED"), new Option("BLUE")));

		String resource = this.serialize(input);

		AssertSerialization.assertEquals("Unexpected serialization of a select input with a list of options.",
				"form_clone_select_input-base", resource);

	}

	/**
	 * Test for a select input with titles in its options.
	 */
	@Test
	public void titles() {

		CloneSelectInput input = new CloneSelectInput("input.field");
		input.setOptions(Arrays.asList(new Option("Red", "RED"), new Option("Blue", "BLUE")));

		String resource = this.serialize(input);

		AssertSerialization.assertEquals("Unexpected serialization of a select input with value and titles.",
				"form_clone_select_input-titles", resource);

	}

	/**
	 * Test for a constrained (with max, min and start) select input.
	 */
	@Test
	public void constraints() {

		CloneSelectInput input = new CloneSelectInput("input.field");
		input.setOptions(Arrays.asList(new Option("RED"), new Option("BLUE"), new Option("GREEN")));
		input.setMin(0);
		input.setMax(5);
		input.setStart(2);

		String resource = this.serialize(input);

		AssertSerialization.assertEquals("Unexpected serialization of a form input with id.",
				"form_clone_select_input-constrain", resource);
	}

	/**
	 * Test for a select input with a selected option.
	 */
	@Test
	public void selected() {

		CloneSelectInput input = new CloneSelectInput("input.field");
		input.setOptions(Arrays.asList(new Option("RED"), new Option("BLUE")));

		Collection<Object> selected = new LinkedList<Object>();
		selected.add("RED");

		input.setSelected(selected);

		String resource = this.serialize(input);

		AssertSerialization.assertEquals("Unexpected serialization of a select input with a selected value.",
				"form_clone_select_input-selected", resource);

	}

	/**
	 * Test for a full completed select input.
	 */
	@Test
	public void full() {

		CloneSelectInput input = new CloneSelectInput("input.field");
		input.setOptions(
				Arrays.asList(new Option("Red", "RED"), new Option("Blue", "BLUE"), new Option("Green", "GREEN")));
		input.setId("aSelect");
		input.setTitle("Title");
		input.setMin(0);
		input.setMax(5);
		input.setStart(2);

		Collection<Object> selected = new LinkedList<Object>();
		selected.add("RED");
		selected.add("BLUE");

		input.setSelected(selected);

		String resource = this.serialize(input);

		AssertSerialization.assertEquals("Unexpected serialization of a form input with id.",
				"form_clone_select_input-full", resource);
	}

	protected abstract String serialize(CloneSelectInput input);
}
