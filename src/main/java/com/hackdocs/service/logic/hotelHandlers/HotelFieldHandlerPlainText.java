package com.hackdocs.service.logic.hotelHandlers;

import com.hackdocs.model.Request;
import com.hackdocs.service.PdfService;
import com.hackdocs.service.Session;
import com.hackdocs.service.flow.FlowLogic;
import com.hackdocs.service.logic.HotelLogic;
import com.hackdocs.validators.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Predicate;

import static com.hackdocs.model.businessModels.FieldType.*;
import static com.hackdocs.service.flow.FlowLogic.changeState;

public class HotelFieldHandlerPlainText {

    protected static Logger logger = LoggerFactory.getLogger(FlowLogic.class);

    @Autowired
    private PdfService pdfService;

    private Predicate<String> notEmprty = (s) -> s != null && !s.isEmpty();

    private String handleInit(Session<HotelLogic.State> session) {
        changeState(session, HotelLogic.State.FIRST_NAME);
        return "Hello! You want to create hotel check-in document. What is your name?";
    }

    private String handleFirstName(String text, Session<HotelLogic.State> session) {
        if (Validator.isValidName(text)) {
            changeState(session, HotelLogic.State.LAST_NAME);
            session.getDocument().getFieldByType(NAME).setValue(text);
            return "Ok. Now, please enter last name";
        } else {
            return "This is not a name. Try again";
        }
    }

    private String handleLastName(String text, Session<HotelLogic.State> session) {

        if (Validator.isValidName(text)) {
            changeState(session, HotelLogic.State.ADDRESS);
            session.getDocument().getFieldByType(LASTNAME).setValue(text);
            return "Ok. Now, please enter a your address";
        } else {
            return "This is not a last name. Try again";
        }
    }


    private String handleCountry(String text, Session<HotelLogic.State> session) {
        if (Validator.isValidCountry(text)) {
            changeState(session, HotelLogic.State.CITY);
            session.getDocument().getFieldByType(COUNTRY).setValue(text);
            return "Ok. Now, enter your city.";
        } else {
            return "This is not a country. Try again";
        }
    }

    private String handleCity(String text, Session<HotelLogic.State> session) {
        if (Validator.isValidCity(text)) {
            changeState(session, HotelLogic.State.TELEPHONE);
            session.getDocument().getFieldByType(CITY).setValue(text);
            return "Ok. Now, please enter a your home phone number";
        } else {
            return "This is not a city. Try again";
        }
    }

    private String handleCellPhone(String text, Session<HotelLogic.State> session) {
        if (Validator.isValidPhone(text)) {
            changeState(session, HotelLogic.State.EMAIL);
            session.getDocument().getFieldByType(CELL_PHONE).setValue(text);
            return "Ok. Now, please enter a your e-mail.";
        } else {
            return "This is not a cell phone number. Try again";
        }
    }

    private String handleEmail(String text, Session<HotelLogic.State> session) {
        if (Validator.isValidEmail(text)) {
            changeState(session, HotelLogic.State.ARRIVAL_DATE);
            session.getDocument().getFieldByType(EMAIL).setValue(text);
            return "Ok. Now, please enter a arrival date.";
        } else {
            return "This is not an e-mail . Try again";
        }
    }

    private String handleDepartureDate(String text, Session<HotelLogic.State> session) {
        if (Validator.isValidDate(text)) {
            changeState(session, HotelLogic.State.DEPARTURE_TIME);
            session.getDocument().getFieldByType(DEPARTURE_DATE).setValue(text);
            return "Ok. Now, please enter a departure time.";
        } else {
            return "This is not a date. Try again";
        }
    }

    private String handleDepartureTime(String text, Session<HotelLogic.State> session) {
        if (Validator.isValidTime(text)) {
            changeState(session, HotelLogic.State.TERMINATED);
            session.getDocument().getFieldByType(DEPARTURE_TIME).setValue(text);

            String file = pdfService.fillDocument(session.getDocument());
            return String.format("Ok. That's all folks! Here is your file:\nhttps://techdrive.pro/api/v1/pdf/%s", file);
        } else {
            return "This is not a time. Try again";
        }
    }


    private String getRequestPlainText(Request request) {
        return request.getQueryResult().getQueryText();
    }

}
