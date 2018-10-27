package com.hackdocs.service;

import org.springframework.stereotype.Service;

@Service
public class VocationLogic extends AbstractFlow<VocationLogic.State> {

    @Override
    public String processRequest(String text, SessionState<VocationLogic.State> state) {
        switch (state.getLogicState()) {
            case INIT:
                changeState(state, State.FIRST_NAME);
                return "Hello! You want to create vocation document. What is your name?";
            case LAST_NAME:
                changeState(state, State.LAST_NAME);
                return "OK!";
        }

        return defaultResponse();
    }

    @Override
    public State getInitState() {
        return State.INIT;
    }

    private String defaultResponse() {
        return "OH MY GOD";
    }

    public enum State {
        INIT,
        FIRST_NAME,
        LAST_NAME,
    }

}
