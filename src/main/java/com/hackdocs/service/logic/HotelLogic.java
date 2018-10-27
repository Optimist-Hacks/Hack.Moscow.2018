package com.hackdocs.service.logic;

import com.hackdocs.model.Request;
import com.hackdocs.service.Session;
import com.hackdocs.service.flow.FlowLogic;
import com.hackdocs.validators.Validator;
import org.springframework.stereotype.Service;

import static com.hackdocs.model.businessModels.FieldType.*;

@Service
public class HotelLogic extends FlowLogic<HotelLogic.State> {

    @Override
    public String process(Request request, Session<State> session) {
        switch (session.getLogicState()) {
            case INIT:
                return handleInit(session);
            case FIRST_NAME:
                return handleFirstName(request, session);
            case LAST_NAME:
                return handleLastName(request, session);
//            case ADDRESS:
//                return handleAddress(text, session);
            case COUNTRY:
                return handleCountry(request, session);
            case CITY:
                return handleCity(request, session);
//            case TELEPHONE:
//                return handleTelephone(text, session);
            case CELL_PHONE:
                return handleCellPhone(request, session);
            case EMAIL:
                return handleEmail(request, session);
//            case ARRIVAL_DATE:
//                return handleArrivalDate(text, session);
//            case ARRIVAL_TIME:
//                return handleArrivalTime(text, session);
            case DEPARTURE_DATE:
                return handleDepartureDate(request, session);
            case DEPARTURE_TIME:
                return handleDepartureTime(request, session);
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
            return String.format("Ok. That's all folks! Here is your file:\nhttps://techdrive.pro/api/v1/pdf/%s", file);
        } else {
            return "This is not a time. Try again";
        }
    }


    private String handleFirstName(Request request, Session<State> session) {
        String firstName = request.getQueryResult().getParameters().get("firstName");
        if (firstName != null) {
            changeState(session, State.LAST_NAME);
            session.getDocument().getFieldByType(NAME).setValue(firstName);
            return "Ok. Now, please enter last name";
        } else {
            return "This is not a name. Try again";
        }
    }

    private String handleLastName(Request request, Session<State> session) {

        String lastName = request.getQueryResult().getParameters().get("lastName");

        if (lastName != null) {
            changeState(session, State.COUNTRY);
            session.getDocument().getFieldByType(LASTNAME).setValue(lastName);
            return "Ok. Now, please enter a your address";
        } else {
            return "This is not a last name. Try again";
        }
    }

    private String handleCountry(Request request, Session<State> session) {
        String country = request.getQueryResult().getParameters().get("country");
        if (country != null) {
            changeState(session, State.CITY);
            session.getDocument().getFieldByType(COUNTRY).setValue(country);
            return "Ok. Now, enter your city.";
        } else {
            return "This is not a country. Try again";
        }
    }

    private String handleCity(Request request, Session<State> session) {

        String city = request.getQueryResult().getParameters().get("city");
        if (city != null) {
            changeState(session, State.CELL_PHONE);
            session.getDocument().getFieldByType(CITY).setValue(city);
            return "Ok. Now, please enter a your home phone number";
        } else {
            return "This is not a city. Try again";
        }
    }

    private String handleCellPhone(Request request, Session<State> session) {

        String cellPhone = request.getQueryResult().getParameters().get("cellPhone");
        if (cellPhone != null) {
            changeState(session, State.EMAIL);
            session.getDocument().getFieldByType(CELL_PHONE).setValue(cellPhone);
            return "Ok. Now, please enter a your e-mail.";
        } else {
            return "This is not a cell phone number. Try again";
        }
    }

    private String handleEmail(Request request, Session<State> session) {

        String email = request.getQueryResult().getParameters().get("email");
        if (email != null) {
            changeState(session, State.DEPARTURE_DATE);
            session.getDocument().getFieldByType(EMAIL).setValue(email);
            return "Ok. Now, please enter a arrival date.";
        } else {
            return "This is not an e-mail . Try again";
        }
    }

    private String handleDepartureDate(Request request, Session<State> session) {

        String depDate = request.getQueryResult().getParameters().get("depDate");
        if (depDate != null) {
            changeState(session, State.DEPARTURE_TIME);
            session.getDocument().getFieldByType(DEPARTURE_DATE).setValue(depDate);
            return "Ok. Now, please enter a departure time.";
        } else {
            return "This is not a date. Try again";
        }
    }

    private String handleDepartureTime(Request request, Session<State> session) {
        String depTime = request.getQueryResult().getParameters().get("depTime");
        if (depTime != null) {
            changeState(session, State.TERMINATED);
            session.getDocument().getFieldByType(DEPARTURE_TIME).setValue(depTime);
            String file = buildPDF(session);
            return String.format("Ok. That's all folks! Here is your file:\nhttps://techdrive.pro/api/v1/pdf/%s", file);
        } else {
            return "This is not a time. Try again";
        }
    }


    private String getRequestPlainText(Request request) {
        return request.getQueryResult().getQueryText();
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
