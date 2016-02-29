package com.github.sailarize.page;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.github.sailarize.i18n.Titles;
import com.github.sailarize.link.LinkBuilder;
import com.github.sailarize.resource.SailResource;
import com.github.sailarize.servlet.RequestHolder;
import com.github.sailarize.url.Filter;

/**
 * Builds a list of pagination links for a list of resources.
 * 
 * @author agusmunioz
 * 
 */
public class PageBuilder {

	private Integer page;

	private Integer size;

	private Integer total;

	private String next;

	private String previous;

	private Integer[] shortcuts;

	private Collection<Filter> filters;

	private Locale locale;

	private PageBuilder(Integer page) {

		this.page = page;
		this.filters = new LinkedList<Filter>();

		if (RequestHolder.get() != null) {
			this.filter(RequestHolder.get());
		}
	}

	/**
	 * Creates a {@link PageBuilder} for a specific page.
	 * 
	 * @param page
	 *            the page number.
	 * 
	 * @return the builder.
	 */
	public static PageBuilder page(Integer page) {

		if (page == null || page.equals(new Integer(0))) {

			return new PageBuilder(1);
		}

		return new PageBuilder(page);
	}

	/**
	 * Configures the size of each page.
	 * 
	 * @param size
	 *            a number.
	 * 
	 * @return the builder for further build.
	 */
	public PageBuilder size(Integer size) {

		this.size = size;
		return this;
	}

	/**
	 * Configures the total amount of existent resources. It is used for
	 * determining if next or previous links must be created.
	 * 
	 * @param total
	 *            the total amount of resources.
	 * 
	 * @return the builder for further build.
	 */
	public PageBuilder total(Integer total) {

		this.total = total;
		return this;
	}

	/**
	 * Configures the title for the next page link.
	 * 
	 * @param title
	 *            the title.
	 * 
	 * @return the builder for further build.
	 */
	public PageBuilder next(String title) {

		this.next = title;
		return this;
	}

	/**
	 * Configures the title for the previous page link.
	 * 
	 * @param title
	 *            the title.
	 * 
	 * @return the builder for further build.
	 */
	public PageBuilder previous(String title) {

		this.previous = title;
		return this;
	}

	/**
	 * Configures pages shortcuts for creating links to specific pages.
	 * 
	 * @param pages
	 *            the pages number.
	 * 
	 * @return the builder for further build.
	 */
	public PageBuilder shortcuts(Integer... pages) {

		this.shortcuts = pages;
		return this;
	}

	/**
	 * Adds a filter (query parameter) to the pagination links.
	 * 
	 * @param name
	 *            the filter name.
	 * 
	 * @param value
	 *            the filter value.
	 * 
	 * @return the builder for further build.
	 */
	public PageBuilder filter(String name, Object value) {

		if (!PageConstants.PAGE_PARAM.equals(name) && !PageConstants.SIZE_PARAM.equals(name)) {
			this.filters.add(new Filter(name, value.toString()));
		}

		return this;
	}

	/**
	 * 
	 * Configures the Locale for obtaining all links titles form properties
	 * files.
	 * 
	 * @param locale
	 *            the locale.
	 * 
	 * @return the builder for further build.
	 */
	public PageBuilder locale(Locale locale) {
		this.locale = locale;
		return this;
	}

	/**
	 * Adds all filters (query parameters) to the pagination links.
	 * 
	 * @param reques
	 *            an http request containing a.
	 * 
	 * @param request
	 *            an http request with the parameters to apply as filters in all
	 *            pagination links.
	 * 
	 * @return the builder for further build.
	 */
	public PageBuilder filter(HttpServletRequest request) {

		for (Entry<String, String[]> filter : request.getParameterMap().entrySet()) {

			for (String value : filter.getValue()) {
				this.filter(filter.getKey(), value);
			}

		}

		return this;
	}

	/**
	 * Builds and adds all the pagination links in the resource list.
	 * 
	 * @param list
	 *            the resource where to add all the links.
	 */
	public void build(SailResource list) {

		if (this.page > 1) {

			LinkBuilder builder = new LinkBuilder(list, PageConstants.PREVIOUS_REL).title(this.getPrevious())
					.filter(PageConstants.PAGE_PARAM, Integer.toString(this.page - 1))
					.filter(PageConstants.SIZE_PARAM, this.size.toString()).filters(this.filters);

			list.add(builder.build(), PageConstants.GROUP);
		}

		if (this.page * this.size < this.total) {

			LinkBuilder builder = new LinkBuilder(list, PageConstants.NEXT_REL).title(this.getNext())
					.filter(PageConstants.PAGE_PARAM, Integer.toString(this.page + 1))
					.filter(PageConstants.SIZE_PARAM, this.size.toString()).filters(this.filters);

			list.add(builder.build(), PageConstants.GROUP);
		}

		if (this.shortcuts != null) {

			for (Integer page : this.shortcuts) {

				LinkBuilder builder = new LinkBuilder(list, this.shortcutRel(page)).title(this.shortcutTitle(page))
						.filter(PageConstants.PAGE_PARAM, page.toString())
						.filter(PageConstants.SIZE_PARAM, this.size.toString()).filters(this.filters);

				list.add(builder.build(), PageConstants.GROUP);
			}
		}

	}

	/**
	 * Gets the title of previous link. It uses the specified with
	 * {@link PageBuilder#previous(String)} or looking into properties files.
	 * 
	 * @return the next link title.
	 */
	private String getPrevious() {

		if (this.previous != null) {
			return this.next;
		}

		if (this.locale != null) {
			return Titles.get(PageConstants.PREVIOUS_TITLE, locale);
		}

		return Titles.get(PageConstants.PREVIOUS_TITLE);
	}

	/**
	 * Gets the title of next link. It uses the specified with
	 * {@link PageBuilder#next(String)} or looking into properties files.
	 * 
	 * @return the next link title.
	 */
	private String getNext() {

		if (this.next != null) {
			return this.next;
		}

		if (this.locale != null) {
			return Titles.get(PageConstants.NEXT_TITLE, locale);
		}

		return Titles.get(PageConstants.NEXT_TITLE);
	}

	/**
	 * Builds the shortcut link rel property.
	 * 
	 * @param page
	 *            the page number.
	 * 
	 * @return the link rel.
	 */
	protected String shortcutRel(Integer page) {

		return page.toString();
	}

	/**
	 * Builds the shortcut link title property.
	 * 
	 * @param page
	 *            the page number.
	 * 
	 * @return the link title.
	 */
	protected String shortcutTitle(Integer page) {

		return page.toString();
	}

}
