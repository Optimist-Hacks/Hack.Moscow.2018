package com.hackdocs.pdf;

import com.hackdocs.model.businessModels.Document;
import com.hackdocs.model.businessModels.Field;
import com.hackdocs.model.businessModels.FieldProperties;
import com.hackdocs.model.businessModels.FieldType;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.ArrayList;

public class Temple {
    public static void main(String[] args) throws IOException, DocumentException {


    }

    private Document createHotelRegistrationApplication() {
        ArrayList<Field> fields = new ArrayList<>();
        Document document = new Document();

        fields.add(new Field(
                FieldType.NAME,
                new FieldProperties(150,50,1,30)
        ));
        fields.add(new Field(
                FieldType.FAMILYNAME,
                new FieldProperties(360,100,1,30)
        ));
        fields.add(new Field(
                FieldType.ADDRESS,
                new FieldProperties(150,150,2,200)
        ));
        fields.add(new Field(
                FieldType.COUNTRY,
                new FieldProperties(150,200,1,30)
        ));
        fields.add(new Field(
                FieldType.CITY,
                new FieldProperties(360,250,1,30)
        ));
        fields.add(new Field(
                FieldType.HOME_PHONE,
                new FieldProperties(150,300,1,30)
        ));
        fields.add(new Field(
                FieldType.CELL_PHONE,
                new FieldProperties(360,350,1,30)
        ));
        fields.add(new Field(
                FieldType.EMAIL,
                new FieldProperties(150,400,1,30)
        ));
        fields.add(new Field(
                FieldType.DEPARTURE_DATE,
                new FieldProperties(150,450,1,30)
        ));
        fields.add(new Field(
                FieldType.DEPARTURE_TIME,
                new FieldProperties(480,500,1,30)
        ));

        document.setFields(fields);
        document.setLineWidth(100);
        document.setFileLink("src/main/resources/pdf/application.pdf");

        return document;
    }
}


