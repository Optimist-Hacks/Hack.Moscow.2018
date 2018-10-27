package com.hackdocs.model.businessModels;


import lombok.Data;

@Data
public class Field {

    FieldType type;
    String value;
    FieldProperties properties;


    public Field(FieldType type, FieldProperties properties) {
        this.type = type;
        this.properties = properties;
    }
}
