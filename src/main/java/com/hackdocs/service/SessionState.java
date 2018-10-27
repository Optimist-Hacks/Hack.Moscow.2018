package com.hackdocs.service;

public class SessionState {

    private Enum logicState;
    private AbstractFlow flow;

    public SessionState(Enum state, AbstractFlow flow) {
        this.logicState = state;
        this.flow = flow;
    }

    public Enum getLogicState() {
        return logicState;
    }

    public AbstractFlow getFlow() {
        return flow;
    }

}
