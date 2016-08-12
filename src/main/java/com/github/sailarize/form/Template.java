package com.github.sailarize.form;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Models a Sail input template value.
 * 
 * @author agusmunioz
 * 
 */
public class Template {

	/**
	 * Java Regex character that must be escaped in the template to prevent bad
	 * regexs.
	 */
	private static Pattern ESCAPE = Pattern.compile("[()\\[\\].+*?^$\\\\|]");

	/**
	 * Regular expression for any character more than once.
	 */
	private static final String ANY_CHAR = "(.+)";

	/**
	 * Variable used in templates for printing the complete value.
	 */
	private static String VALUE_VAR = "{#value}";

	/**
	 * Pattern for matching variables in the template.
	 */
	private static Pattern VARIABLE_PATTERN = Pattern.compile("\\{#\\w+\\}");

	private Pattern pattern;

	private Map<String, Integer> groups = new HashMap<String, Integer>();

	private Map<String, String> variables = new HashMap<String, String>();

	/**
	 * Creates an initialized {@link Template}.
	 * 
	 * @param template
	 *            an expression this templates models.
	 */
	private Template(String template) {

		String regex = ESCAPE.matcher(template).replaceAll("\\\\$0");

		int group = 1;

		Matcher matcher = VARIABLE_PATTERN.matcher(template);

		while (matcher.find()) {

			String variable = matcher.group();

			regex = regex.replace(variable, ANY_CHAR);

			groups.put(variable, group++);

		}

		this.pattern = Pattern.compile(regex);
	}

	/**
	 * Creates an initialized {@link Template}.
	 * 
	 * @param template
	 *            a template that uses Sail template variables. Null value is
	 *            replaced with an empty String.
	 * 
	 * @return a {@link Template} ready for being used.
	 */
	public static Template template(String template) {

		if (template == null) {
			return new Template("");
		}

		return new Template(template);
	}

	/**
	 * Generates a String using the configured instance as the data source,
	 * following the pattern and the configured template for resolving variables
	 * values.
	 * 
	 * @param expression
	 *            an expression that optionally uses the template variables.
	 * 
	 * @param instance
	 *            a String that follows the template structure but with
	 *            variables replaced using specific values.
	 * 
	 * @return the result of evaluating the expression using the instance as
	 *         source, or the instance if the expression is null or empty.
	 */
	public String eval(String expression, String instance) {

		if (expression == null || expression.isEmpty()) {
			return instance;
		}

		Matcher matcher = this.pattern.matcher(instance);

		this.variables.put(VALUE_VAR, instance);

		if (matcher.matches()) {

			for (Entry<String, Integer> group : this.groups.entrySet()) {

				this.variables.put(group.getKey(),
						matcher.group(group.getValue()));
			}
		}

		String result = expression;

		for (Entry<String, String> variable : this.variables.entrySet()) {

			result = result.replace(variable.getKey(), variable.getValue());
		}

		return result;
	}
}
