package com.hackdocs.service.flow;

import com.hackdocs.service.PdfService;
import com.hackdocs.service.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class FlowLogic<State extends Enum> {

    protected static Logger logger = LoggerFactory.getLogger(FlowLogic.class);

    protected abstract String process(String text, Session<State> state);

    @Autowired
    private PdfService pdfService;

    public String processRequest(String text, Session<State> state) {
        logger.info("Process state " + state);
        return process(text, state);
    }

    public void changeState(Session<State> state, State newState) {
        state.setLogicState(newState);
    }

    public abstract State getInitState();

    protected String buildPDF(Session<State> session) {
        return pdfService.fillDocument(session.getDocument());
    }


}
