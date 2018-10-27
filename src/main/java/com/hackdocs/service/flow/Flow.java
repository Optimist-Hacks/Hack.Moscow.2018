package com.hackdocs.service.flow;

import com.github.otopba.javarocketstart.RocketText;

public enum Flow {

    VACATION("VACATION");

    public final String name;

    Flow(String name) {
        this.name = name;
    }

    public static boolean contains(String value) {
        for (Flow type : Flow.values()) {
            if (RocketText.safeEqualsIgnoreCase(type.name, value)) {
                return true;
            }
        }
        return false;
    }

    public static Flow fromValue(String value) {
        for (Flow type : Flow.values()) {
            if (RocketText.safeEqualsIgnoreCase(type.name, value)) {
                return type;
            }
        }
        return null;
    }

}
