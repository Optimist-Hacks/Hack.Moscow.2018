package com.hackdocs.service.logic;

import com.hackdocs.service.SessionState;
import com.hackdocs.service.flow.FlowLogic;
import com.hackdocs.validators.Validator;
import org.springframework.stereotype.Service;

@Service
public class VacationLogic extends FlowLogic<VacationLogic.State> {

    @Override
    public String process(String text, SessionState<State> state) {
        switch (state.getLogicState()) {
            case INIT:
                return handleInit(state);
            case FIRST_NAME:
                return handleFirstName(text, state);
            case LAST_NAME:
                return handleLastName(text, state);
            case DATE:
                return handleDate(text, state);
        }

        return defaultResponse();
    }

    private String handleInit(SessionState<VacationLogic.State> state) {
        changeState(state, State.FIRST_NAME);
        return "Hello! You want to create vocation document. What is your name?";
    }

    private String handleFirstName(String text, SessionState<VacationLogic.State> state) {
        if (!Validator.isValidFirstName(text)) {
            return "This is not a name. Try again";
        }

        changeState(state, State.LAST_NAME);
        return "Ok. Now, please enter last name";
    }

    private String handleLastName(String text, SessionState<VacationLogic.State> state) {
        if (!Validator.isValidSecondName(text)) {
            return "This is not a last name. Try again";
        }

        changeState(state, State.DATE);
        return "Ok. Now, please enter a date";
    }

    private String handleDate(String text, SessionState<VacationLogic.State> state) {
        if (!Validator.isValidDate(text)) {
            return "This is not a date. Try again";
        }

        changeState(state, State.FIRST_NAME);
        return "Ok. This is all. ";
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
        DATE
    }

}
