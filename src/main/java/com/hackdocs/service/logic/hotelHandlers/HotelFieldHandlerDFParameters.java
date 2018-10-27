package com.hackdocs.service.logic.hotelHandlers;

import com.hackdocs.model.Request;
import com.hackdocs.service.PdfService;
import com.hackdocs.service.Session;
import com.hackdocs.service.flow.FlowLogic;
import com.hackdocs.service.logic.HotelLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.function.Predicate;

import static com.hackdocs.model.businessModels.FieldType.*;
import static com.hackdocs.service.flow.FlowLogic.changeState;
import static com.hackdocs.service.logic.HotelLogic.COMPLETED_DOCUMENTS;

@Component
public class HotelFieldHandlerDFParameters {

    protected static Logger logger = LoggerFactory.getLogger(FlowLogic.class);

    @Autowired
    private PdfService pdfService;

    private Predicate<String> notEmprty = (s) -> s != null && !s.isEmpty();

    public String handleInit(Session<HotelLogic.State> session) {
        changeState(session, HotelLogic.State.FIRST_NAME);
        return "Hello! You want to create hotel check-in document. What is your name?";
    }

    public String handleFirstName(Request request, Session<HotelLogic.State> session) {
        String firstName = request.getQueryResult().getParameters().get("firstName");
        if (notEmprty.test(firstName)) {
            changeState(session, HotelLogic.State.LAST_NAME);
            session.getDocument().getFieldByType(NAME).setValue(firstName);
            logger.info("Я ЗАЛОГГИРОВАЛ ИМЯ ЛЮБИМОЕ ТВОЁ:  " + session.getDocument().getFieldByType(NAME).getValue());
            return "Ok. Now, please enter last name";
        } else {
            return "This is not a name. Try again";
        }
    }

    public String handleLastName(Request request, Session<HotelLogic.State> session) {

        String lastName = request.getQueryResult().getParameters().get("lastName");

        if (notEmprty.test(lastName)) {
            changeState(session, HotelLogic.State.COUNTRY);
            session.getDocument().getFieldByType(LASTNAME).setValue(lastName);
            logger.info("Я ЗАЛОГГИРОВАЛ ФА:  " + session.getDocument().getFieldByType(NAME).getValue());
            return "Ok. Now, please enter a your country";
        } else {
            return "This is not a last name. Try again";
        }
    }

    public String handleCountry(Request request, Session<HotelLogic.State> session) {
        String country = request.getQueryResult().getParameters().get("country");
        if (notEmprty.test(country)) {
            changeState(session, HotelLogic.State.CITY);
            session.getDocument().getFieldByType(COUNTRY).setValue(country);
            return "Ok. Now, enter your city.";
        } else {
            return "This is not a country. Try again";
        }
    }

    public String handleCity(Request request, Session<HotelLogic.State> session) {

        String city = request.getQueryResult().getParameters().get("city");
        if (notEmprty.test(city)) {
            changeState(session, HotelLogic.State.CELL_PHONE);
            session.getDocument().getFieldByType(CITY).setValue(city);
            return "Ok. Now, please enter a your cell phone number";
        } else {
            return "This is not a city. Try again";
        }
    }

    public String handleCellPhone(Request request, Session<HotelLogic.State> session) {

        String cellPhone = request.getQueryResult().getParameters().get("cellPhone");
        if (notEmprty.test(cellPhone)) {
            changeState(session, HotelLogic.State.EMAIL);
            session.getDocument().getFieldByType(CELL_PHONE).setValue(cellPhone);
            return "Ok. Now, please enter a your e-mail.";
        } else {
            return "This is not a cell phone number. Try again";
        }
    }

    public String handleEmail(Request request, Session<HotelLogic.State> session) {

        String email = request.getQueryResult().getParameters().get("email");
        if (notEmprty.test(email)) {
            changeState(session, HotelLogic.State.DEPARTURE_DATE);
            session.getDocument().getFieldByType(EMAIL).setValue(email);
            return "Ok. Now, please enter a departure date.";
        } else {
            return "This is not an e-mail . Try again";
        }
    }

    public String handleDepartureDate(Request request, Session<HotelLogic.State> session) {

        String depDate = request.getQueryResult().getParameters().get("depDate");
        if (notEmprty.test(depDate)) {
            changeState(session, HotelLogic.State.DEPARTURE_TIME);
            session.getDocument().getFieldByType(DEPARTURE_DATE).setValue(depDate);
            return "Ok. Now, please enter a departure time.";
        } else {
            return "This is not a date. Try again";
        }
    }

    public String handleDepartureTime(Request request, Session<HotelLogic.State> session) {
        String depTime = request.getQueryResult().getParameters().get("depTime");
        if (notEmprty.test(depTime)) {
            changeState(session, HotelLogic.State.TERMINATED);
            session.getDocument().getFieldByType(DEPARTURE_TIME).setValue(depTime);
            Image file = pdfService.fillDocument(session.getDocument());
            COMPLETED_DOCUMENTS.add(session.getDocument());
            return String.format("Ok. That's all folks! Here is your file:\nhttps://techdrive.pro/api/v1/pdf/%s", file);
        } else {
            return "This is not a time. Try again";
        }
    }

}
