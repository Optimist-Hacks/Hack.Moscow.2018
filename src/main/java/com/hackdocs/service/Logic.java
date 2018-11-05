package com.hackdocs.service;

import com.github.otopba.javarocketstart.RocketText;
import com.hackdocs.model.Request;
import com.hackdocs.model.Response;
import com.hackdocs.model.response.payload.google.expectedInputs.inputPrompt.richInitialPrompt.Items;
import com.hackdocs.model.response.payload.google.richResponse.ItemSimpleResponse;
import com.hackdocs.service.flow.Flow;
import com.hackdocs.service.flow.FlowLogic;
import com.hackdocs.service.logic.HotelLogic;
import com.hackdocs.service.logic.VacationLogic;
import com.hackdocs.util.DocumentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.github.otopba.javarocketstart.RocketText.safeEqualsIgnoreCase;
import static com.hackdocs.model.Response.buildGoogleResponse;
import static com.hackdocs.model.Response.buildTelegramResponse;

@Service
public class Logic {

    private static final String DEFAULT_WRONG_TEXT = "Please call Alexander!!!";
    private static Logger logger = LoggerFactory.getLogger(Logic.class);
    private static final String GOOGLE_ASSISTANT_WELCOME = "GOOGLE_ASSISTANT_WELCOME";
    private static final String DEFAULT_WELCOME_TEXT = "Hello! This is our cool bot. Please choose document:\n-Vacation\n-Hotel";
    private static final String TELEGRAM = "telegram";

    private Map<String, Session> sessions = new HashMap<>();

    private final VacationLogic vacationLogic;

    private final HotelLogic hotelLogic;

    @Autowired
    public Logic(VacationLogic vacationLogic, HotelLogic hotelLogic) {
        this.vacationLogic = vacationLogic;
        this.hotelLogic = hotelLogic;
    }

    public Response processRequest(Request request) {
        String sessionId = request.getSession();
        logger.info("Session = " + sessionId);

        String response;
        if (safeEqualsIgnoreCase(request.getQueryResult().getQueryText(), GOOGLE_ASSISTANT_WELCOME)) {
            logger.info(GOOGLE_ASSISTANT_WELCOME);
            response = processWelcomeMessage(sessionId);
        } else {
            response = processDataMessage(request, sessionId);
        }

        if (safeEqualsIgnoreCase(request.getOriginalDetectIntentRequest().getSource(), TELEGRAM)) {
            return buildTelegramResponse(response);
        } else {
            Response response1 = buildGoogleResponse(response);

            try {
                String png = sessions.get(sessionId).getDocument().getPng();
                String pdf = sessions.get(sessionId).getDocument().getPdf();

                if (png != null) {
                    Items cardWrapper = new Items();

                    cardWrapper.getBasicCard().getImage().setUrl("https://techdrive.pro/api/v1/png/11.png");
                    cardWrapper.getBasicCard().getButtons().get(0).getOpenUrlAction().setUrl(pdf);

                    response1.getPayload().getGoogle().getRichResponse().getItems().add(cardWrapper);
                    ((ItemSimpleResponse) response1.getPayload().getGoogle().getRichResponse().getItems().get(0)).getSimpleResponse().setTextToSpeech("DONE!");
                }
            } catch (Exception ignore) {

            }

            return response1;
        }
    }

    private String processDataMessage(Request request, String sessionId) {
        String response;
        String queryText = request.getQueryResult().getQueryText();
        logger.info("Full text = " + queryText);
        logger.info("HASH" + sessions.size());
        Session currSession = obtainSession(sessionId, queryText);

        if (currSession != null) {
            FlowLogic flow = currSession.getFlow();
            if (safeEqualsIgnoreCase(request.getQueryResult().getQueryText(), "cancel")) {
                sessions.remove(sessionId);
                return "";
            } else {
                response = flow.processRequest(request, currSession);
            }
        } else {
            response = processWelcomeMessage(sessionId);
        }

        if (RocketText.isEmpty(response)) {
            response = DEFAULT_WRONG_TEXT;
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
                        currSession = new Session(vacationLogic.getInitState(), vacationLogic,  DocumentBuilder.getVocationDocument());
                        break;
                    case HOTEL:
                        currSession = new Session(hotelLogic.getInitState(), hotelLogic, DocumentBuilder.getHotelDocument());
                        break;
                }
            }

            if (currSession != null) {
                sessions.put(sessionId, currSession);
            }
        } else {
            logger.info("We already have this session");
        }
        return currSession;
    }

    private String processWelcomeMessage(String sessionId) {
        logger.info("Remove session " + sessionId);
        sessions.remove(sessionId);
        return DEFAULT_WELCOME_TEXT;
    }

}
