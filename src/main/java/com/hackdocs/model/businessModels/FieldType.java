package com.hackdocs.model.businessModels;

public enum FieldType {

    NAME("name"),
    MIDDLENAME("middle name"),
    LASTNAME("last name"),
    FAMILYNAME("family name"),
    ENTER_DATE("enter date"),
    CELL_PHONE("cell phone"),
    HOME_PHONE("home phone"),
    ADDRESS("address"),
    COUNTRY("country"),
    CITY("city"),
    EMAIL("e-mail"),
    DEPARTURE_DATE("departure date"),
    DEPARTURE_TIME("departure time"),

    /**
     * FOR PASSPORT
     */
    IDENTIFICATION_DOCUMENT_TYPE("identification document type"),
    PASSPORT_NUMBER("passport number"),
    PASSPORT_SERIES("passport series"),
    PASSPORT_ISSUE_DATE("passport issue date"),
    REGISTRATION_ADDRESS("registration address"),
    ;

    final String description;

    FieldType(String description) {
        this.description = description;
    }

}
