package com.github.sailarize.media;

import com.github.sailarize.link.HypermediaLink;
import com.github.sailarize.link.LinkBuilder;
import com.github.sailarize.utils.ToStringBuilder;

public class Image {

    private String url;

    private Integer height;

    private Integer width;

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

    public Integer getHeight() {

        return height;
    }

    public void setHeight(Integer height) {

        this.height = height;
    }

    public Integer getWidth() {

        return width;
    }

    public void setWidth(Integer width) {

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
