package com.github.sailarize.form;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.github.sailarize.resource.SailResource;
import com.github.sailarize.url.Filter;
import com.github.sailarize.url.QueryString;
import com.github.sailarize.url.UrlBuilder;
import com.github.sailarize.utils.ToStringBuilder;

/**
 * Builder for {@link Form}.
 * 
 * @author agusmunioz
 * 
 */
public class FormBuilder {

    public static final String POST = "POST";

    public static final String PATCH = "PATCH";

    private static final String DELETE = "DELETE";

    private static final String GET = "GET";

    private static final String UPDATE_FORM = "update";

    private static final String CREATE_FORM = "create";

    private static final String DELETE_FORM = "delete";

    private static final String GET_FORM = "get";

    private String action;

    private String id;

    private String method;

    private String title;

    private Collection<FormInput> inputs;

    private QueryString query;

    private String dateMask = "yyyy-MM-dd";

    private Map<String, String> headers;

    /**
     * Creates an initialized {@link FormBuilder}.
     * 
     * @param url
     *            the form URL.
     * 
     * @param id
     *            the form id.
     * 
     * @param method
     *            the HTTP method.
     */
    private FormBuilder(String url, String id, String method) {

        this.action = url;
        this.id = id;
        this.method = method;
        this.inputs = new LinkedList<FormInput>();
    }

    /**
     * Creates a builder for a form that creates a type resource.
     * 
     * @param resourceClass
     *            the type of resource to be created with the form.
     * 
     * @param values
     *            a list of values to replace in the URL if it is a template.
     * 
     * @return the builder for building the form.
     */
    public static FormBuilder create(
            Class<? extends SailResource> resourceClass, Object... values) {

        return new FormBuilder(UrlBuilder.url(resourceClass, values),
                CREATE_FORM, POST);

    }

    /**
     * Creates a builder for a form that updates a specific resource.
     * 
     * @param resource
     *            the specific resource the form will update.
     * 
     * @param values
     *            a list of values to replace in the URL if it is a template.
     * 
     * @return the builder for further building.
     */
    public static FormBuilder update(SailResource resource, Object... values) {

        return new FormBuilder(UrlBuilder.url(resource, values), UPDATE_FORM,
                PATCH);

    }

    /**
     * Creates a builder for a form that deletes a specific resource.
     * 
     * @param resource
     *            the resource to be deleted.
     * 
     * @param values
     *            a list of values to replace in the URL if it is a template.
     * 
     * @return the builder for further building.
     */
    public static FormBuilder delete(SailResource resource, Object... values) {

        return new FormBuilder(UrlBuilder.url(resource, values), DELETE_FORM,
                DELETE);

    }

    /**
     * Creates a builder for a form that performs a get of a list of resources.
     * 
     * @param resourceClass
     *            the resource upon a GET is performed.
     * 
     * @param values
     *            a list of values to replace in the URL if it is a template.
     * 
     * @return the builder for further building.
     */
    public static FormBuilder get(Class<? extends SailResource> resourceClass,
            Object... values) {

        return new FormBuilder(UrlBuilder.url(resourceClass, values), GET_FORM,
                GET);

    }

    /**
     * Creates a builder for a form that performs a GET.
     * 
     * @param resource
     *            the resource upon the GET is performed.
     * 
     * @param values
     *            a list of values to replace in the URL if it is a template.
     * 
     * @return the builder for further building.
     */
    public static FormBuilder get(SailResource resource, Object... values) {

        return new FormBuilder(UrlBuilder.url(resource, values), GET_FORM, GET);
    }

    /**
     * Changes the original form id.
     * 
     * @param id
     *            an id.
     * 
     * @return the builder for further building.
     */
    public FormBuilder id(String id) {

        this.id = id;
        return this;
    }

    /**
     * Changes the original action URL of the form.
     * 
     * @param action
     *            an URL.
     * 
     * @return the builder for further building.
     */
    public FormBuilder action(String action) {

        this.action = action;
        return this;
    }

    /**
     * Changes the default HTTP method of the form.
     * 
     * @param method
     *            an HTTP method.
     * 
     * @return the builder for further building.
     */
    public FormBuilder method(String method) {

        this.method = method;
        return this;
    }

    /**
     * Configures the form title.
     * 
     * @param title
     *            a user friendly text.
     * 
     * @return the builder for further building.
     */
    public FormBuilder title(String title) {

        this.title = title;
        return this;
    }

