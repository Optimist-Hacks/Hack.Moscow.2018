package com.hackdocs.service;

import org.springframework.stereotype.Service;

@Service
public class VocationLogic extends AbstractFlow<VocationLogic.State> {

    @Override
    public String processRequest(String text, VocationLogic.State state) {
        switch (state) {
            case INIT:
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

        return defaultResponse();
    }

    @Override
    public State getInitState() {
        return State.INIT;
    }

    private String defaultResponse() {
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

    public enum State {
        INIT
    }

}
