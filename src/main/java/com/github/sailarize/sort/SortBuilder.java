package com.github.sailarize.sort;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.github.sailarize.link.LinkBuilder;
import com.github.sailarize.link.RelBuilder;
import com.github.sailarize.properties.Titles;
import com.github.sailarize.resource.SailResource;
import com.github.sailarize.servlet.RequestHolder;
import com.github.sailarize.url.Filter;
import com.github.sailarize.utils.ToStringBuilder;

/**
 * Builds links for sorting resources.
 * 
 * @author agusmunioz
 * 
 */
public class SortBuilder {

	private Collection<SortOption> options;

	private Collection<Filter> filters;

	private String[] titles;

	private Locale locale;

	/**
	 * Creates an initialized {@link SortBuilder}. It uses {@link RequestHolder}
	 * for navigation consistency in the links.
	 */
	private SortBuilder() {

		this.options = new LinkedList<SortOption>();
		this.filters = new LinkedList<Filter>();

		if (RequestHolder.get() != null) {
			this.filter(RequestHolder.get());
		}
	}

	/**
	 * Creates an initialized {@link SortBuilder}.
	 * 
	 * @param options
	 *            a list of sort options for building the links.
	 */
	private SortBuilder(Collection<SortOption> options) {
		this();
		this.options = options;
	}

	/**
	 * Creates a {@link SortBuilder} for working with the specified options.
	 * 
	 * @param options
	 *            the list of options.
	 * 
	 * @return the builder for further build.
	 */
	public static SortBuilder options(Collection<SortOption> options) {
		return new SortBuilder(options);
	}

	/**
	 * Creates a {@link SortBuilder} with an ascending and a descending option
	 * for each sort.
	 * 
	 * @param sorts
	 *            the sort values.
	 * 
	 * @return the builder for further build.
	 */
	public static SortBuilder bidirectionals(String... sorts) {

		SortBuilder builder = new SortBuilder();

		for (String sort : sorts) {
			builder.bidirectional(sort, null, null);
		}

		return builder;
	}

	/**
	 * Creates a {@link SortBuilder} with an ascending option for each sort.
	 * 
	 * @param sorts
	 *            the sort values.
	 * 
	 * @return the builder for further build.
	 */
	public static SortBuilder ascendings(String... sorts) {

		SortBuilder builder = new SortBuilder();

		for (String sort : sorts) {
			builder.ascending(sort, null);
		}

		return builder;
	}

	/**
	 * Creates a {@link SortBuilder} with a descending option for each sort.
	 * 
	 * @param sorts
	 *            the sort values.
	 * 
	 * @return the builder for further build.
	 */
	public static SortBuilder descendings(String... sorts) {

		SortBuilder builder = new SortBuilder();

		for (String sort : sorts) {
			builder.descending(sort, null);
		}

		return builder;
	}

	/**
	 * Adds an ascending sort option.
	 * 
	 * @param value
	 *            the sort option value.
	 * 
	 * @param title
	 *            the link title (not required).
	 * 
	 * @return the builder for further build.
	 */
	public SortBuilder ascending(String value, String title) {

		this.options.add(new SimpleSortOption(value, SortConstants.ASCENDING, title));

		return this;
	}

	/**
	 * Adds a descending sort option.
	 * 
	 * @param value
	 *            the sort option value.
	 * 
	 * @param title
	 *            the link title (not required).
	 * 
	 * @return the builder for further build.
	 */
	public SortBuilder descending(String value, String title) {

		this.options.add(new SimpleSortOption(value, SortConstants.DESCENDING, title));

		return this;
	}

	/**
	 * Adds an ascending and a descending sort options.
	 * 
	 * @param value
	 *            the sort option value.
	 * 
	 * @param ascending
	 *            the ascending link title (not required).
	 * 
	 * @param descending
	 *            the descending link title (not required).
	 * 
	 * @return the builder for further build.
	 */
	public SortBuilder bidirectional(String sort, String ascending, String descending) {

		this.ascending(sort, ascending);

		this.descending(sort, descending);

		return this;
	}

	/**
	 * Sets the sort options titles. The order of titles will be matched with
	 * the order of options.
	 * 
	 * @param titles
	 *            the titles of all options.
	 * 
	 * @return the builder for further build.
	 */
	public SortBuilder titles(String... titles) {

		this.titles = titles;

		return this;
	}

	/**
	 * Adds a filter (query parameter) to all sort links.
	 * 
	 * @param name
	 *            the filter name.
	 * 
	 * @param value
	 *            the filter value.
	 * 
	 * @return the builder for further build.
	 */
	public SortBuilder filter(String name, Object value) {

		if (!SortConstants.SORT_BY.equals(name) && !SortConstants.SORT_DIRECTION.equals(name)) {
			this.filters.add(new Filter(name, value.toString()));
		}

		return this;
	}

	/**
	 * Adds all filters (query parameters) to all sort links.
	 * 
	 * 
	 * @param request
	 *            an HTTP request with the parameters to apply as filters in all
	 *            sort links.
	 * 
	 * @return the builder for further build.
	 */
	public SortBuilder filter(HttpServletRequest request) {

		for (Entry<String, String[]> filter : request.getParameterMap().entrySet()) {

			for (String value : filter.getValue()) {
				this.filter(filter.getKey(), value);
			}

		}

		return this;
	}

	/**
	 * Configures the Locale for obtaining the links titles from properties
	 * files.
	 * 
	 * @param locale
	 *            the locale.
	 * 
	 * @return the builder for further build.
	 */
	public SortBuilder locale(Locale locale) {
		this.locale = locale;
		return this;
	}

	/**
	 * Builds the sort links and adds it to the list resource.
	 * 
	 * @param list
	 *            a resource modeling a list or resources.
	 * 
	 * @param values
	 *            any value used to replace in the list url if a template is
	 *            used.
	 */
	public void build(SailResource list, Object... values) {

		int index = 0;

		for (SortOption option : this.options) {

			LinkBuilder builder = new LinkBuilder(list,
					RelBuilder.rel(SortConstants.REL, option.getValue(), option.getDirection()), values)
							.filters(this.filters).filter(SortConstants.SORT_BY, option.getValue())
							.filter(SortConstants.SORT_DIRECTION, option.getDirection())
							.title(this.getTitle(option, index));

			list.add(builder.build(), SortConstants.GROUP);

			index++;
		}

	}

	/**
	 * Gets the sort link title. It tries to get it from the option, the
	 * properties file or the list of {@link SortBuilder#titles(String...)}.
	 * 
	 * @param option
	 *            the sort option a title is needed.
	 *
	 * @param position
	 *            the position titles in {@link SortBuilder#titles(String...)}.
	 *
	 * @return the title or null if cannot be resolved.
	 */
	private String getTitle(SortOption option, int position) {

		if (option.getTitle() != null) {
			return option.getTitle();
		}

		if (this.titles != null && position < this.titles.length) {

			return this.titles[position];
		}

		String key = String.format(SortConstants.TITLE_KEY, option.getValue(), option.getDirection());

		if (this.locale != null) {
			return Titles.get(key, this.locale);
		}

		return Titles.get(key);
	}

	@Override
	public String toString() {

		return ToStringBuilder.toString(this);
	}
}
