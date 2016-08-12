package com.github.sailarize.mediatype;

import com.github.sailarize.resource.SailResource;
import com.github.sailarize.resource.SailResourceList;
import com.github.sailarize.resource.Version;
import com.github.sailarize.utils.Annotations;

/**
 * Helps to create a resource media type.
 * 
 * @author agusmunioz
 *
 */
public class MediaTypeBuilder {

	public static final String SAIL_MEDIA = "application/vnd.sail+json";

	private static final String VERSION_PARAM = ";version=";

	/**
	 * Builds the resource media type.
	 * 
	 * @param resource
	 *            the sail resource.
	 * @return the media type.
	 */
	public static String build(SailResource resource) {

		Class<? extends SailResource> type = resource.getClass();

		if (type.equals(SailResourceList.class)) {

			SailResourceList<?> list = (SailResourceList<?>) resource;

			if (list.version() != null) {
				return mediaType(list.version());
			}

			type = list.resourceType();
		}

		return build(type);
	}

	/**
	 * Builds the resource class media type.
	 * 
	 * @param resourceType
	 *            the sail resource type.
	 * 
	 * @return the media type.
	 */
	public static String build(Class<? extends SailResource> resourceType) {

		Version annotation = Annotations.search(resourceType, Version.class);

		if (annotation != null) {
			return mediaType(annotation.value());
		}

		return SAIL_MEDIA;
	}

	private static String mediaType(String version) {

		StringBuilder builder = new StringBuilder(SAIL_MEDIA);

		if (version != null && !version.isEmpty()) {
			builder.append(VERSION_PARAM).append(version);
		}

		return builder.toString();
	}
}
