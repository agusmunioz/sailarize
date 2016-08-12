package com.github.sailarize.meta;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.github.sailarize.resource.SailTags;
import com.github.sailarize.resource.SailResource;

public class HeaderBuilder {

    private Map<String, String> headers;

    /**
     * Sets the resource headers in section #meta.
     * 
     * @param headers
     *            the headers.
     * 
     * @return this builder for further build.
     */
    public HeaderBuilder add(Map<String, String> headers) {

        for (Entry<String, String> header : headers.entrySet()) {
            this.add(header.getKey(), header.getValue());
        }

        return this;
    }

    /**
     * Adds a header in #meta section. If the header was already added, it is
     * override.
     * 
     * @param name
     *            the header name.
     * 
     * @param value
     *            the header value.
     * 
     * @return this builder for further build.
     */
    public HeaderBuilder add(String name, Object value) {

        if (this.headers == null) {
            this.headers = new HashMap<String, String>();
        }

        this.headers.put(name, value.toString());

        return this;
    }

    /**
     * Builds the header section in the sail resource.
     * 
     * @param resource
     *            the sail resource.
     */
    public void build(SailResource resource) {

        resource.meta(SailTags.HEADERS, this.headers);
    }
}
