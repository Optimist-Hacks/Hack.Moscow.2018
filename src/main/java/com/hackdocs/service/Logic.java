package com.hackdocs.service;

import com.github.otopba.javarocketstart.RocketText;
import com.hackdocs.model.Request;
import com.hackdocs.model.Response;
import com.hackdocs.model.response.Payload;
import com.hackdocs.model.response.payload.Google;
import com.hackdocs.model.response.payload.google.RichResponse;
import com.hackdocs.model.response.payload.google.richResponse.Item;
import com.hackdocs.model.response.payload.google.richResponse.item.SimpleResponse;
import com.hackdocs.service.flow.Flow;
import com.hackdocs.service.flow.FlowLogic;
import com.hackdocs.service.logic.VacationLogic;
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
    private static final String TELEGRAM = "telegram";

    private final Map<String, Session> sessions = new HashMap<>();

    private final VacationLogic vacationLogic;

    @Autowired
    public Logic(VacationLogic vacationLogic) {
        this.vacationLogic = vacationLogic;
    }

    public Response processRequest(Request request) {
        String session = request.getSession();
        logger.info("Session = " + session);

        if (RocketText.safeEqualsIgnoreCase(request.getQueryResult().getQueryText(), GOOGLE_ASSISTANT_WELCOME)) {
            return processWelcomeMessage(session);
        }

//        if (RocketText.safeEqualsIgnoreCase(request.getOriginalDetectIntentRequest().getSource(), "TELEGRAM")) {
//            return
//        }

        return processDataMessage(request, session);
    }

    private Response processDataMessage(Request request, String sessionId) {
        String response = null;
        String queryText = request.getQueryResult().getQueryText();
        logger.info("Full text = " + queryText);

        Session currSession = obtainSession(sessionId, queryText);

        if (currSession != null) {
            FlowLogic flow = currSession.getFlow();
            response = flow.processRequest(queryText, currSession);
        }

        if (RocketText.isEmpty(response)) {
            response = defaultWrongText();
        }

        return buildResponse(response);
    }

    private Session obtainSession(String sessionId, String queryText) {
        Session currSession = sessions.get(sessionId);
        if (currSession == null) {
            logger.info("This is new session");
            Flow flow = Flow.fromValue(queryText);
            if (flow != null) {
                switch (flow) {
                    case VACATION:
                        currSession = new Session(vacationLogic.getInitState(), vacationLogic);
                        break;
                }
            }
            sessions.put(sessionId, currSession);
        } else {
            logger.info("We already have this session");
        }

        return currSession;
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

    private Response processWelcomeMessage(String session) {
        logger.info(GOOGLE_ASSISTANT_WELCOME);
        logger.info("Remove session " + session);
        sessions.remove(session);
        return buildResponse(defaultWelcomeText());
    }


}
