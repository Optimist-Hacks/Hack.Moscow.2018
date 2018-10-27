package com.hackdocs.pdf;

import com.hackdocs.model.businessModels.Document;
import com.hackdocs.model.businessModels.Field;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

class PdfProcessor implements BasePdfProcessor {

    @Override
    public void fillDocument(Document document) {


        PdfStamper stamper = null;
        PdfReader reader;
        BaseFont font = null;

        try {
            reader = new PdfReader(document.getFileLink()); // input PDF
            stamper = new PdfStamper(reader,
                    new FileOutputStream("documentResult.pdf")); // output PDF
            font = BaseFont.createFont(
                    BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED  ); // set font
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        assert stamper != null;
        assert font != null;

        PdfContentByte over = stamper.getOverContent(1);

        for (Field field : document.getFields()) {
            for (int lineIndex=0; lineIndex<field.getProperties().getNumbOfLines(); lineIndex++) {
                over.beginText();
                over.setFontAndSize(font, 10);
                over.setTextMatrix(field.getProperties().getXCoord()
                        , field.getProperties().getYCoord() - 15*lineIndex);
                over.showText(field.getValue());
                over.endText();
            }
        }


        try {
            stamper.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
