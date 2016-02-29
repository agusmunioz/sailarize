package com.github.sailarize.url;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.sailarize.resource.Path;
import com.github.sailarize.resource.SailResource;
import com.github.sailarize.resource.SailResourceList;
import com.github.sailarize.utils.Annotations;

/**
 * Builder for resources URL.
 * 
 * @author agustin.munoz
 * 
 */
public class UrlBuilder {

    private static final String SLASH = "/";

    /**
     * Pattern for matching variables in the template.
     */
    private static Pattern PATH_VARIABLE_PATTERN = Pattern
            .compile("\\{\\w+\\}");

    /**
     * Builds a URL by appending the host and replacing any variable in the
     * path.
     * 
     * @param path
     *            a path that could have variables.
     * 
     * @param values
     *            the path variables values.
     * 
     * @return the URL.
     */
    public static String url(String path, Object... values) {

        if (values.length > 0) {

            Matcher matcher = PATH_VARIABLE_PATTERN.matcher(path);

            int index = 0;

            while (matcher.find() && index < values.length) {

                String variable = matcher.group();

                path = path.replace(variable, values[index++].toString());

            }
        }

        return PathHolder.get() + path;
    }

    /**
     * Creates the URL of a list of resources. E.g /users
     * 
     * @param resourceClass
     *            the resources class.
     * 
     * @param values
     *            Path variable values if the resource's Path is a template.
     * 
     * @return the URL as {@link String}
     */
    public static String url(Class<? extends SailResource> resourceClass,
            Object... values) {

        Path path = Annotations.search(resourceClass, Path.class);

        if (path == null || path.value().isEmpty()) {

            StringBuilder builder = new StringBuilder("The resource class ")
                    .append(resourceClass.getName())
                    .append(" is not annotated with @")
                    .append(Path.class.getName())
                    .append(" or the Path value is empty");

            throw new UrlBuildException(builder.toString());
        }

        return url(path.value(), values);

    }

    /**
     * Builds a specific resource URL.
     * 
     * @param resource
     *            a specific resource.
     * 
     * @param values
     *            Path variable values if the resource's Path is a template.
     * 
     * @return the URL as {@link String}
     */
    public static String url(SailResource resource, Object... values) {

        Class<? extends SailResource> type = resource.getClass();

        if (SailResourceList.class.isAssignableFrom(type)) {
            type = ((SailResourceList<?>) resource).getResourceType();
        }

        StringBuilder builder = new StringBuilder(url(type, values));

        if (resource.getId() != null) {
            builder.append(SLASH).append(resource.getId());
        }

        return builder.toString();

    }

    /**
     * Builds a URL representing a relationship between a resource and a list o
     * resources. E.g /users/1/phones
     * 
     * @param resource
     *            the resource owner of the relationship.
     * 
     * @param targetResource
     *            the class of resources related to the resource owner of the
     *            relationship.
     * 
     * @return a URL as {@link String}
     */
    public static String url(SailResource resource,
            Class<? extends SailResource> targetResource) {

        StringBuilder builder = new StringBuilder(url(resource));

        builder.append(url(targetResource).replace(PathHolder.get(), ""));

        return builder.toString();

    }

    /**
     * Builds a URL representing a relationship between two resources. E.g
     * /users/1/cars/2
     * 
     * @param resource
     *            the resource owner of the relationship.
     * 
     * @param targetResource
     *            the resource at the other side of the relationship.
     * 
     * @return a URL as {@link String}
     */
    public static String url(SailResource resource, SailResource targetResource) {

        StringBuilder builder = new StringBuilder(url(resource));

        builder.append(url(targetResource).replace(PathHolder.get(), ""));

        return builder.toString();

    }

    /**
     * Sets the host to the URL. In case the URL already has a host, it changes.
     * 
     * @param url
     *            a URL or path as string.
     * 
     * @param host
     *            the new host.
     * 
     * @return an URL with the new host.
     */
    public static String host(String url, String host) {

        try {

            URL wrapper = new URL(url);

            String replace = wrapper.getHost();

            if (wrapper.getPort() != -1) {
                replace += ":" + wrapper.getPort();
            }

            return url.replaceFirst(replace, host);

        } catch (MalformedURLException e) {

            return host + url;
        }
    }

    /**
     * Sets the protocol to the URL. In case the URL already has a protocol, it
     * changes.
     * 
     * @param url
     *            a url as string.
     * 
     * @param protocol
     *            the new host.
     * 
     * @return an URL with the new protocol or an empty string if the url is not
     *         valid.
     */
    public static String protocol(String url, String protocol) {

        try {

            return url.replaceFirst(new URL(url).getProtocol(), protocol);

        } catch (MalformedURLException e) {

            return "";
        }
    }

}
