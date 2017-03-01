package com.github.sailarize.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Utility class for generating a pretty print of an object.
 * 
 * @author agusmunioz
 * 
 */
public class ToStringBuilder {

    /**
     * Creates a pretty print of the object using reflection.
     * 
     * @param object
     *            the object to print.
     * 
     * @return the object pretty printing.
     */
    public static String toString(Object object) {

        Class<? extends Object> type = object.getClass();

        StringBuilder builder = new StringBuilder(type.getSimpleName()).append(": [");

        while (type != Object.class) {

            for (Field field : type.getDeclaredFields()) {

                if (!Modifier.isStatic(field.getModifiers())) {

                    field.setAccessible(true);

                    builder.append(field.getName()).append(": ").append(getValue(object, field)).append(", ");

                }
            }

            type = type.getSuperclass();
        }

        int delete = builder.lastIndexOf(",");

        if (delete > 0) {
            builder.delete(delete, delete + 2);
        }

        return builder.append("]").toString();
    }

    /**
     * Gets the field value.
     * 
     * @param object
     *            the object where the field is defined.
     * @param field
     *            the field.
     * @return the value or null is there was an error.
     */
    private static Object getValue(Object object, Field field) {

        try {

            return field.get(object);

        } catch (Exception e) {
            return null;
        }
    }
}
