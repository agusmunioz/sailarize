package com.github.sailarize.media;

import com.github.sailarize.link.HypermediaLink;
import com.github.sailarize.link.LinkBuilder;
import com.github.sailarize.utils.ToStringBuilder;

/**
 * Models the information of a video.
 * 
 * @author agusmunioz
 *
 */
public class Video {

    private String id;

    private String url;

    private String title;

    /**
     * Creates an initialized {@link Video}.
     * 
     * @param id
     *            an id to identify the video. It is used as the rel of the
     *            video link.
     * @param url
     *            the video URL.
     */
    public Video(String id, String url) {
        this.id = id;
        this.url = url;
    }

    /**
     * The video id.
     * 
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the video id.
     * 
     * @param id
     *            the id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the vide URL.
     * 
     * @return the url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the vide url.
     * 
     * @param url
     *            a url as string..
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the vide title.
     * 
     * @return the title or null if not set.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the vide title.
     * 
     * @param title
     *            a title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {

        return ToStringBuilder.toString(this);
    }

    /**
     * Gets the hypermedia link for this video.
     * 
     * @return a not null hypermedia link.
     */
    public HypermediaLink getLink() {

        return new LinkBuilder(this.getUrl(), this.getId()).title(this.getTitle()).build();

    }
}
