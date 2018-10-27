package com.hackdocs.pfd;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
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

import static com.hackdocs.pfd.FieldModels.*;

class PdfProcessor implements BasePdfProcessor {

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

    @Override
    public HashMap<Integer, FieldModels> process(String path) {
        readAndProcessDocument(path);
        return fields;
    }

    @Override
    public void writeToLine(int lineIndex, String value) {
        documentLines.set(lineIndex, insertToLine(documentLines.get(lineIndex), value));
    }

    @Override
    public void writeDocument() {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("resultDocument.pdf"));
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);

        for (String line : documentLines) {
            try {
                document.add(new Paragraph(line, font));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }

        document.close();
    }

    private void readAndProcessDocument(String path) {
        PdfReader reader = null;
        try {
            reader = new PdfReader(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        documentLines = new ArrayList<>();

        assert reader != null;
        for (int i = 1; i <= reader.getNumberOfPages(); ++i) {
            TextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
            String text = null;
            try {
                text = PdfTextExtractor.getTextFromPage(reader, i, strategy);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int count = 0;

            assert text != null;
            for (String line : text.split("\n")) {
                documentLines.add(line);

                FieldModels model = processLine(line);
                if (model != null) {
                    fields.put(count, model);
                }
                count++;
            }
        }
        reader.close();
    }

    @Nullable
    private FieldModels processLine(@NotNull String line) {
        //BAD HARDCODE
        for (FieldModels model : models) {
            for (String keyWord : model.getValues()) {
                if (line.contains(keyWord + " _")) {
                    return model;
                }
            }
        }
        return null;
    }

    private String insertToLine(@NotNull String line, @NotNull String value) {
        ArrayList<Character> arrayValue = new ArrayList<>();
        for (Character ch : value.toCharArray()) {
            arrayValue.add(ch);
        }

        for (Character ch : value.toCharArray()) {
            if (!line.contains("_")) break;
            line = line.replaceFirst("_", ch.toString());
            arrayValue.remove(0);
        }

        line = line.trim();

        if (!arrayValue.isEmpty()) {
            StringBuilder lineBuilder = new StringBuilder(line);
            for (Character ch : arrayValue) {
                lineBuilder.append(ch);
            }
            line = lineBuilder.toString();
        }

        return line;
    }

}
