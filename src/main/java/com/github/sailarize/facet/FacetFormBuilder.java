package com.github.sailarize.facet;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.github.sailarize.form.FormBuilder;
import com.github.sailarize.form.FormInput;
import com.github.sailarize.form.Template;
import com.github.sailarize.form.ValueInput;
import com.github.sailarize.link.LinkBuilder;
import com.github.sailarize.resource.SailResource;
import com.github.sailarize.servlet.RequestHolder;
import com.github.sailarize.url.Filter;
import com.github.sailarize.utils.Collector;

/**
 * Builder that creates forms for faceting a list of resources.
 * 
 * @author agusmunioz
 * 
 */
public class FacetFormBuilder {

	private String id;

	private Collection<Filter> filters;

	private Collection<Filter> applied;

	private Collection<FormInput> inputs;

	private String title;

	private FacetOption option;

	private String cleanTitle;

	private String cleanRel;

	public FacetFormBuilder(FacetOption option) {

		this.option = option;
		this.id = option.getFacet();
		this.inputs = new LinkedList<FormInput>();
		this.applied = new LinkedList<Filter>();
		this.filters = new LinkedList<Filter>();
		this.text(option.getFacet(), option.getValue());

		if (RequestHolder.get() != null) {
			this.filter(RequestHolder.get());
		}
	}

	/**
	 * Creates an initialized {@link FacetFormBuilder}.
	 * 
	 * @param option
	 *            the option a form will be created for.
	 * 
	 * @return the builder.
	 */
	public static FacetFormBuilder facet(FacetOption option) {

		return new FacetFormBuilder(option);
	}

	/**
	 * Adds a number input in the final form.
	 * 
	 * @param name
	 *            the input name.
	 * 
	 * @return the builder for further build.
	 */
	public FacetFormBuilder number(String name) {

		this.inputs.add(new ValueInput(name));
		return this;
	}

	/**
	 * Adds a text input in the final form.
	 * 
	 * @param name
	 *            the input name.
	 * 
	 * @return the builder for further build.
	 */
	public FacetFormBuilder text(String name) {

		this.inputs.add(new ValueInput(name));
		return this;
	}

	/**
	 * Adds a text input in the final form.
	 * 
	 * @param name
	 *            the input name.
	 * 
	 * @return the builder for further build.
	 */
	public FacetFormBuilder text(String name, Object value) {

		this.inputs.add(new ValueInput(name, value));
		return this;
	}

	/**
	 * Configures the form title.
	 * 
	 * @param title
	 *            a title.
	 * 
	 * @return the builder for further build.
	 */
	public FacetFormBuilder title(String title) {

		this.title = title;
		return this;
	}

	/**
	 * Configures an expression for building the clean links titles, if an
	 * option of the same facet is already applied.
	 * 
	 * @param expression
	 *            an expression that uses template variables.
	 * 
	 * @return the builder for further build.
	 */
	public FacetFormBuilder cleanTitle(String expression) {

		this.cleanTitle = expression;
		return this;
	}

	/**
	 * Configures an expression for building the clean links rel, if an option
	 * of the same facet is already applied.
	 * 
	 * @param expression
	 *            an expression that uses template variables.
	 * 
	 * @return the builder for further build.
	 */
	public FacetFormBuilder cleanRel(String expression) {

		this.cleanRel = expression;
		return this;
	}

	/**
	 * Adds a filter to all facet forms.
	 * 
	 * @param name
	 *            the filter name.
	 * 
	 * @param value
	 *            the filter value.
	 * 
	 * @return the builder for further build.
	 */
	public FacetFormBuilder filter(String name, Object value) {

		Filter filter = new Filter(name, value.toString());

		this.filters.add(filter);

		if (this.option.getFacet().equals(name)) {
			this.applied.add(filter);
		}

		return this;
	}

	/**
	 * Adds all the query parameters in the request as filters in all facet
	 * forms.
	 * 
	 * @param request
	 *            the HTTP request with the parameters.
	 * 
	 * @return the builder for further build.
	 */
	public FacetFormBuilder filter(HttpServletRequest request) {

		for (Entry<String, String[]> filter : request.getParameterMap().entrySet()) {

			for (String value : filter.getValue()) {
				this.filter(filter.getKey(), value);
			}

		}

		return this;
	}

	/**
	 * Adds the form to the resource. If any other option from the same facet is
	 * already applied, then a clean link for that option is also created.
	 * 
	 * @param resource
	 *            the resource where to add the form, and eventually, clean
	 *            links.
	 */
	public void build(SailResource resource) {

		resource.add(FormBuilder.get(resource).id(this.id).title(this.title).inputs(this.inputs)
				.filters(this.option.compatibles(this.filters)).build(), FacetBuilder.GROUP);

		Template template = Template.template(this.option.getValue());

		for (Filter clean : this.applied) {

			String rel = FacetBuilder.CLEAN + template.eval(this.cleanRel, clean.getValue().replace(" ", "_"));

			String title = template.eval(this.cleanTitle, clean.getValue());

			resource.add(
					new LinkBuilder(resource, rel).filters(Collector.reject(this.filters, clean)).title(title).build(),
					FacetBuilder.GROUP, this.option.getFacet());
		}

	}
}
