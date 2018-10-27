package com.hackdocs.service;

import com.hackdocs.service.flow.FlowLogic;

public class SessionState<State extends Enum> {

    private State logicState;
    private FlowLogic flow;

    public SessionState(State state, FlowLogic flow) {
        this.logicState = state;
        this.flow = flow;
    }

    public State getLogicState() {
        return logicState;
    }

    public void setLogicState(State logicState) {
        this.logicState = logicState;
    }

    public FlowLogic getFlow() {
        return flow;
    }

}
