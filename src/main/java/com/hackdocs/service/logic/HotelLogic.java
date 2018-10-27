package com.hackdocs.service.logic;

import com.hackdocs.service.Session;
import com.hackdocs.service.flow.FlowLogic;
import com.hackdocs.validators.Validator;
import org.springframework.stereotype.Service;

@Service
public class HotelLogic extends FlowLogic<HotelLogic.State> {

    @Override
    public String process(String text, Session<State> state) {
        switch (state.getLogicState()) {
            case INIT:
                return handleInit(state);
            case FIRST_NAME:
                return handleFirstName(text, state);
            case LAST_NAME:
                return handleLastName(text, state);
            case ADDRESS:
                return handleAddress(text, state);
            case COUNTRY:
                return handleCountry(text, state);
            case CITY:
                return handleCity(text, state);
            case TELEPHONE:
                return handleTelephone(text, state);
            case CELL_PHONE:
                return handleCellPhone(text, state);
            case EMAIL:
                return handleEmail(text, state);
            case ARRIVAL_DATE:
                return handleArrivalDate(text, state);
            case ARRIVAL_TIME:
                return handleArrivalTime(text, state);
            case DEPARTURE_DATE:
                return handleDepartureDate(text, state);
            case DEPARTURE_TIME:
                return handleDepartureTime(text, state);
        }

        return defaultResponse();
    }

    private String handleInit(Session<State> state) {
        changeState(state, State.FIRST_NAME);
        return "Hello! You want to create HOTEL?? document. What is your first name?";
    }

    private String handleFirstName(String text, Session<State> state) {
        if (Validator.isValidName(text)) {
            changeState(state, State.LAST_NAME);
            return "Ok. Now, please enter last name";
        } else {
            return "This is not a name. Try again";
        }
    }

    private String handleLastName(String text, Session<State> state) {
        if (Validator.isValidName(text)) {
            changeState(state, State.ADDRESS);
            return "Ok. Now, please enter a your address";
        } else {
            return "This is not a last name. Try again";
        }
    }

    private String handleAddress(String text, Session<State> state) {
        if (Validator.isValidAddress(text)) {
            changeState(state, State.COUNTRY);
            return "Ok. Now, please enter a your country. ";
        } else {
            return "This is not an address. Try again";
        }
    }

    private String handleCountry(String text, Session<State> state) {
        if (Validator.isValidCountry(text)) {
            changeState(state, State.CITY);
            return "Ok. ";
        } else {
            return "This is not a country. Try again";
        }
    }

    private String handleCity(String text, Session<State> state) {
        if (Validator.isValidCity(text)) {
            changeState(state, State.TELEPHONE);
            return "Ok. Now, please enter a your home phone number";
        } else {
            return "This is not a city. Try again";
        }
    }

    private String handleTelephone(String text, Session<State> state) {
        if (Validator.isValidPhone(text)) {
            changeState(state, State.CELL_PHONE);
            return "Ok. Now, please enter your cell phone number";
        } else {
            return "This is not a phone number. Try again";
        }

    }

    private String handleCellPhone(String text, Session<State> state) {
        if (Validator.isValidPhone(text)) {
            changeState(state, State.EMAIL);
            return "Ok. Now, please enter a your e-mail.";
        } else {
            return "This is not a cell phone number. Try again";
        }
    }

    private String handleEmail(String text, Session<State> state) {
        if (Validator.isValidEmail(text)) {
            changeState(state, State.ARRIVAL_DATE);
            return "Ok. Now, please enter a arrival date.";
        } else {
            return "This is not an e-mail . Try again";
        }
    }

    private String handleArrivalDate(String text, Session<State> state) {
        if (Validator.isValidDate(text)) {
            changeState(state, State.ARRIVAL_TIME);
            return "Ok. Now, please enter arrival time.";
        } else {
            return "This is not a date. Try again";
        }
    }

    private String handleArrivalTime(String text, Session<State> state) {
        if (Validator.isValidTime(text)) {
            changeState(state, State.DEPARTURE_DATE);
            return "Ok. Now, please enter a departure date.";
        } else {
            return "This is not a time. Try again";
        }
    }

    private String handleDepartureDate(String text, Session<State> state) {
        if (Validator.isValidDate(text)) {
            changeState(state, State.DEPARTURE_TIME);
            return "Ok. Now, please enter a departure time.";
        } else {
            return "This is not a date. Try again";
        }
    }

    private String handleDepartureTime(String text, Session<State> state) {
        if (Validator.isValidTime(text)) {
            changeState(state, State.TERMINATED);
            return "Ok. That's all foks!";
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
        ADDRESS,
        COUNTRY,
        CITY,
        TELEPHONE,
        CELL_PHONE,
        EMAIL,
        ARRIVAL_DATE,
        ARRIVAL_TIME,
        DEPARTURE_DATE,
        DEPARTURE_TIME,
        TERMINATED
    }


}
