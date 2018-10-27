package com.hackdocs.validators;

import java.util.regex.Pattern;

public class Validator {

    static String[] months = {"january","february","march","april","may","june","july","august","september","october","november","december"};

    private static final Pattern namePattern = Pattern.compile("^[a-zA-Z][^#&<>\\\"~;$^%{}?]{1,20}$");
    private static final Pattern sexPattern = Pattern.compile("^(male)|(female)|(f)|(m)|(girl)|(man)|(woman)$");

    public static boolean isValidName(String name) {
        return namePattern.matcher(name.toLowerCase()).find();
    }

    public static boolean isValidSex(String sex) {
        return sexPattern.matcher(sex.toLowerCase()).find();
    }

    //13 october
    public static boolean isValidDate(String date) {
        return true;
    }

    //11.22
    public static boolean isValidTime(String time) { return false; }
}
