package com.hackdocs.service.logic;

import com.hackdocs.model.Request;
import com.hackdocs.service.PdfService;
import com.hackdocs.service.Session;
import com.hackdocs.service.flow.FlowLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

import static com.hackdocs.model.businessModels.FieldType.*;
import static com.hackdocs.service.logic.HotelLogic.COMPLETED_DOCUMENTS;

@Service
public class VacationLogic extends FlowLogic<VacationLogic.State> {

    @Autowired
    private PdfService pdfService;

    private Predicate<String> notEmprty = (s) -> s != null && !s.isEmpty();

    @Override
    protected String process(Request request, Session<State> state) {
        switch (state.getLogicState()) {
            case INIT:
                return handleInit(state);
            case FIRST_NAME:
                return handleFirstName(request, state);
            case LAST_NAME:
                return handleLastName(request, state);
            case DATE_FROM:
                return handleDateFrom(request, state);
            case DATE_TO:
                return handleDateTo(request, state);
        }

        return defaultResponse();
    }

    private String getRequestPlainText(Request request) {
        return request.getQueryResult().getQueryText();
    }

    private String handleInit(Session<State> state) {
        changeState(state, State.FIRST_NAME);
        return "Hello! You want to create vacation document. What is your name?";
    }

    public String handleFirstName(Request request, Session<State> session) {
        String firstName = request.getQueryResult().getParameters().get("firstName");
        if (notEmprty.test(firstName)) {
            changeState(session, State.LAST_NAME);
            session.getDocument().getFieldByType(NAME).setValue(firstName);
            logger.info("Я ЗАЛОГГИРОВАЛ ИМЯ ЛЮБИМОЕ ТВОЁ:  " + session.getDocument().getFieldByType(NAME).getValue());
            return "Ok. Now, please enter last name";
        } else {
            return "This is not a name. Try again";
        }
    }

    public String handleLastName(Request request, Session<State> session) {
        String lastName = request.getQueryResult().getParameters().get("lastName");

        logger.info("LAST_NAME_HANDLE");

        if (notEmprty.test(lastName)) {
            changeState(session, State.DATE_FROM);
            session.getDocument().getFieldByType(LASTNAME).setValue(lastName);
            return "Ok. Now, please enter a date from";
        } else {
            return "This is not a last name. Try again";
        }
    }

    public String handleDateFrom(Request request, Session<State> session) {
        logger.info("DATE_HANDLE");

        String depDate = request.getQueryResult().getParameters().get("depDate");
        if (notEmprty.test(depDate)) {
            changeState(session, State.DATE_TO);
            session.getDocument().getFieldByType(DATE_FROM).setValue(depDate);
            return "Ok. Now, please enter a date to";
        } else {
            return "This is not a date. Try again";
        }
    }

    public String handleDateTo(Request request, Session<State> session) {
        logger.info("DATE_HANDLE");

        String dateTO = request.getQueryResult().getParameters().get("depDate");
        if (notEmprty.test(dateTO)) {
            changeState(session, State.TERMINATED);
            session.getDocument().getFieldByType(DATE_TO).setValue(dateTO);
            String file = pdfService.fillDocument(session.getDocument());
            return String.format("Ok. That's all folks! Here is your file:\nhttps://techdrive.pro/api/v1/png/%s", file);
        } else {
            return "This is not a time. Try again";
        }
    }

    @Override
    public State getInitState() {
        return State.INIT;
    }

    private String defaultResponse() {
        return "Something goes wrong. Please try again";
    }

    public enum State {
        INIT,
        FIRST_NAME,
        LAST_NAME,
        DATE_FROM,
        DATE_TO,
        TERMINATED
    }

}
