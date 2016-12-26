package com.github.sailarize.form;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;

import com.github.sailarize.properties.Titles;

/**
 * Builder for {@link CloneSelectInput}.
 * 
 * @author agusmunioz
 *
 */
public class CloneSelectBuilder {

	private String name;

	private Integer min;

	private Integer max;

	private Integer start;

	private Collection<Option> options;

	private Locale locale;

	private CloneSelectBuilder(String name) {
		this.name = name;
		this.options = new LinkedList<Option>();
	}

	/**
	 * Creates an initialized {@link CloneSelectBuilder}.
	 * 
	 * @param name
	 *            the clone select name.
	 * 
	 * @return the builder.
	 */
	public static CloneSelectBuilder name(String name) {
		return new CloneSelectBuilder(name);
	}

	/**
	 * Configures the min property of the clone select.
	 */
	public CloneSelectBuilder min(Integer min) {
		this.min = min;
		return this;
	}

	/**
	 * Configures the max property of the clone select.
	 */
	public CloneSelectBuilder max(Integer max) {
		this.max = max;
		return this;
	}

	/**
	 * Configures the start property of the clone select.
	 */
	public CloneSelectBuilder start(Integer start) {
		this.start = start;
		return this;
	}

	/**
	 * Adds an option to the select.
	 * 
	 * @param option
	 *            the option.
	 * 
	 * @return the builder.
	 */
	public CloneSelectBuilder option(Option option) {

		this.options.add(option);

		return this;
	}

	/**
	 * Adds an option to the clone select.
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
	public CloneSelectBuilder option(String value, String title) {

		String i18n = this.i18nTitle(title);

		return this.option(new Option(i18n == null ? title : i18n, value));
	}

	/**
	 * Sets the locale for searching for options titles.
	 * 
	 * @param locale
	 *            the targeted locale.
	 * 
	 * @return the builder.
	 */
	public CloneSelectBuilder locale(Locale locale) {
		this.locale = locale;
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
	private String i18nTitle(String key) {

		return (this.locale == null) ? Titles.get(key) : Titles.get(key, this.locale);

	}

	/**
	 * Builds the clone select with whatever is configured at the moment.
	 */
	public CloneSelectInput build() {

		CloneSelectInput select = new CloneSelectInput(this.name);
		select.setMin(this.min);
		select.setMax(this.max);
		select.setStart(this.start);
		select.setOptions(this.options);

		return select;
	}
}
