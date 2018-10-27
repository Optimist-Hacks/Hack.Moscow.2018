package com.hackdocs.validators;

import java.util.regex.Pattern;

public class Validator {

    private static final Pattern namePattern = Pattern.compile("^[a-zA-Z][^#&<>\\\"~;$^%{}?]{1,20}$");
    private static final Pattern sexPattern = Pattern.compile("^(male)|(female)|(f)|(m)|(girl)|(man)|(woman)$");
    private static final Pattern phonePattern = Pattern.compile("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$");
    private static final Pattern emailPattern = Pattern.compile("^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@(([0-9a-zA-Z])+([-\\w]*[0-9a-zA-Z])*\\.)+[a-zA-Z]{2,9})$");
    private static final Pattern aPattern = Pattern.compile("");

    public static boolean isValidName(String name) {
        return namePattern.matcher(name.toLowerCase()).find();
    }

    public static boolean isValidSex(String sex) {
        return sexPattern.matcher(sex.toLowerCase()).find();
    }

    public static boolean isValidDate(String date) {
        return true;
    }

    public static boolean isValidPhone(String phone) {
        return phonePattern.matcher(phone.toLowerCase()).find();
    }

    public static boolean isValidEmail(String email) {
        return emailPattern.matcher(email.toLowerCase()).find();
    }

    public static boolean isValidAddress(String text) {
        return false;
    }

    public static boolean isValidCountry(String text) {
        return false;
    }

    public static boolean isValidCity(String text) {
        return false;
    }

    public static boolean isValidTime(String text) {
        return false;
    }
}
