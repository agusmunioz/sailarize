package com.github.sailarize.media;

import com.github.sailarize.link.HypermediaLink;
import com.github.sailarize.link.LinkBuilder;
import com.github.sailarize.utils.ToStringBuilder;

public class Image {

    private String url;

    private Float height;

    private Float width;

    private String title;

    private String rel;

    private String type;

    private String[] tags;

    public Image(String url, String rel) {

        this.url = url;
        this.rel = rel;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    public Float getHeight() {

        return height;
    }

    public void setHeight(Float height) {

        this.height = height;
    }

    public Float getWidth() {

        return width;
    }

    public void setWidth(Float width) {

        this.width = width;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getRel() {

        return rel;
    }

    public void setRel(String rel) {

        this.rel = rel;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String[] getTags() {

        return tags;
    }

    public void setTags(String... tags) {

        this.tags = tags;
    }

    @Override
	public String toString() {

		return ToStringBuilder.toString(this);
	}
    
    /**
     * Gets the hypermedia link for this image.
     * 
     * @return a not null hypermedia link.
     */
    public HypermediaLink getLink() {

        LinkBuilder builder = new LinkBuilder(this.getUrl(), this.getRel())
                .title(this.getTitle());

        if (this.getType() != null) {
            builder.type("image/" + this.getType());
        }

        if (this.getHeight() != null) {
            builder.data("height", this.getHeight().toString());
        }

        if (this.getWidth() != null) {
            builder.data("width", this.getWidth().toString());
        }

        return builder.build();
    }
}
