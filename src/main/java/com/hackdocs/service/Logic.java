package com.hackdocs.service;

import com.github.otopba.javarocketstart.RocketText;
import com.hackdocs.model.Request;
import com.hackdocs.model.Response;
import com.hackdocs.model.response.Payload;
import com.hackdocs.model.response.payload.Google;
import com.hackdocs.model.response.payload.Telegram;
import com.hackdocs.model.response.payload.google.RichResponse;
import com.hackdocs.model.response.payload.google.richResponse.Item;
import com.hackdocs.model.response.payload.google.richResponse.item.SimpleResponse;
import com.hackdocs.service.flow.Flow;
import com.hackdocs.service.flow.FlowLogic;
import com.hackdocs.service.logic.HotelLogic;
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

    private final HotelLogic hotelLogic;

    @Autowired
    public Logic(VacationLogic vacationLogic, HotelLogic hotelLogic) {
        this.vacationLogic = vacationLogic;
        this.hotelLogic = hotelLogic;
    }

    public Response processRequest(Request request) {
        String session = request.getSession();
        logger.info("Session = " + session);

        String response;
        if (RocketText.safeEqualsIgnoreCase(request.getQueryResult().getQueryText(), GOOGLE_ASSISTANT_WELCOME) || !sessions.containsKey(session)) {
            response = processWelcomeMessage(session);
        } else {
            response = processDataMessage(request, session);
        }

        if (RocketText.safeEqualsIgnoreCase(request.getOriginalDetectIntentRequest().getSource(), TELEGRAM)) {
            return buildTelegramResponse(response);
        } else {
            return buildGoogleResponse(response);
        }
    }

    private String processDataMessage(Request request, String sessionId) {
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

        return response;
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
                    case HOTEL:
                        currSession = new Session(hotelLogic.getInitState(), hotelLogic);
                        break;
                }
            }
            sessions.put(sessionId, currSession);
        } else {
            logger.info("We already have this session");
        }

        return currSession;
    }

    private Response buildGoogleResponse(String text) {
        SimpleResponse simpleResponse = new SimpleResponse(text);
        Item item = new Item(simpleResponse);
        RichResponse richResponse = new RichResponse(Collections.singletonList(item));
        Google google = new Google(true, richResponse);
        Payload payload = new Payload(google);
        return new Response(payload);
    }

    private Response buildTelegramResponse(String text) {
        Telegram telegram = new Telegram(text);
        Payload payload = new Payload(telegram);
        return new Response(payload);
    }

    private String defaultWelcomeText() {
        return "Hello! This is our cool bot. Please choose document:\n-Vacation\n-Hotel";
    }

    private String defaultWrongText() {
        return "Please call Alexander!!!";
    }

    private String processWelcomeMessage(String session) {
        logger.info(GOOGLE_ASSISTANT_WELCOME);
        logger.info("Remove session " + session);
        sessions.remove(session);
        return defaultWelcomeText();
    }

}
