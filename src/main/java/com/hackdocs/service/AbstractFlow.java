package com.hackdocs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractFlow<State extends Enum> {

    protected static Logger logger = LoggerFactory.getLogger(AbstractFlow.class);

    protected abstract String process(String text, SessionState<State> state);

    public String processRequest(String text, SessionState<State> state) {
        logger.info("Process state " + state);
        return process(text, state);
    }

    public void changeState(SessionState<State> state, State newState) {
        state.setLogicState(newState);
    }

    public abstract State getInitState();

}
