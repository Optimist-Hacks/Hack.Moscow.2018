package com.hackdocs.model.businessModels;

public enum FieldType {

    NAME("name"),
    MIDDLENAME("middle name"),
    LASTNAME("last name"),
    ARRIVAL_DATE("arrival date"),
    ARRIVAL_TIME("arrival time"),
    CELL_PHONE("cell phone"),
    HOME_PHONE("home phone"),
    ADDRESS("address"),
    COUNTRY("country"),
    CITY("city"),
    EMAIL("e-mail"),
    DEPARTURE_DATE("departure date"),
    DEPARTURE_TIME("departure time"),
    CURRENT_DATE(""),
    DATE_FROM("date from"),
    DATE_TO("date to"),

    /**
     * FOR PASSPORT
     */
    IDENTIFICATION_DOCUMENT_TYPE("identification document type"),
    PASSPORT_NUMBER("passport number"),
    PASSPORT_SERIES("passport series"),
    PASSPORT_ISSUE_DATE("passport issue date"),
    REGISTRATION_ADDRESS("registration address"),
    BAD_TYPE("error occured")
    ;

    final String description;

    FieldType(String description) {
        this.description = description;
    }

}
