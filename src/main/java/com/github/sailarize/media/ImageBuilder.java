package com.github.sailarize.media;

import com.github.sailarize.properties.Hosts;

/**
 * A builder for {@link Image}.
 * 
 * @author agustin.munoz
 *
 */
public class ImageBuilder {

	private String rel;

	private String host;

	private String type;

	private String url;

	private Integer height;

	private Integer width;

	private String title;

	private String path;

	/**
	 * Creates an initialized {@link ImageBuilder}.
	 * 
	 * @param rel
	 *            the image link rel.
	 * 
	 * @return the builder.
	 */
	public static ImageBuilder rel(String rel) {

		ImageBuilder builder = new ImageBuilder();
		builder.rel = rel;

		return builder;
	}

	/**
	 * Configures the image host.
	 * 
	 * @param host
	 * 
	 * @return
	 */
	public ImageBuilder host(String host, String... args) {

		String property = Hosts.get(host, args);

		this.host = (property == null ? host : property);

		return this;
	}

	public ImageBuilder type(String type) {
		this.type = type;
		return this;
	}

	public ImageBuilder url(String url) {
		this.url = url;
		return this;
	}

	public ImageBuilder height(Integer height) {
		this.height = height;
		return this;
	}

	public ImageBuilder width(Integer width) {
		this.width = width;
		return this;
	}

	public ImageBuilder title(String title) {
		this.title = title;
		return this;
	}

	public ImageBuilder path(String path) {

		this.path = path;
		return this;
	}

	public Image build() {

		if (this.url == null || this.url.isEmpty()) {
			this.url = this.host + this.path;
		}

		Image image = new Image(this.url, rel);
		image.setType(this.type);
		image.setHeight(this.height);
		image.setWidth(this.width);
		image.setTitle(this.title);

		return image;
	}
}
