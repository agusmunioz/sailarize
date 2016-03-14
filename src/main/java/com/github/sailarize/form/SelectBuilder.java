package com.github.sailarize.form;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;

import com.github.sailarize.properties.Titles;

/**
 * Facilitates the build of {@link SelectInput} and {@link Option}.
 * 
 * @author agusmunioz
 *
 */
public class SelectBuilder {

	private static final String TITLE_KEY_PREFIX = "select.";

	private String name;

	private Collection<Option> options;

	private Locale locale;

	private SelectBuilder(String name) {
		this.name = name;
		this.options = new LinkedList<Option>();
	}

	/**
	 * Creates and initializes a {@link SelectBuilder}.
	 * 
	 * @param name
	 *            the select name.
	 * 
	 * @return the builder.
	 */
	public static SelectBuilder select(String name) {

		return new SelectBuilder(name);
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

		return this.option(new Option(this.title(title), value));
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

			StringBuilder key = new StringBuilder(TITLE_KEY_PREFIX).append(this.name).append(".").append(value);

			this.option(new Option(this.title(key.toString()), value));
		}

		return this;
	}

	/**
	 * Gets the title form properties file.
	 * 
	 * @param key
	 *            the title key.
	 * 
	 * @return the title or the key if no title is found.
	 */
	private String title(String key) {

		String title = (locale == null) ? Titles.get(key) : Titles.get(key, locale);

		return (title == null) ? key : title;
	}

	public SelectInput build() {

		SelectInput select = new SelectInput(this.name);
		select.setOptions(this.options);

		return select;
	}

}
