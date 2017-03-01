package com.github.sailarize.utils;

public class StringUtils {

    public static String capitalize(String word) {

        if (word.isEmpty()) {
            return word;
        }

        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }
}
