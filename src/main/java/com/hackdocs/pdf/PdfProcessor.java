package com.hackdocs.pdf;

import com.hackdocs.model.businessModels.Field;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.hackdocs.pdf.FieldModels.*;

class PdfProcessor {

    private ArrayList<FieldModels> models;

    private HashMap<Integer, FieldModels> fields = new HashMap<>();
    private ArrayList<String> documentLines;

    PdfProcessor() {
        models = new ArrayList<>();
        models.add(NAME_FIELD);
        models.add(SURNAME_FIELD);
        models.add(DATA_FIELD);
        models.add(MALE_FIELD);
        models.add(FAVORITE_PORN_FIELD);
    }
/*
    public void fillDocument() {


        PdfStamper stamper = null;
        PdfReader reader = null;
        BaseFont font = null;

        try {
            reader = new PdfReader("src/main/resources/pdf/application.pdf"); // input PDF
            stamper = new PdfStamper(reader,
                    new FileOutputStream("documentResult.pdf")); // output PDF
            font = BaseFont.createFont(
                    BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        assert stamper != null;
        assert font != null;




        // get object for writing over the existing content;
        // you can also use getUnderContent for writing in the bottom layer
        PdfContentByte over = stamper.getOverContent(i);

        // write text
        over.beginText();
        over.setFontAndSize(font, 10);    // set font and size
        over.setTextMatrix(107, 740);   // set x,y position (0,0 is at the bottom left)
        over.showText("I can write at page " + i);  // set text
        over.endText();


        try {
            stamper.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

*/
}
