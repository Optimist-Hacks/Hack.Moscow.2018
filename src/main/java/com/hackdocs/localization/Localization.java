package com.hackdocs.localization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
public class Localization {

    private static Logger logger = LoggerFactory.getLogger(Localization.class);

    private ResourceBundle resourceBundle;

    private Localization() {
        logger.info("Load localization");
        resourceBundle = ResourceBundle.getBundle("localization.data", new UTF8Control());
    }

    public String getString(String key) {
        try {
            return resourceBundle.getString(key);
        } catch (Exception ex) {
            logger.warn("Can't find string by key " + key);
            return "";
        }
    }

    public String getQuantityString(String key, int count) {
        String keyString = String.valueOf(count);
        char lastSymbol = keyString.charAt(keyString.length() - 1);
        String fullKey = key;
        if (lastSymbol == '0') {
            fullKey += "_zero";
        } else if (lastSymbol == '1') {
            fullKey += "_one";
        } else if (lastSymbol == '2' || lastSymbol == '3' || lastSymbol == '4') {
            fullKey += "_few";
        } else {
            fullKey += "_many";
        }

        return getString(fullKey);
    }

}
