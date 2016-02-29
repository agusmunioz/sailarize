package com.github.sailarize.resource;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.sailarize.form.Form;
import com.github.sailarize.link.HypermediaLink;
import com.github.sailarize.link.LinkBuilder;
import com.github.sailarize.media.Image;
import com.github.sailarize.url.UrlBuilder;
import com.github.sailarize.utils.ToStringBuilder;

/**
 * Models a Sail Resource.
 * 
 * @author agusmunioz
 * 
 */
//@JsonSerialize(using=SailResourceSerializer.class)
public abstract class SailResource {

    private static final String SELF = "self";

    private String id;

    private Map<String, Collection<HypermediaLink>> links;

    private Map<String, Collection<Form>> forms;

    private Map<String, Object> meta;

    private Map<String, Object> extras;

    /**
     * Creates a {@link SailResource}.
     * 
     */
    protected SailResource() {

        this.links = new HashMap<String, Collection<HypermediaLink>>();

    }

    /**
     * Creates a {@link SailResource} which URL is a template.
     * 
     * @param id
     *            the resource id.
     * 
     * @param values
     *            if the resource's URL is a template, each variable is replaced
     *            with the a value.
     */
    public SailResource(String id, Object... values) {

        this();
        this.id = id;
        this.add((new LinkBuilder(this, SELF, values)).build());
    }

    /**
     * Adds a link under a group.
     * 
     * @param link
     *            the link.
     * 
     * @param groups
     *            the groups names. Cannot use the character '#' as it is a Sail
     *            reserved character.
     */
    public void add(HypermediaLink link, String... groups) {

        this.group(this.links, link, SailConstants.LINKS_KEY, groups);

    }

    /**
     * Adds a form under a group.
     * 
     * @param form
     *            the form.
     * 
     * @param groups
     *            the group names. Cannot use the character '#' as it is a Sail
     *            reserved character.
     */
    public void add(Form form, String... groups) {

        if (this.forms == null) {
            this.forms = new HashMap<String, Collection<Form>>();
        }

        this.group(this.forms, form, SailConstants.FORMS_KEY, groups);
    }

    /**
     * Adds an image link to the resource.
     * 
     * @param image
     *            the image to link to.
     * 
     * @param groups
     *            the groups the link must be added to.
     */
    public void add(Image image, String... groups) {

        this.group(this.links, image.getLink(), SailConstants.IMG_KEY, groups);
    }

    /**
     * The resource id.
     * 
     * @return the id.
     */
    @JsonInclude(Include.NON_NULL)
    public final String getId() {

        return id;
    }

    /**
     * Sets the resource id.
     * 
     * @param id
     */
    protected final void setId(String id) {

        this.id = id;
    }

    /**
     * The list of links under no group.
     * 
     * @return a not null list of links.
     */
    @JsonIgnore
    public final Collection<HypermediaLink> getLinks() {

        return this.links.get(SailConstants.LINKS_KEY);
    }

    /**
     * The list of forms under no group.
     * 
     * @return a not null list of forms.
     */
    @JsonIgnore
    public final Collection<Form> getForms() {

        return this.forms.get(SailConstants.FORMS_KEY);
    }

    /**
     * Configures the resource's path by overriding the default. The self link
     * is updated.
     * 
     * @param path
     *            a URL path that could contain variables.
     * 
     * @param values
     *            path variable values
     */
    public void overridePath(String path, Object... values) {

        for (HypermediaLink link : this.getLinks()) {

            if (SELF.equals(link.getRel())) {
                link.setHref(UrlBuilder.url(path, values));
                return;
            }
        }
    }

    /**
     * Adds a value in meta section.
     * 
     * @param field
     *            the field under the value is stored.
     * @param value
     *            the value.
     */
    public void meta(String field, Object value) {

        if (this.meta == null) {
            this.meta = new HashMap<String, Object>();
        }

        this.meta.put(field, value);
    }

    /**
     * Sets a property in the resource.
     * 
     * @param property
     *            the property name.
     * 
     * @param value
     *            the property value.
     */
    public void set(String property, Object value) {

        if (this.extras == null) {
            this.extras = new HashMap<String, Object>();
        }

        this.extras.put(property, value);
    }

    /**
     * Dynamics properties, grouped links, forms and meta.
     * 
     * @return a map with dynamic properties, groups names as keys and a list of
     *         forms or links as the value.
     */
    @JsonAnyGetter
    private Map<String, Object> getAny() {

        Map<String, Object> all = new HashMap<String, Object>(this.links);

        if (this.meta != null) {
            all.put(SailConstants.META, this.meta);
        }

        if (this.forms != null) {
            all.putAll(this.forms);
        }

        if (this.extras != null) {
            all.putAll(this.extras);
        }

        return all;
    }

    /**
     * Adds an object to a group.
     * 
     * @param object
     *            the object to group.
     * 
     * @param prefix
     *            the group prefix or starting name.
     * 
     * @param names
     *            the list of names for building the group key by concatenating
     *            the names with {@link SailResource#KEY}.
     */
    private <T> void group(Map<String, Collection<T>> groups, T object,
            String prefix, String... names) {

        StringBuilder builder = new StringBuilder(prefix);

        for (String name : names) {
            builder.append(SailConstants.KEY).append(name);
        }

        String key = builder.toString();

        if (!groups.containsKey(key)) {
            groups.put(key, new LinkedList<T>());
        }

        groups.get(key).add(object);
    }

    @Override
    public String toString() {

        return ToStringBuilder.toString(this);
    }

}
