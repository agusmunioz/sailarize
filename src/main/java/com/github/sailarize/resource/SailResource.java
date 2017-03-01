package com.github.sailarize.resource;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import com.github.sailarize.form.Form;
import com.github.sailarize.link.HypermediaLink;
import com.github.sailarize.link.LinkBuilder;
import com.github.sailarize.media.Image;
import com.github.sailarize.media.Video;
import com.github.sailarize.utils.ToStringBuilder;

/**
 * Models a Sail Resource.
 * 
 * @author agusmunioz
 * 
 */
public abstract class SailResource {

    private static final String SELF = "self";

    private String id;

    private Map<String, Object> meta;

    private Map<String, Collection<HypermediaLink>> links;

    private Map<String, Collection<Form>> forms;

    private Map<String, Collection<Image>> images;

    private Map<String, Collection<Video>> videos;

    /**
     * Creates a {@link SailResource}.
     * 
     */
    protected SailResource() {

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
    }

    /**
     * Adds a value in meta section.
     * 
     * @param field
     *            the field under the value is stored. It cannot use # as it is
     *            a reserved key.
     * @param value
     *            the value.
     */
    public void meta(String field, Object value) {

        if (this.meta == null) {
            this.meta = new LinkedHashMap<String, Object>();
        }

        this.meta.put(field, value);
    }

    /**
     * Configures the self link.
     * 
     * @param values
     *            if the URL is a template, this are the values for replacing.
     */
    public void self(Object... values) {
        this.add((new LinkBuilder(this, SELF, values)).build());
    }

    /**
     * Gets the meta information.
     * 
     * @return a not empty map or null if no meta is configured.
     */
    public Map<String, Object> getMeta() {
        return this.meta;
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

        if (link == null) {
            return;
        }
        if (this.links == null) {
            this.links = new LinkedHashMap<String, Collection<HypermediaLink>>();
        }

        this.group(this.links, link, SailTags.LINKS, groups);
    }

    /**
     * The links and their groups.
     * 
     * @return a not null map of links.
     */
    public final Map<String, Collection<HypermediaLink>> getLinks() {

        return this.links;
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

        if (form == null) {
            return;
        }
        if (this.forms == null) {
            this.forms = new LinkedHashMap<String, Collection<Form>>();
        }

        this.group(this.forms, form, SailTags.FORMS, groups);
    }

    /**
     * The forms under each group.
     * 
     * @return the map of forms or null if no form is added.
     */
    public final Map<String, Collection<Form>> getForms() {

        return this.forms;
    }

    /**
     * Adds an image to the resource.
     * 
     * @param image
     *            the image to link to.
     * 
     * @param groups
     *            the groups the iamge must be added to.
     */
    public void add(Image image, String... groups) {

        if (image == null) {
            return;
        }
        if (this.images == null) {
            this.images = new LinkedHashMap<String, Collection<Image>>();
        }

        this.group(this.images, image, SailTags.IMG, groups);
    }

    /**
     * Gets the images grouped.
     * 
     * @return the map of images or null if no image is added.
     */
    public final Map<String, Collection<Image>> getImages() {

        return images;
    }

    /**
     * Adds a video to the resource.
     * 
     * @param video
     *            the video to link to.
     * 
     * @param groups
     *            the groups the video must be added to.
     */
    public void add(Video video, String... groups) {

        if (video == null) {
            return;
        }
        if (this.videos == null) {
            this.videos = new LinkedHashMap<String, Collection<Video>>();
        }

        this.group(this.videos, video, SailTags.VIDEO, groups);
    }

    /**
     * Gets the videos grouped.
     * 
     * @return the map of videos or null if no image is added.
     */
    public Map<String, Collection<Video>> getVideos() {

        return videos;
    }

    /**
     * The resource id.
     * 
     * @return the id.
     */
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
     * Removes the links.
     */
    public void unlink() {
        this.links = null;
    }

    /**
     * Disables any Sail feature in order to return a plain JSON. Sets every
     * Hypermedia content in null so is not serialized.
     */
    public void unsail() {
        this.meta = null;
        this.links = null;
        this.forms = null;
        this.images = null;
        this.videos = null;
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
    private <T> void group(Map<String, Collection<T>> groups, T object, String prefix, String... names) {

        StringBuilder builder = new StringBuilder(prefix);

        for (String name : names) {
            builder.append(SailTags.KEY).append(name);
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
