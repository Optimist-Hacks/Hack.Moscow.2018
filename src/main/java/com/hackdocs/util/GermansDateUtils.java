package com.hackdocs.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GermansDateUtils {

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static String formatDate(String rawDateTime) {
        return format(rawDateTime, DateTimeFormatter.ISO_DATE);
    }

    public static String formatTime(String rawDateTime) {
        return format(rawDateTime, DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public static String format(String rawDateTime, DateTimeFormatter formatter) {
        LocalDateTime dateTime = LocalDateTime.parse(rawDateTime);
        return dateTime.format(formatter);
    }


}
