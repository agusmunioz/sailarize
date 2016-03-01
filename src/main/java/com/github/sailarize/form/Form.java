package com.github.sailarize.form;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.github.sailarize.utils.ToStringBuilder;

/**
 * Models a Hypermedia form for interacting with the API.
 * 
 * @author agusmunioz
 * 
 */
public class Form {

    private String id;

    private String action;

    private String method;

    private String title;

    private Map<String, String> headers;

    private Collection<FormInput> inputs;

    /**
     * The form id.
     * 
     * @return an id.
     */
    public String getId() {

        return id;
    }

    /**
     * Sets the form id.
     * 
     * @param id
     *            a unique id.
     */
    public void setId(String id) {

        this.id = id;
    }

    /**
     * The form action URL where it will be submitted.
     * 
     * @return the action URL.
     */
    public String getAction() {

        return action;
    }

    /**
     * Sets the form action URL where it will be submitted.
     * 
     * @param action
     *            an URL.
     */
    public void setAction(String action) {

        this.action = action;
    }

    /**
     * Gets the HTTP method of the form.
     * 
     * @return the HTTP method.
     */
    public String getMethod() {

        return method;
    }

    /**
     * Sets the form HTPP method.
     * 
     * @param method
     *            the HTTP method.
     */
    public void setMethod(String method) {

        this.method = method;
    }

    /**
     * Gets the form title.
     * 
     * @return a user friendly text explaining the form.
     */
    public String getTitle() {

        return title;
    }

    /**
     * Sets the form title.
     * 
     * @param title
     *            a user friendly text for titeling the form.
     */
    public void setTitle(String title) {

        this.title = title;
    }

    /**
     * Gets the list of headers that must be used when submitting the form.
     * 
     * @return the headers or null if no header was added.
     */
    public Map<String, String> getHeaders() {

        return headers;
    }

    /**
     * Sets the list of headers that must be used when submitting the form.
     * 
     * @param headers
     *            a map where the keys are the headers names and the values is
     *            the headers values
     */
    public void setHeaders(Map<String, String> headers) {

        this.headers = headers;
    }

    /**
     * Adds a header to the form in order to be used when submitting the form.
     * 
     * @param name
     *            the header's name.
     * 
     * @param value
     *            the header's value.
     */
    public void addHeader(String name, Object value) {

        if (this.headers == null) {
            this.headers = new HashMap<String, String>();
        }

        this.headers.put(name, value.toString());
    }

    /**
     * Gets the list of inputs the form requires.
     * 
     * @return the list of inputs.
     */
    public Collection<FormInput> getInputs() {

        return inputs;
    }

    /**
     * Adds an input to the form.
     * 
     * @param input
     *            an input.
     * 
     * @return the form for further configuration.
     */
    public Form add(FormInput input) {

        if (this.inputs == null) {
            this.inputs = new LinkedList<FormInput>();
        }

        this.inputs.add(input);

        return this;
    }

    /**
     * Sets the list of form inputs.
     * 
     * @param inputs
     *            the inputs.
     */
    public void setInputs(Collection<FormInput> inputs) {

        this.inputs = inputs;
    }

    @Override
    public String toString() {

        return ToStringBuilder.toString(this);
    }
}
