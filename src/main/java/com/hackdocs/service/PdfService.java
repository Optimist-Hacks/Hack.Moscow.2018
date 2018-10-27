package com.hackdocs.service;

import com.hackdocs.model.businessModels.Document;
import com.hackdocs.model.businessModels.Field;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class PdfService {

    private static Logger logger = LoggerFactory.getLogger(PdfService.class);

    private static final String OUT_FOLDER = "out_documents";
    private static final String PDF = "pdf";

    public void fillDocument(Document document) {
        PdfStamper stamper = null;
        PdfReader reader;
        BaseFont font = null;

        try {
            reader = new PdfReader(document.getFileLink()); // input PDF
            stamper = new PdfStamper(reader, new FileOutputStream(prepareDocumentPath())); // output PDF
            font = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font
        } catch (IOException | DocumentException e) {
            logger.error("error", e);
        }

        assert stamper != null;
        assert font != null;

        PdfContentByte over = stamper.getOverContent(1);


        for (Field field : document.getFields()) {
            ArrayList<String> lines = fieldLines(field);
            for (int lineIndex = 0; lineIndex < field.getProperties().getNumbOfLines(); lineIndex++) {
                over.beginText();
                over.setFontAndSize(font, 10);
                over.setTextMatrix(field.getProperties().getXCoord(), field.getProperties().getYCoord() - 15 * lineIndex);
                over.showText(lines.get(lineIndex));
                over.endText();
            }
        }

        try {
            stamper.close();
        } catch (DocumentException | IOException e) {
            logger.error("error", e);
        }
    }

    private ArrayList<String> fieldLines(Field field) {
        ArrayList<String> result = new ArrayList<>();
        int maxLength = field.getProperties().getMaxLength();
        String value = field.getValue();
        for (int i=0; i<field.getProperties().getNumbOfLines(); i++) {
            if (value.length() <= maxLength) {
                result.add(value);
                break;
            }
            String temp = value.substring(0, maxLength);
            result.add(temp);
            value = value.replace(temp, "");
        }

        return result;
    }

    private String prepareDocumentPath() {
        Path path = Paths.get(OUT_FOLDER);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                logger.error("Can' create folder", e);
            }
        }
        return path.resolve(System.currentTimeMillis() + "." + PDF).toString();
    }

    public Path getDocument(String path) {
        return Paths.get(OUT_FOLDER).resolve(path);
    }

}
