package com.hackdocs.service;

import com.github.otopba.javarocketstart.RocketText;
import com.hackdocs.Flow;
import com.hackdocs.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String processRequest(Request request) {
        String session = request.getSession();
        logger.info("Session = " + session);

        if (RocketText.safeEqualsIgnoreCase(request.getQueryResult().getQueryText(), GOOGLE_ASSISTANT_WELCOME)) {
            logger.info("GOOGLE_ASSISTANT_WELCOME");
            sessions.remove(session);
            return defaultWelcomeText();
        }

        String text = request.getQueryResult().getQueryText();
        logger.info("Full text = " + text);

        SessionState sessionState = sessions.get(session);
        if (sessionState == null) {
            Flow flow = Flow.fromValue(text);
            if (flow != null) {
                switch (flow) {
                    case VOCATION:
                        sessionState = new SessionState(vocationLogic.getInitState(), vocationLogic);
                        return vocationLogic.processRequest(text, sessionState.getLogicState());
                }
            }
        } else {
            Enum logicState = sessionState.getLogicState();
            AbstractFlow flow = sessionState.getFlow();
            return flow.processRequest(text, logicState);
        }

        return defaultWrongText();
    }

    private String defaultWelcomeText() {
        return "{\n" +
                "  \"payload\": {\n" +
                "    \"google\": {\n" +
                "      \"expectUserResponse\": true,\n" +
                "      \"richResponse\": {\n" +
                "        \"items\": [\n" +
                "          {\n" +
                "            \"simpleResponse\": {\n" +
                "              \"textToSpeech\": \"this is a simple response\"\n" +
                "            }\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

    private String defaultWrongText() {
        return "{\n" +
                "  \"payload\": {\n" +
                "    \"google\": {\n" +
                "      \"expectUserResponse\": true,\n" +
                "      \"richResponse\": {\n" +
                "        \"items\": [\n" +
                "          {\n" +
                "            \"simpleResponse\": {\n" +
                "              \"textToSpeech\": \"this is a simple response\"\n" +
                "            }\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

}
