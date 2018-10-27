package com.hackdocs.service;

import com.hackdocs.model.businessModels.Document;
import com.hackdocs.service.flow.FlowLogic;
import com.hackdocs.util.DocumentBuilder;
import lombok.Data;


@Data
public class Session<State extends Enum> {

    private State logicState;
    private FlowLogic flow;
    private Document document = DocumentBuilder.getHotelDocument();


    public Session(State state, FlowLogic flow) {
        this.logicState = state;
        this.flow = flow;
    }

}
