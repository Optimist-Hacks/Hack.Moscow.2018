package com.hackdocs.service.logic;

import com.hackdocs.model.Request;
import com.hackdocs.model.businessModels.Document;
import com.hackdocs.service.Session;
import com.hackdocs.service.flow.FlowLogic;
import com.hackdocs.service.logic.hotelHandlers.HotelFieldHandlerDFParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class HotelLogic extends FlowLogic<HotelLogic.State> {

    public static final List<Document> COMPLETED_DOCUMENTS = new ArrayList<>();

    @Autowired
    private HotelFieldHandlerDFParameters handler;

    @Override
    public String process(Request request, Session<State> session) {
        logger.info("СЫРОЙ КВЕРИ ТЕКСТ ДЛЯ СТЕПЫ, ВОЗЬМИТЕ ПОЖАЛУЙСТА " + request.getQueryResult().getQueryText().toLowerCase());
        if (request.getQueryResult().getQueryText().equalsIgnoreCase("cancel")) {
            session.setLogicState(State.INIT);
        }

        switch (session.getLogicState()) {
            case INIT:
                return handler.handleInit(session);
            case FIRST_NAME:
                return handler.handleFirstName(request, session);
            case LAST_NAME:
                return handler.handleLastName(request, session);
            case COUNTRY:
                return handler.handleCountry(request, session);
            case CITY:
                return handler.handleCity(request, session);
            case CELL_PHONE:
                return handler.handleCellPhone(request, session);
            case EMAIL:
                return handler.handleEmail(request, session);
            case DEPARTURE_DATE:
                return handler.handleDepartureDate(request, session);
            case DEPARTURE_TIME:
                return handler.handleDepartureTime(request, session);
//            case ADDRESS:
//                return handleAddress(text, session);
//            case TELEPHONE:
//                return handleTelephone(text, session);
//            case ARRIVAL_DATE:
//                return handleArrivalDate(text, session);
//            case ARRIVAL_TIME:
//                return handleArrivalTime(text, session);
        }

        return defaultResponse();
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
