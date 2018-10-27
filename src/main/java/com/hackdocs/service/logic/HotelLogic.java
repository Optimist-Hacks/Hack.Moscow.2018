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

        changeState(state, State.ADDRESS);
        return "Ok. Now, please enter a your address";
    }

    private String handleAddress(String text, Session<State> state) {
        if (false) {//!Validator.isValidAddress(text)) {
            return "This is not an address. Try again";
        }

        changeState(state, State.COUNTRY);
        return "Ok. Now, please enter a your country. ";
    }

    private String handleCountry(String text, Session<State> state) {
        if (false) {//!Validator.isValidCountry(text)) {
            return "This is not a country. Try again";
        }

        changeState(state, State.CITY);
        return "Ok. ";
    }

    private String handleCity(String text, Session<State> state) {
        if (false) {//!Validator.isValidCity(text)) {
            return "This is not a city. Try again";
        }

        changeState(state, State.TELEPHONE);
        return "Ok. Now, please enter a your home phone number";
    }

    private String handleTelephone(String text, Session<State> state) {
        if (false) {//!Validator.isValidPhone(text)) {
            return "This is not a phone number. Try again";
        }

        changeState(state, State.CELL_PHONE);
        return "Ok. Now, please enter your cell phone number";
    }

    private String handleCellPhone(String text, Session<State> state) {
        if (false) {//!Validator.isValidPhone(text)) {
            return "This is not a cell phone number. Try again";
        }

        changeState(state, State.EMAIL);
        return "Ok. Now, please enter a your e-mail.";
    }

    private String handleEmail(String text, Session<State> state) {
        if (false) {//!Validator.isValidEmail(text)) {
            return "This is not an e-mail . Try again";
        }

        changeState(state, State.ARRIVAL_DATE);
        return "Ok. Now, please enter a arrival date.";
    }

    private String handleArrivalDate(String text, Session<State> state) {
        if (!Validator.isValidDate(text)) {
            return "This is not a date. Try again";
        }

        changeState(state, State.ARRIVAL_TIME);
        return "Ok. Now, please enter arrival time.";
    }

    private String handleArrivalTime(String text, Session<State> state) {
        if (false) {//!Validator.isValidTime(text)) {
            return "This is not a time. Try again";
        }

        changeState(state, State.DEPARTURE_DATE);
        return "Ok. Now, please enter a departure date.";
    }

    private String handleDepartureDate(String text, Session<State> state) {
        if (!Validator.isValidDate(text)) {
            return "This is not a date. Try again";
        }

        changeState(state, State.DEPARTURE_TIME);
        return "Ok. Now, please enter a departure time.";
    }

    private String handleDepartureTime(String text, Session<State> state) {
        if (false) {//!Validator.isValidTime(text)) {
            return "This is not a time. Try again";
        }

        changeState(state, State.TERMINATED);
        return "Ok. That's all foks!";
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
