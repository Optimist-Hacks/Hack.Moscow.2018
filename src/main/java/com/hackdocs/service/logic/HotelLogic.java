package com.hackdocs.service.logic;

import com.hackdocs.service.Session;
import com.hackdocs.service.flow.FlowLogic;
import com.hackdocs.validators.Validator;
import org.springframework.stereotype.Service;

import static com.hackdocs.model.businessModels.FieldType.*;

@Service
public class HotelLogic extends FlowLogic<HotelLogic.State> {

    @Override
    public String process(String text, Session<State> session) {
        switch (session.getLogicState()) {
            case INIT:
                return handleInit(session);
            case FIRST_NAME:
                return handleFirstName(text, session);
            case LAST_NAME:
                return handleLastName(text, session);
            case ADDRESS:
                return handleAddress(text, session);
            case COUNTRY:
                return handleCountry(text, session);
            case CITY:
                return handleCity(text, session);
            case TELEPHONE:
                return handleTelephone(text, session);
            case CELL_PHONE:
                return handleCellPhone(text, session);
            case EMAIL:
                return handleEmail(text, session);
            case ARRIVAL_DATE:
                return handleArrivalDate(text, session);
            case ARRIVAL_TIME:
                return handleArrivalTime(text, session);
            case DEPARTURE_DATE:
                return handleDepartureDate(text, session);
            case DEPARTURE_TIME:
                return handleDepartureTime(text, session);
        }

        return defaultResponse();
    }

    private String handleInit(Session<State> session) {
        changeState(session, State.FIRST_NAME);
        return "Hello! You want to create hotel check-in document. What is your name?";
    }

    private String handleFirstName(String text, Session<State> session) {
        if (Validator.isValidName(text)) {
            changeState(session, State.LAST_NAME);
            session.getDocument().getFieldByType(NAME).setValue(text);
            return "Ok. Now, please enter last name";
        } else {
            return "This is not a name. Try again";
        }
    }

    private String handleLastName(String text, Session<State> session) {
        if (Validator.isValidName(text)) {
            changeState(session, State.ADDRESS);
            session.getDocument().getFieldByType(LASTNAME).setValue(text);
            return "Ok. Now, please enter a your address";
        } else {
            return "This is not a last name. Try again";
        }
    }

    private String handleAddress(String text, Session<State> session) {
        if (Validator.isValidAddress(text)) {
            changeState(session, State.COUNTRY);
            session.getDocument().getFieldByType(ADDRESS).setValue(text);
            return "Ok. Now, please enter a your country. ";
        } else {
            return "This is not an address. Try again";
        }
    }

    private String handleCountry(String text, Session<State> session) {
        if (Validator.isValidCountry(text)) {
            changeState(session, State.CITY);
            session.getDocument().getFieldByType(COUNTRY).setValue(text);
            return "Ok. Now, enter your city.";
        } else {
            return "This is not a country. Try again";
        }
    }

    private String handleCity(String text, Session<State> session) {
        if (Validator.isValidCity(text)) {
            changeState(session, State.TELEPHONE);
            session.getDocument().getFieldByType(CITY).setValue(text);
            return "Ok. Now, please enter a your home phone number";
        } else {
            return "This is not a city. Try again";
        }
    }

    private String handleTelephone(String text, Session<State> session) {
        if (Validator.isValidPhone(text)) {
            changeState(session, State.CELL_PHONE);
            session.getDocument().getFieldByType(HOME_PHONE).setValue(text);
            return "Ok. Now, please enter your cell phone number";
        } else {
            return "This is not a phone number. Try again";
        }
    }

    private String handleCellPhone(String text, Session<State> session) {
        if (Validator.isValidPhone(text)) {
            changeState(session, State.EMAIL);
            session.getDocument().getFieldByType(CELL_PHONE).setValue(text);
            return "Ok. Now, please enter a your e-mail.";
        } else {
            return "This is not a cell phone number. Try again";
        }
    }

    private String handleEmail(String text, Session<State> session) {
        if (Validator.isValidEmail(text)) {
            changeState(session, State.ARRIVAL_DATE);
            session.getDocument().getFieldByType(EMAIL).setValue(text);
            return "Ok. Now, please enter a arrival date.";
        } else {
            return "This is not an e-mail . Try again";
        }
    }

    private String handleArrivalDate(String text, Session<State> session) {
        if (Validator.isValidDate(text)) {
            changeState(session, State.ARRIVAL_TIME);
            session.getDocument().getFieldByType(ARRIVAL_DATE).setValue(text);
            return "Ok. Now, please enter arrival time.";
        } else {
            return "This is not a date. Try again";
        }
    }

    private String handleArrivalTime(String text, Session<State> session) {
        if (Validator.isValidTime(text)) {
            changeState(session, State.DEPARTURE_DATE);
            session.getDocument().getFieldByType(ARRIVAL_TIME).setValue(text);
            return "Ok. Now, please enter a departure date.";
        } else {
            return "This is not a time. Try again";
        }
    }

    private String handleDepartureDate(String text, Session<State> session) {
        if (Validator.isValidDate(text)) {
            changeState(session, State.DEPARTURE_TIME);
            session.getDocument().getFieldByType(DEPARTURE_DATE).setValue(text);
            return "Ok. Now, please enter a departure time.";
        } else {
            return "This is not a date. Try again";
        }
    }

    private String handleDepartureTime(String text, Session<State> session) {
        if (Validator.isValidTime(text)) {
            changeState(session, State.TERMINATED);
            session.getDocument().getFieldByType(DEPARTURE_TIME).setValue(text);
            String file = buildPDF(session);
            return "Ok. That's all foks! Here is your file:\n" + file;
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
