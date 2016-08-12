package com.github.sailarize.properties;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import com.github.sailarize.properties.Titles;

/**
 * Unit test for {@link Titles}.
 * 
 * @author agusmunioz
 *
 */
public class TitlesTest {

	/**
	 * Test a title is obtained in a properties file with only the language.
	 */
	@Test
	public void language() {

		String title = Titles.get("facets.test.ONE", new Locale("es", "AR"));

		Assert.assertEquals("Titulo uno", title);
	}

	/**
	 * Test a title is obtain from the default properties file when no
	 * {@link Locale} is specified.
	 */
	@Test
	public void noLocale() {

		String title = Titles.get("facets.test.ONE");

		Assert.assertEquals("Default title", title);
	}

	/**
	 * Test a title is obtained from the default properties files when a not
	 * supported/not found language and country is provided.
	 */
	@Test
	public void defaulted() {

		String title = Titles.get("facets.test.ONE", new Locale("pt", "BR"));

		Assert.assertEquals("Default title", title);
	}

	/**
	 * Test a title is obtained in a properties file with the language and
	 * country.
	 */
	@Test
	public void languageAndCountry() {

		String title = Titles.get("facets.test.ONE", new Locale("en", "US"));

		Assert.assertEquals("Title one", title);
	}

	/**
	 * Test a title is obtained from the most specific properties file. That is
	 * to say, having an only language properties and a language_COUNTRY
	 * properties both with the same language, the title is obtained from the
	 * last one.
	 */
	@Test
	public void specific() {

		String title = Titles.get("facets.test.ONE", new Locale("fr", "FR"));

		Assert.assertEquals("France titre premier", title);
	}

	/**
	 * Test a title is obtained from the language default properties file when
	 * there is no properties file specific to the country.
	 */
	@Test
	public void unsuportedCountry() {

		String title = Titles.get("facets.test.ONE", new Locale("fr", "BL"));

		Assert.assertEquals("Titre premier", title);
	}

}
