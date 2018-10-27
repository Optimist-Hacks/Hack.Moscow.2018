package com.hackdocs.pdf;

import com.hackdocs.model.businessModels.Document;
import com.hackdocs.model.businessModels.Field;
import com.hackdocs.model.businessModels.FieldProperties;
import com.hackdocs.model.businessModels.FieldType;
import com.hackdocs.service.PdfService;

import java.util.ArrayList;

public class Temple {

    public static void main(String[] args) {
        PdfService processor = new PdfService();
        Document document = createHotelRegistrationApplication();
        processor.fillDocument(document);
    }

    private static Document createHotelRegistrationApplication() {
        ArrayList<Field> fields = new ArrayList<>();
        Document document = new Document();

        fields.add(new Field(
                FieldType.NAME,
                "Name",
                new FieldProperties(160,645,1,30)
        ));
        fields.add(new Field(
                FieldType.FAMILYNAME,
                "Family name",
                new FieldProperties(390,645,1,30)
        ));
        fields.add(new Field(
                FieldType.ADDRESS,
                "Address",
                new FieldProperties(160,630,2,200)
        ));
        fields.add(new Field(
                FieldType.COUNTRY,
                "Country",
                new FieldProperties(160,602,1,30)
        ));
        fields.add(new Field(
                FieldType.CITY,
                "City",
                new FieldProperties(390,602,1,30)
        ));
        fields.add(new Field(
                FieldType.HOME_PHONE,
                "Home number",
                new FieldProperties(160,587,1,30)
        ));
        fields.add(new Field(
                FieldType.CELL_PHONE,
                "Cell number",
                new FieldProperties(390,587,1,30)
        ));
        fields.add(new Field(
                FieldType.EMAIL,
                "Some email",
                new FieldProperties(160,572,1,30)
        ));
        fields.add(new Field(
                FieldType.DEPARTURE_DATE,
                "22.22.22",
                new FieldProperties(160,532,1,30)
        ));
        fields.add(new Field(
                FieldType.DEPARTURE_TIME,
                "11.11",
                new FieldProperties(480,532,1,30)
        ));

        document.setFields(fields);
        document.setLineWidth(100);
        document.setFileLink("src/main/resources/pdf/application.pdf");

        return document;
    }
}


