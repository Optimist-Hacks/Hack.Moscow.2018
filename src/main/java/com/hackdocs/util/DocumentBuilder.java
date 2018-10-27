package com.hackdocs.util;

import com.hackdocs.model.businessModels.Document;
import com.hackdocs.model.businessModels.Field;
import com.hackdocs.model.businessModels.FieldProperties;
import com.hackdocs.model.businessModels.FieldType;

import java.util.ArrayList;

public interface DocumentBuilder {

    static Document getHotelDocument() {
        ArrayList<Field> fields = new ArrayList<>();
        Document hotelDoc = new Document();

        fields.add(new Field(
                FieldType.NAME,
                new FieldProperties(160,645,1,30)
        ));
        fields.add(new Field(
                FieldType.LASTNAME,
                new FieldProperties(390,645,1,30)
        ));
        fields.add(new Field(
                FieldType.ADDRESS,
                new FieldProperties(160,630,2,200)
        ));
        fields.add(new Field(
                FieldType.COUNTRY,
                new FieldProperties(160,602,1,30)
        ));
        fields.add(new Field(
                FieldType.CITY,
                new FieldProperties(390,602,1,30)
        ));
        fields.add(new Field(
                FieldType.HOME_PHONE,
                new FieldProperties(160,587,1,30)
        ));
        fields.add(new Field(
                FieldType.CELL_PHONE,
                new FieldProperties(390,587,1,30)
        ));
        fields.add(new Field(
                FieldType.EMAIL,
                new FieldProperties(160,572,1,30)
        ));
        fields.add(new Field(
                FieldType.DEPARTURE_DATE,
                new FieldProperties(160,532,1,30)
        ));
        fields.add(new Field(
                FieldType.DEPARTURE_TIME,
                new FieldProperties(480,532,1,30)
        ));

        hotelDoc.setFields(fields);
        hotelDoc.setLineWidth(100);
        hotelDoc.setFileLink("src/main/resources/pdf/application.pdf");

        return hotelDoc;
    }
}