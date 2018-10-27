package com.hackdocs.controller;

import com.hackdocs.service.PdfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping(value = "/api/v1/pdf")
public class PdfController {

    private static Logger logger = LoggerFactory.getLogger(PdfController.class);
    private static final String APPLICATION_PDF = "application/pdf";

    private final PdfService pdfService;

    @Autowired
    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @ResponseBody
    @RequestMapping(value = "{path}", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public byte[] helloWorld(@PathVariable String path) throws IOException {
        logger.info("Receive pdf request path = " + path);

        logger.info("Receive qr request");
        Path document = pdfService.getDocument(path);

        return Files.readAllBytes(document);
    }

}
