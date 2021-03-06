package com.hackdocs.util;

import com.hackdocs.model.businessModels.Document;
import com.hackdocs.model.businessModels.Field;
import com.hackdocs.model.businessModels.FieldProperties;
import com.hackdocs.model.businessModels.FieldType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public interface DocumentBuilder {

    static Document getHotelDocument() {
        ArrayList<Field> fields = new ArrayList<>();
        Document hotelDoc = new Document();
        DateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();


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
                new FieldProperties(160,537,1,30)
        ));
        fields.add(new Field(
                FieldType.DEPARTURE_TIME,
                new FieldProperties(480,537,1,30)
        ));
        fields.add(new Field(
                FieldType.ARRIVAL_DATE,
                dateFormat.format(date),
                new FieldProperties(160,547,1,30)
        ));
        fields.add(new Field(
                FieldType.ARRIVAL_TIME,
                timeFormat.format(date),
                new FieldProperties(480,547,1,30)
        ));

        hotelDoc.setFields(fields);
        hotelDoc.setLineWidth(100);
        hotelDoc.setFileLink("src/main/resources/pdf/hotelApplication.pdf");

        return hotelDoc;
    }

    static Document getVocationDocument() {
        ArrayList<Field> fields = new ArrayList<>();
        Document vocationDoc = new Document();
        DateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");
        Date date = new Date();

        fields.add(new Field(
                FieldType.NAME,
                new FieldProperties(175,655,1,30)
        ));
        fields.add(new Field(
                FieldType.LASTNAME,
                new FieldProperties(190,630,1,30)
        ));
        fields.add(new Field(
                FieldType.DATE_FROM,
                new FieldProperties(200,608,1,30)
        ));
        fields.add(new Field(
                FieldType.DATE_TO,
                new FieldProperties(315,608,1,30)
        ));
        fields.add(new Field(
                FieldType.CURRENT_DATE,
                dateFormat.format(date),
                new FieldProperties(290,560,1,30)
        ));
        fields.add(new Field(
                FieldType.CURRENT_DATE,
                dateFormat.format(date),
                new FieldProperties(290,475,1,30)
        ));

        vocationDoc.setFields(fields);
        vocationDoc.setLineWidth(100);
        vocationDoc.setFileLink("src/main/resources/pdf/vocationApplication.pdf");

        return vocationDoc;
    }
}
