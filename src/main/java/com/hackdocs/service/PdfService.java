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

@Service
public class PdfService {

    private static Logger logger = LoggerFactory.getLogger(PdfService.class);

    private static final String OUT_FOLDER = "out_documents";
    private static final String PDF = "pdf";

    public String fillDocument(Document document) {
        PdfStamper stamper = null;
        PdfReader reader;
        BaseFont font = null;


        Path path = prepareDocumentPath();

        try {
            reader = new PdfReader(document.getFileLink()); // input PDF
            stamper = new PdfStamper(reader, new FileOutputStream(path.toString())); // output PDF
            font = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font
        } catch (IOException | DocumentException e) {
            logger.error("error", e);
        }

        assert stamper != null;
        assert font != null;

        PdfContentByte over = stamper.getOverContent(1);

        for (Field field : document.getFields()) {
            for (int lineIndex = 0; lineIndex < field.getProperties().getNumbOfLines(); lineIndex++) {
                over.beginText();
                over.setFontAndSize(font, 10);
                over.setTextMatrix(field.getProperties().getXCoord(), field.getProperties().getYCoord() - 15 * lineIndex);
                over.showText(field.getValue());
                over.endText();
            }
        }

        try {
            stamper.close();
        } catch (DocumentException | IOException e) {
            logger.error("error", e);
        }

        return path.getFileName().toString();
    }

    private Path prepareDocumentPath() {
        Path path = Paths.get(OUT_FOLDER);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                logger.error("Can' create folder", e);
            }
        }
        return path.resolve(System.currentTimeMillis() + "." + PDF);
    }

    public Path getDocument(String path) {
        return Paths.get(OUT_FOLDER).resolve(path);
    }

}