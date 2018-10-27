package com.hackdocs.model.businessModels;

import lombok.Data;

import java.util.List;

@Data
public class Document {
    List<Field> fields;
    int lineWidth;
    String fileLink;
}
