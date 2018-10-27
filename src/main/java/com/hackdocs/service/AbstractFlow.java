package com.hackdocs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractFlow<State extends Enum> {

    protected static Logger logger = LoggerFactory.getLogger(AbstractFlow.class);

    public abstract String processRequest(String text, SessionState<State> state);

    private String innerProcessRequest(String text, SessionState<State> state) {
        logger.info("Process state " + state);
        return processRequest(text, state);
    }

    public void changeState(SessionState<State> state, State newState) {
        state.setLogicState(newState);
    }

    public abstract State getInitState();

}
