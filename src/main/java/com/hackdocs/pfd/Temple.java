package com.hackdocs.pfd;

import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Temple {

    public static void main(String[] args) throws IOException, DocumentException {

        PdfProcessor processor = new PdfProcessor();

        HashMap<Integer, FieldModels> models = processor.process("src/main/resources/pdf/example3.pdf");

        for (Map.Entry<Integer, FieldModels> entry : models.entrySet()) {
            processor.writeToLine(entry.getKey(), entry.getValue().toString());
        }

        processor.writeDocument();

    }



}


