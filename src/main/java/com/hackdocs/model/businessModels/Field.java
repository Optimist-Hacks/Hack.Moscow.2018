package com.hackdocs.model.businessModels;


import lombok.Data;

@Data
public class Field {

    FieldType type;
    String value;
    FieldProperties coords;

}
