package com.hackdocs.service;

public class SessionState<State extends Enum> {

    private State logicState;
    private AbstractFlow flow;

    public SessionState(State state, AbstractFlow flow) {
        this.logicState = state;
        this.flow = flow;
    }

    public State getLogicState() {
        return logicState;
    }

    public void setLogicState(State logicState) {
        this.logicState = logicState;
    }

    public AbstractFlow getFlow() {
        return flow;
    }

}
