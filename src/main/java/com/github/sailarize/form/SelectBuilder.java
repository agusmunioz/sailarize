package com.github.sailarize.form;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;

import com.github.sailarize.properties.Titles;
import com.sun.javafx.binding.StringFormatter;

/**
 * Facilitates the build of {@link SelectInput} and {@link Option}.
 * 
 * @author agusmunioz
 *
 */
public class SelectBuilder {

	private static final String TITLE_KEY = "select.%s.%s";

	private SelectInput input;

	private Collection<Option> options;

	private Locale locale;

	private SelectBuilder(SelectInput input) {
		this.input = input;
		this.options = new LinkedList<Option>();
	}

	/**
	 * Creates and initializes a {@link SelectBuilder} for building a
	 * {@link SelectInput} in Single mode.
	 * 
	 * @param name
	 *            the select name.
	 * 
	 * @return the builder.
	 */
	public static SelectBuilder single(String name) {

		return new SelectBuilder(new SingleSelectInput(name));
	}

	/**
	 * Creates and initializes a {@link SelectBuilder} for building a
	 * {@link SelectInput} in multiple mode.
	 * 
	 * @param name
	 *            the select name.
	 * 
	 * @return the builder.
	 */
	public static SelectBuilder multi(String name) {

		return new SelectBuilder(new MultiSelectInput(name));
	}

	/**
	 * Adds an option to the select.
	 * 
	 * @param value
	 *            the option value.
	 * 
	 * @param title
	 *            the option title or the key in /sail/titles*.properties where
	 *            to get the title.
	 * 
	 * @return the builder.
	 */
	public SelectBuilder option(String value, String title) {

		String i18n = this.title(title);

		return this.option(new Option(i18n == null ? title : i18n, value));
	}

	/**
	 * Adds an option to the select.
	 * 
	 * @param option
	 *            the option.
	 * 
	 * @return the builder.
	 */
	public SelectBuilder option(Option option) {

		this.options.add(option);

		return this;
	}

	/**
	 * Sets the locale for searching for options titles.
	 * 
	 * @param locale
	 *            the targeted locale.
	 * @return the builder.
	 */
	public SelectBuilder locale(Locale locale) {
		this.locale = locale;
		return this;
	}

	/**
	 * Adds a list of options to the select. It searches for titles in
	 * sail/titles*.properties files using the key select.{name}.{value}, if not
	 * found no title is set. It uses {@link SelectBuilder#locale(Locale)} if
	 * set, otherwise it uses default locale.
	 * 
	 * @param values
	 *            the select values.
	 * 
	 * @return the builder.
	 */
	public SelectBuilder options(String... values) {

		for (String value : values) {

			String key = StringFormatter.format(TITLE_KEY, this.input.getName(), value).get();

			this.option(new Option(this.title(key), value));
		}

		return this;
	}

	/**
	 * Gets the title form properties file.
	 * 
	 * @param key
	 *            the title key.
	 * 
	 * @return the title or null if not found.
	 */
	private String title(String key) {

		return (this.locale == null) ? Titles.get(key) : Titles.get(key, this.locale);

	}

	/**
	 * Builds the select input.
	 * 
	 * @return the input.
	 */
	public SelectInput build() {

		this.input.setOptions(this.options);

		return this.input;
	}

}
