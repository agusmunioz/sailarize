package com.github.sailarize.meta;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.github.sailarize.resource.SailResource;
import com.github.sailarize.resource.SailTags;
import com.github.sailarize.servlet.RequestHolder;

/**
 * Builds the shortcut field in #meta section.
 *
 * @author agustin.munoz
 * 
 */
public class ShortcutBuilder {

	private static final String AND = "&";

	private static final String EQUALS = "=";

	private Set<String> exclude;

	public ShortcutBuilder() {

		this.exclude = new HashSet<String>();
	}

	/**
	 * Excludes query parameters in the shortcut.
	 * 
	 * @param exclude
	 *            the query parameter names to exclude.
	 * 
	 * @return this builder for further build.
	 */
	public ShortcutBuilder exclude(String... exclude) {

		for (String param : exclude) {
			this.exclude.add(param);
		}

		return this;
	}

	/**
	 * Builds the shortcut field in the resource.
	 * 
	 * @param resource
	 *            the sail resource.
	 */
	public void build(SailResource resource) {

		this.build(resource, RequestHolder.get());
	}

	/**
	 * Builds the shortcut field in the resource.
	 * 
	 * @param resource
	 *            the sail resource.
	 * 
	 * @param request
	 *            an HTTP request with the query parameters to include in the
	 *            shortcut.
	 */
	public void build(SailResource resource, HttpServletRequest request) {

		StringBuilder shortcut = new StringBuilder();

		for (Entry<String, String[]> filter : request.getParameterMap().entrySet()) {

			if (!this.exclude.contains(filter.getKey())) {

				for (String value : filter.getValue()) {
					shortcut.append(AND).append(filter.getKey()).append(EQUALS).append(value);
				}
			}

		}

		resource.meta(SailTags.SHORTCUT, shortcut.toString());
	}
}
