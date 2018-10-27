package com.hackdocs.pdf;

import com.hackdocs.model.businessModels.Document;
import com.hackdocs.model.businessModels.Field;
import com.hackdocs.model.businessModels.FieldProperties;
import com.hackdocs.model.businessModels.FieldType;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Temple {
    public static void main(String[] args) throws IOException, DocumentException {

        /* example inspired from "iText in action" (2006), chapter 2 */

        PdfReader reader = new PdfReader("src/main/resources/pdf/application.pdf"); // input PDF
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream("documentResult.pdf")); // output PDF
        BaseFont bf = BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font

        //loop on pages (1-based)
        for (int i=1; i<=reader.getNumberOfPages(); i++){

            // get object for writing over the existing content;
            // you can also use getUnderContent for writing in the bottom layer
            PdfContentByte over = stamper.getOverContent(i);

            // write text
            over.beginText();
            over.setFontAndSize(bf, 10);    // set font and size
            over.setTextMatrix(107, 740);   // set x,y position (0,0 is at the bottom left)
            over.showText("I can write at page " + i);  // set text
            over.endText();
        }

        stamper.close();

    }


//    private Document getTestDocument() {
//        ArrayList<Field> fields = new ArrayList<>();
//        Document document = new Document();
//
//        fields.add(new Field(
//                FieldType.NAME,
//                new FieldProperties()
//        ));
//        fields.add(new Field(
//                FieldType.FAMILYNAME,
//                new FieldProperties()
//        ));
//        fields.add(new Field(
//                FieldType.ADDRESS,
//                new FieldProperties()
//        ));
//        fields.add(new Field(
//                FieldType,
//                new FieldProperties()
//        ));
//        fields.add(new Field(
//                FieldType.NAME,
//                new FieldProperties()
//        ));
//        fields.add(new Field(
//                FieldType.NAME,
//                new FieldProperties()
//        ));
//        fields.add(new Field(
//                FieldType.NAME,
//                new FieldProperties()
//        ));
//        fields.add(new Field(
//                FieldType.NAME,
//                new FieldProperties()
//        ));
//        fields.add(new Field(
//                FieldType.NAME,
//                new FieldProperties()
//        ));
//        fields.add(new Field(
//                FieldType.NAME,
//                new FieldProperties()
//        ));
//
//        return document;
//    }
}


