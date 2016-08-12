package com.github.sailarize.utils;

import java.lang.annotation.Annotation;

/**
 * Utility class for operating with annotations.
 * 
 * @author agusmunioz
 * 
 */
public class Annotations {

    /**
     * Searches a annotation recursively.
     * 
     * @param target
     *            the annotated class or a subclass.
     * 
     * @param annotationClass
     *            the type of annotation searched.
     * 
     * @return the annotation or null if not found.
     */
    public static <T extends Annotation> T search(Class<?> target,
            Class<T> annotationClass) {

        Class<?> type = target;

        while (!Object.class.equals(type)) {

            T annotation = type.getAnnotation(annotationClass);

            if (annotation != null) {
                return annotation;
            }

            type = type.getSuperclass();
        }

        return null;
    }
}
