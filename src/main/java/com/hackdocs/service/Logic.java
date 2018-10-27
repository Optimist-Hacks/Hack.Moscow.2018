package com.hackdocs.service;

import com.github.otopba.javarocketstart.RocketText;
import com.hackdocs.Flow;
import com.hackdocs.model.Request;
import com.hackdocs.model.Response;
import com.hackdocs.model.response.Payload;
import com.hackdocs.model.response.payload.Google;
import com.hackdocs.model.response.payload.google.RichResponse;
import com.hackdocs.model.response.payload.google.richResponse.Item;
import com.hackdocs.model.response.payload.google.richResponse.item.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class Logic {

    private static Logger logger = LoggerFactory.getLogger(Logic.class);
    private static final String GOOGLE_ASSISTANT_WELCOME = "GOOGLE_ASSISTANT_WELCOME";

    private final Map<String, SessionState> sessions = new HashMap<>();

    private final AbstractFlow vocationLogic;

    @Autowired
    public Logic(AbstractFlow vocationLogic) {
        this.vocationLogic = vocationLogic;
    }

    public Response processRequest(Request request) {
        String session = request.getSession();
        logger.info("Session = " + session);

        if (RocketText.safeEqualsIgnoreCase(request.getQueryResult().getQueryText(), GOOGLE_ASSISTANT_WELCOME)) {
            logger.info("GOOGLE_ASSISTANT_WELCOME");
            sessions.remove(session);
            return buildResponse(defaultWelcomeText());
        }

        String response = null;
        String text = request.getQueryResult().getQueryText();
        logger.info("Full text = " + text);

        SessionState sessionState = sessions.get(session);
        if (sessionState == null) {
            Flow flow = Flow.fromValue(text);
            if (flow != null) {
                switch (flow) {
                    case VOCATION:
                        sessionState = new SessionState(vocationLogic.getInitState(), vocationLogic);
                        buildResponse(vocationLogic.processRequest(text, sessionState));
                        break;
                }
            }
        } else {
            AbstractFlow flow = sessionState.getFlow();
            response = flow.processRequest(text, sessionState);
        }

        if (RocketText.isEmpty(response)) {
            response = defaultWrongText();
        }
        return buildResponse(response);
    }

    private Response buildResponse(String text) {
        SimpleResponse simpleResponse = new SimpleResponse(text);
        Item item = new Item(simpleResponse);
        RichResponse richResponse = new RichResponse(Collections.singletonList(item));
        Google google = new Google(true, richResponse);
        Payload payload = new Payload(google);
        return new Response(payload);
    }

    private String defaultWelcomeText() {
        return "Hello! This is our cool bot. Please choose document";
    }

    private String defaultWrongText() {
        return "Please call Alexander!!!";
    }

}
