package com.hackdocs.pfd;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

public class Temple {

    public static void main(String[] args) throws IOException, DocumentException {

        PdfProcessor processor = new PdfProcessor();

        processor.process("src/main/resources/pdf/example2.pdf");

    }



}


