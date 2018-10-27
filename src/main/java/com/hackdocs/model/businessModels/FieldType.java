package com.hackdocs.model.businessModels;

public enum FieldType {

    NAME("name"),
    MIDDLENAME("middle name"),
    LASTNAME("last name"),
    IDENTIFICATION_DOCUMENT_TYPE("identification document type"),
    PASSPORT_NUMBER("passport number"),
    PASSPORT_SERIES("passport series"),
    PASSPORT_ISSUE_DATE("passport issue date"),
    REGISTRATION_ADDRESS("registration address"),
    ENTER_DATE("enter date");

    final String description;

    FieldType(String description) {
        this.description = description;
    }

}
