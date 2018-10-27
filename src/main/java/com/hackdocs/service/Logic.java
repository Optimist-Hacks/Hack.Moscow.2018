package com.hackdocs.service;

import com.github.otopba.javarocketstart.RocketText;
import com.hackdocs.Flow;
import com.hackdocs.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Logic {

    private static Logger logger = LoggerFactory.getLogger(Logic.class);
    private static final String GOOGLE_ASSISTANT_WELCOME = "GOOGLE_ASSISTANT_WELCOME";

    private final VocationLogic vocationLogic;

    @Autowired
    public Logic(VocationLogic vocationLogic) {
        this.vocationLogic = vocationLogic;
    }

    public String processRequest(Request request) {
        if (RocketText.safeEqualsIgnoreCase(request.getQueryResult().getQueryText(), GOOGLE_ASSISTANT_WELCOME)) {
            logger.info("GOOGLE_ASSISTANT_WELCOME");
            return defaultWelcomeText();
        }

        String text = request.getQueryResult().getFulfillmentText();
        logger.info("Full text = " + text);

        Flow flow = Flow.fromValue(text);
        if (flow != null) {
            switch (flow) {
                case VOCATION:
                    return vocationLogic.processRequest(text);
            }
        }

        return defaultWrongText();
    }

    private String defaultWelcomeText() {
        return "Hello! THis is super cool bot. Please choose type of document";
    }

    private String defaultWrongText() {
        return "Can't understand. Please try again";
    }

}
