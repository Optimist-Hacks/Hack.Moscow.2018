package com.hackdocs.service.logic;

import com.hackdocs.service.Session;
import com.hackdocs.service.flow.FlowLogic;
import com.hackdocs.validators.Validator;
import org.springframework.stereotype.Service;

@Service
public class VacationLogic extends FlowLogic<VacationLogic.State> {

    @Override
    public String process(String text, Session<State> state) {
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

    private String handleInit(Session<State> state) {
        changeState(state, State.FIRST_NAME);
        return "Hello! You want to create vacation document. What is your name?";
    }

    private String handleFirstName(String text, Session<State> state) {
        if (!Validator.isValidName(text)) {
            return "This is not a name. Try again";
        }

        changeState(state, State.LAST_NAME);
        return "Ok. Now, please enter last name";
    }

    private String handleLastName(String text, Session<State> state) {
        if (!Validator.isValidName(text)) {
            return "This is not a last name. Try again";
        }

        changeState(state, State.DATE);
        return "Ok. Now, please enter a date";
    }

    private String handleDate(String text, Session<State> state) {
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
