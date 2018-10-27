package com.hackdocs.util;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static String formatDate(String text) {
        return format(text, "MM.dd.yyyy");
    }

    public static String formatTime(String text) {
        return format(text, "HH:mm");
    }

    public static String format(String text, String format) {
        ISO8601DateFormat inputDateFormat = new ISO8601DateFormat();
        Date date;
        try {
            date = inputDateFormat.parse(text);
        } catch (ParseException e) {
            logger.error("Can't parse date " + text, e);
            return null;
        }

        DateFormat outputDateFormat = new SimpleDateFormat(format);
        return outputDateFormat.format(date);
    }

}