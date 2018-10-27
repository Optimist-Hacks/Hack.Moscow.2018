package com.hackdocs.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern firstNamePattern = Pattern.compile("[A-Z][a-zA-Z][^#&<>\\\"~;$^%{}?]{1,20}$");
    private static final Pattern secondNamePattern = Pattern.compile("[A-Z][a-zA-Z][^#&<>\\\"~;$^%{}?]{1,20}$");

    public static boolean isValidFirstName(String firstName) {
        Matcher matcher = firstNamePattern.matcher(firstName);

        if (matcher.find()) {
            return matcher.start() == 0;
        } else {
            return false;
        }
    }

    public static boolean isValidSecondName(String secondName) {
        Matcher matcher = secondNamePattern.matcher(secondName);

        if (matcher.find()) {
            return matcher.start() == 0;
        } else {
            return false;
        }
    }

    public static boolean isValidSex(String sex) {
        return true;
    }

    public static boolean isValidDate(String date) {
        return true;
    }
}
