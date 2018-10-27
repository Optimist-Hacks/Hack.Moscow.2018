package com.hackdocs.model.businessModels;

import lombok.Data;

import java.util.List;

@Data
public class Document {
    List<Field> fields;
    int lineWidth;
    String fileLink;
    String id = "123";

    public Field getFieldByType(FieldType type) {
        return fields.stream()
                .filter((field -> field.type.equals(type)))
                .findFirst()
                .orElseThrow(NoSuchFieldError::new);
    }

}
