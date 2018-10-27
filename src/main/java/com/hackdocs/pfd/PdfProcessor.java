package com.hackdocs.pfd;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

class PdfProcessor {

    void process(String path) throws IOException, DocumentException {

        writeDocument(readAndProcessDocument(path));

    }

    private String processLine(String line) {
        return insertToLine(line, "WI12312323112312321312");
    }

    private String insertToLine(String line, String value) {
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

    private ArrayList<String> readAndProcessDocument(String path) throws IOException {
        PdfReader reader = new PdfReader(path);

        ArrayList<String> parsedDocument = new ArrayList<>();

        for (int i = 1; i <= reader.getNumberOfPages(); ++i) {
            TextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
            String text = PdfTextExtractor.getTextFromPage(reader, i, strategy);

            for (String line : text.split("\n")) {
                parsedDocument.add(processLine(line));
            }
        }

        // убираем за собой
        reader.close();

        return parsedDocument;
    }

    private void writeDocument(ArrayList<String> lines) throws IOException, DocumentException {
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream("resultDocument.pdf"));

        document.open();

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);

        for (String line : lines) {
            //document.add(new Chunk(line, font));
            document.add(new Paragraph(line, font));
        }

        document.close();
    }

}
