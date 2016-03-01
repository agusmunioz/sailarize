package com.github.sailarize.mediatype;

import com.github.sailarize.resource.SailResource;
import com.github.sailarize.resource.SailResourceList;
import com.github.sailarize.resource.Version;
import com.github.sailarize.utils.Annotations;

public class MediaTypeBuilder {

    public static final String SAIL_MEDIA = "application/vnd.sail+json";

    private static final String VERSION_PARAM = ";version=";

    public static String getType(SailResource resource) {

        Class<? extends SailResource> type = resource.getClass();

        if (type.equals(SailResourceList.class)) {

            SailResourceList<?> list = (SailResourceList<?>) resource;

            if (list.version() != null) {
                return mediaType(list.version());
            }

            type = list.resourceType();
        }

        return getType(type);
    }

    public static String getType(Class<? extends SailResource> resourceType) {

        Version annotation = Annotations.search(resourceType, Version.class);

        if (annotation != null) {
            return mediaType(annotation.value());
        }

        return SAIL_MEDIA;
    }

    private static String mediaType(String version) {

        return SAIL_MEDIA + VERSION_PARAM + version;
    }
}