    /**
     * Adds a number input in the form.
     * 
     * @param name
     *            the input name.
     * 
     * @return the builder for further building.
     */
    public FormBuilder number(String name) {

        FormInput input = new FormInput(name);
        this.inputs.add(input);
        return this;
    }

    /**
     * Adds a number input in the form with a specific value pre-set.
     * 
     * @param name
     *            the input name.
     * 
     * @param value
     *            the input value.
     * 
     * @return the builder for further building.
     */
    public FormBuilder number(String name, Number value) {

        FormInput input = new FormInput(name);
        input.setValue(value);
        this.inputs.add(input);
        return this;
    }

    /**
     * Adds a text input in the form with a specific value pre-set.
     * 
     * @param name
     *            the input name.
     * 
     * @param value
     *            the input value.
     * 
     * @return the builder for further building.
     */
    public FormBuilder text(String name, String value) {

        FormInput input = new FormInput(name);
        input.setValue(value);
        this.inputs.add(input);
        return this;
    }

    /**
     * Adds a text input in the form.
     * 
     * @param name
     *            the input name.
     * 
     * @return the builder for further building.
     */
    public FormBuilder text(String name) {

        FormInput input = new FormInput(name);
        this.inputs.add(input);
        return this;
    }

    /**
     * Configures the date mask for date inputs. Default value yyyy-MM-dd
     * 
     * @param mask
     *            the mask.
     * @return the builder for further building.
     */
    public FormBuilder dateMask(String mask) {

        this.dateMask = mask;
        return this;
    }

    /**
     * Adds a date input.
     * 
     * @param name
     *            the input name.
     * 
     * @return the builder for further building.
     */
    public FormBuilder date(String name) {

        FormInput input = new FormInput(name);
        input.setMask(this.dateMask);
        this.inputs.add(input);
        return this;
    }

    /**
     * Adds a date input with a specific value.
     * 
     * @param name
     *            the input name.
     * 
     * @return the builder for further building.
     */
    public FormBuilder date(String name, Date value) {

        FormInput input = new FormInput(name);
        input.setValue(new SimpleDateFormat(this.dateMask).format(value));
        input.setMask(this.dateMask);
        this.inputs.add(input);
        return this;
    }

    /**
     * Adds an input for a comma separated list of value in one input.
     * 
     * @param name
     *            the input name.
     * 
     * @return the builder for further building.
     */
    public FormBuilder list(String name) {

        FormInput input = new FormInput(name);
        input.setValue(new String[] {});
        this.inputs.add(input);
        return this;
    }

    /**
     * Sets the form inputs. It overrides any already configured input.
     * 
     * @param inputs
     *            the list of inputs.
     * 
     * @return the builder for further building.
     */
    public FormBuilder inputs(Collection<FormInput> inputs) {

        this.inputs = inputs;

        return this;
    }

    /**
     * Adds a filter to all facet links.
     * 
     * @param name
     *            the filter name.
     * 
     * @param value
     *            the filter value.
     * 
     * @return the builder for further build.
     */
    public FormBuilder filter(String name, Object value) {

        if (this.query == null) {
            this.query = new QueryString();
        }

        this.query.add(name, value.toString());

        return this;
    }

    /**
     * Adds a list of filters to the form action.
     * 
     * @param filters
     *            the filters.
     * 
     * @return the builder for further build.
     */
    public FormBuilder filters(Collection<Filter> filters) {

        for (Filter filter : filters) {
            this.filter(filter.getName(), filter.getValue());
        }

        return this;
    }

    /**
     * Configures a header to be added to the form.
     * 
     * @param name
     *            the header's name.
     * 
     * @param value
     *            the header's value.
     * 
     * @return the builder for further build.
     */
    public FormBuilder header(String name, Object value) {

        if (this.headers == null) {
            this.headers = new HashMap<String, String>();
        }

        this.headers.put(name, value.toString());

        return this;
    }

    public FormBuilder noHeaders() {

        if (this.headers == null) {
            this.headers = new HashMap<String, String>();
        } else {
            this.headers.clear();
        }

        return this;
    }

    /**
     * Builds the form with what it was configured previously
     * 
     * @return the form.
     */
    public Form build() {

        StringBuilder action = new StringBuilder(this.action);

        if (this.query != null) {
            action.append(query);
        }

        Form form = new Form();
        form.setId(this.id);
        form.setAction(action.toString());
        form.setMethod(this.method);
        form.setTitle(this.title);
        form.setInputs(this.inputs);
        form.setHeaders(this.headers);

        return form;
    }

    @Override
    public String toString() {

        return ToStringBuilder.toString(this);
    }

}
