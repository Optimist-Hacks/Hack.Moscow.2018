package com.hackdocs.service;

import com.hackdocs.model.businessModels.Document;
import com.hackdocs.model.businessModels.Field;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.RendererException;
import org.ghost4j.renderer.SimpleRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {

    private static Logger logger = LoggerFactory.getLogger(PdfService.class);

    private static final String OUT_FOLDER = "out_documents";
    private static final String PDF = "pdf";
    private static final String PNG = "png";

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
            if (field.getValue() != null) {
                ArrayList<String> lines = fieldLines(field);
                for (int lineIndex = 0; lineIndex < field.getProperties().getNumbOfLines(); lineIndex++) {
                    over.beginText();
                    over.setFontAndSize(font, 10);
                    over.setTextMatrix(field.getProperties().getXCoord(), field.getProperties().getYCoord() - 15 * lineIndex);
                    over.showText(lines.get(lineIndex));
                    over.endText();
                }
            }
        }

        try {
            stamper.close();
        } catch (DocumentException | IOException e) {
            logger.error("error", e);
        }

        String fileName = path.getFileName().toString();
        String imageName = saveImage(pdfToImage("/home/HackDocsBot/out_documents/" + fileName)).getFileName().toString();
        document.setPdf("https://techdrive.pro/api/v1/pdf/" + fileName);
        document.setPng("http://techdrive.pro/api/v1/pdf/" + imageName);
        return imageName;
    }

    private ArrayList<String> fieldLines(Field field) {
        ArrayList<String> result = new ArrayList<>();
        int maxLength = field.getProperties().getMaxLength();
        String value = field.getValue();
        for (int i = 0; i < field.getProperties().getNumbOfLines(); i++) {
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

    private Path prepareImagePath() {
        Path path = Paths.get(OUT_FOLDER);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                logger.error("Can' create folder", e);
            }
        }
        return path.resolve(System.currentTimeMillis() + "." + PNG);
    }

    public Path getDocument(String path) {
        return Paths.get(OUT_FOLDER).resolve(path);
    }

    private Path saveImage(Image image) {
        Path path = prepareImagePath();
        try {
            ImageIO.write((RenderedImage) image, PNG, new File(path.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    private Image pdfToImage(String path) {

        PDFDocument document = new PDFDocument();
        try {
            document.load(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleRenderer renderer = new SimpleRenderer();
        renderer.setResolution(300);
        List<Image> images = new ArrayList<>();
        try {
            images = renderer.render(document);
        } catch (IOException | RendererException | org.ghost4j.document.DocumentException e) {
            e.printStackTrace();
        }

        return images.get(0);
    }

}
