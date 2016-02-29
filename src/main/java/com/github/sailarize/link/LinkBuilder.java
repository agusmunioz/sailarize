package com.github.sailarize.link;

import java.util.Collection;

import com.github.sailarize.mediatype.MediaTypeBuilder;
import com.github.sailarize.resource.SailResource;
import com.github.sailarize.url.Filter;
import com.github.sailarize.url.QueryString;
import com.github.sailarize.url.UrlBuilder;

/**
 * Builder for facilitating the build of a {@link HypermediaLink}.
 * 
 * @author agusmunioz
 * 
 */
public class LinkBuilder {

    private HypermediaLink link;

    private QueryString query;

    /**
     * Creates and initializes a {@link LinkBuilder}.
     * 
     * @param href
     *            the link href field.
     * @param rel
     *            the lihk rel field.
     */
    public LinkBuilder(String href, String rel) {

        this.link = new HypermediaLink();
        this.link.setHref(href);
        this.link.setRel(rel);
    }

    /**
     * Creates and initializes a {@link LinkBuilder} for building a link to a
     * list of resources. <br>
     * E.g:<br>
     * /users <br>
     * /resources </br>
     * 
     * @param resourceType
     *            the resources type to link to.
     * 
     * @param rel
     *            the link's rel field value.
     * 
     * @param values
     *            any Path value to replace if the resource's Path is a
     *            template.
     */
    public LinkBuilder(Class<? extends SailResource> resourceType, String rel,
            Object... values) {

        this(UrlBuilder.url(resourceType, values), rel);
        this.link.setType(MediaTypeBuilder.getType(resourceType));
    }

    /**
     * Creates an initializes a {@link LinkBuilder} for building a link to a
     * specific resource. <br>
     * E.g:<br>
     * /users/1 <br>
     * /resources/{id} </br>
     * 
     * @param resource
     *            the resource to link to.
     * 
     * @param rel
     *            the link's rel field value.
     * 
     * @param values
     *            any Path value to replace if the resource's Path is a
     *            template.
     */
    public LinkBuilder(SailResource resource, String rel, Object... values) {

        this(UrlBuilder.url(resource, values), rel);
        this.link.setType(MediaTypeBuilder.getType(resource));
    }

    /**
     * Creates and initializes a {@link LinkBuilder} for building a link
     * representing a relationship between a resource and a list o resources. <br>
     * E.g: <br>
     * /users/1/phones <br>
     * /resources/{id}/related-resources.
     * 
     * @param source
     *            the owner or source of the relationship.
     * 
     * @param destination
     *            the resources type related to the source.
     * 
     * @param rel
     *            the link's rel property.
     */
    public LinkBuilder(SailResource source,
            Class<? extends SailResource> destination, String rel) {

        this(UrlBuilder.url(source, destination), rel);
        this.link.setType(MediaTypeBuilder.getType(destination));
    }

    /**
     * Creates and initializes a {@link LinkBuilder} for building a link
     * representing a relationship from a specific resource to another specific
     * resource. <br>
     * E.g: <br>
     * /users/1/jobs/2 <br>
     * /resources/{id}/related-resources/{related-id}
     * 
     * @param source
     *            the owner or source of the relationship.
     * 
     * @param destination
     *            the resource related to the source.
     * 
     * @param rel
     *            the link's rel field value.
     */
    public LinkBuilder(SailResource source, SailResource destination, String rel) {

        this(UrlBuilder.url(source, destination), rel);
        this.link.setType(MediaTypeBuilder.getType(destination));
    }

    /**
     * Creates an initialized {@link LinkBuilder} for building a link that
     * appends a fixed path to a resource URL. <br>
     * E.g: <br>
     * /users/1/some-path <br>
     * /resources/{id}/a-relation
     * 
     * @param resource
     *            the resource.
     * @param path
     *            the the path to append.
     * @param rel
     *            the link's rel field value.
     */
    public LinkBuilder(SailResource resource, String path, String rel) {

        this(UrlBuilder.url(resource, path), rel);
    }

    /**
     * Sets the link title.
     * 
     * @param title
     *            a text with the link title.
     * 
     * @return the {@link LinkBuilder} for continuing building.
     */
    public LinkBuilder title(String title) {

        this.link.setTitle(title);
        return this;
    }

    /**
     * Adds a filter (query parameter) to the link.
     * 
     * @param name
     *            the filter name.
     * 
     * @param value
     *            the filter value. Will be URL encoded.
     * 
     * @return the {@link LinkBuilder} for continuing building.
     */
    public LinkBuilder filter(String name, String value) {

        if (this.query == null) {
            this.query = new QueryString();
        }

        this.query.add(name, value);

        return this;
    }

    /**
     * Addas a list of filters (query parameters) to the link.
     * 
     * @param filters
     *            the list of filter.
     * 
     * @return the {@link LinkBuilder} for continuing building.
     */
    public LinkBuilder filters(Collection<Filter> filters) {

        for (Filter filter : filters) {
            this.filter(filter.getName(), filter.getValue());
        }

        return this;
    }

    /**
     * Sets the link type field.
     * 
     * @param type
     *            the link type information.
     * 
     * @return the {@link LinkBuilder} for continuing building.
     */
    public LinkBuilder type(String type) {

        this.link.setType(type);
        return this;
    }

    /**
     * Sets the link host. If the link already have a host, it changes the
     * original.
     * 
     * @param host
     *            the link host
     * 
     * @return the {@link LinkBuilder} for continuing building.
     */
    public LinkBuilder host(String host) {

        this.link.setHref(UrlBuilder.host(link.getHref(), host));
        return this;
    }

    /**
     * Changes the URL protocol to HTTPS.
     * 
     * 
     * @return the {@link LinkBuilder} for continuing building.
     */
    public LinkBuilder https() {

        this.link.setHref(UrlBuilder.protocol(link.getHref(), "https"));
        return this;
    }

    /**
     * Changes the URL protocol to HTTP.
     * 
     * 
     * @return the {@link LinkBuilder} for continuing building.
     */
    public LinkBuilder http() {

        this.link.setHref(UrlBuilder.protocol(link.getHref(), "http"));
        return this;
    }

    /**
     * Adds an extra field prefixed with data- to the link.
     * 
     * @param name
     *            the data field name.
     * 
     * @param value
     *            the data field value.
     * 
     * @return the {@link LinkBuilder} for continuing building.
     */
    public LinkBuilder data(String name, String value) {

        this.link.addData(name, value);
        return this;
    }

    /**
     * Builds the configured link.
     * 
     * @return a configured {@link HypermediaLink}.
     */
    public HypermediaLink build() {

        if (this.query != null) {
            this.link.setHref(this.link.getHref() + this.query);
        }

        return this.link;
    }

}