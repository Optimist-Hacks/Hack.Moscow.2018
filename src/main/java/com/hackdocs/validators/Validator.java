package com.hackdocs.validators;

import java.util.regex.Pattern;

public class Validator {

    private static final Pattern namePattern = Pattern.compile("^[a-zA-Z][^#&<>\\\"~;$^%{}?]{1,20}$");
    private static final Pattern sexPattern = Pattern.compile("^(male)|(female)|f|m|(girl)|(man)$");

    public static boolean isValidName(String name) {
        return namePattern.matcher(name.toLowerCase()).find();
    }

    public static boolean isValidSex(String sex) {
        return sexPattern.matcher(sex.toLowerCase()).find();
    }

    public static boolean isValidDate(String date) {
        return true;
    }
}
