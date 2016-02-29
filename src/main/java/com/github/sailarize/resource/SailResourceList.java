package com.github.sailarize.resource;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.sailarize.link.HypermediaLink;
import com.github.sailarize.link.LinkBuilder;
import com.github.sailarize.mediatype.MediaTypeBuilder;
import com.github.sailarize.servlet.RequestHolder;

/**
 * Models a list of {@link SailResource}s.
 * 
 * @author agusmunioz.
 * 
 * @param <T>
 *            the type of resources in the list.
 */
public class SailResourceList<T extends SailResource> extends SailResource {

    private Class<T> resourceType;

    private Collection<T> resources;

    private String version;

    /**
     * Creates an initialized {@link SailResourceList}.
     * 
     * @param resourceType
     *            the type of resources in the list.
     * 
     * @param id
     *            the list id (optional).
     * 
     * @param request
     *            the HTTP request for managing navigation consistency in links.
     * 
     * @param values
     */
    protected SailResourceList(Class<T> resourceType, String id,
            HttpServletRequest request, Object... values) {

        super();
        this.resourceType = resourceType;
        this.resources = new LinkedList<T>();

        LinkBuilder builder = new LinkBuilder(this, "self", values);

        if (request != null) {

            for (Entry<String, String[]> parameter : request.getParameterMap()
                    .entrySet()) {
                for (String value : parameter.getValue()) {
                    builder.filter(parameter.getKey(), value);
                }
            }

        }

        this.add(builder.build());
    }

    /**
     * Builds a {@link SailResourceList} for an specified type of resources.
     * 
     * @param itemType
     *            the resource's type.
     * 
     * @param values
     * 
     * @return the list.
     * 
     */
    public static <T extends SailResource> SailResourceList<T> build(
            Class<T> itemType, Object... values) {

        return new SailResourceList<T>(itemType, null, RequestHolder.get(),
                values);
    }

    /**
     * Builds a {@link SailResourceList} for an specified type of resources.
     * 
     * @param itemType
     *            the resource's type.
     * 
     * @param request
     *            an HTTP request for maintaining the navigation consistency by
     *            incorporating any query parameter already applied.
     * 
     * @param values
     * 
     * @return the list.
     * 
     */
    public static <T extends SailResource> SailResourceList<T> build(
            Class<T> itemType, HttpServletRequest request, Object... values) {

        return new SailResourceList<T>(itemType, null, request, values);
    }

    /**
     * Builds a {@link SailResourceList} for an specified type of resources.
     * 
     * @param itemType
     *            the resource's type.
     * @param id
     *            the list id.
     * 
     * @return the list.
     */
    public static <T extends SailResource> SailResourceList<T> build(
            Class<T> itemType, String id, Object... values) {

        return new SailResourceList<T>(itemType, id, RequestHolder.get(),
                values);
    }

    /**
     * The list of resources.
     * 
     * @return the resources.
     */
    public Collection<T> getResources() {

        return resources;
    }

    /**
     * Sets the list of resources.
     * 
     * @param resources
     *            a collection of resources.
     */
    public void setResources(Collection<T> resources) {

        this.resources = resources;
    }

    /**
     * Adds an item in the list.
     * 
     * @param resource
     *            the resource to be added.
     */
    public void add(T resource) {

        this.resources.add(resource);
    }

    /**
     * The version of the list resource.
     * 
     * @return the version or null if not used.
     */
    @JsonIgnore
    public String getVersion() {

        return this.version;
    }

    /**
     * The version of the list resource.
     * 
     * @param version
     *            the version or null if not used.
     */
    public void setVersion(String version) {

        this.version = version;

        for (HypermediaLink link : this.getLinks()) {
            link.setType(MediaTypeBuilder.getType(this));
        }
    }

    @JsonIgnore
    public Class<T> getResourceType() {

        return resourceType;
    }

}
