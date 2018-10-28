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
@RequestMapping(value = "/api/v1/png")
public class PngController {

    private static Logger logger = LoggerFactory.getLogger(PngController.class);
    private static final String IMAGE_PNG = "image/png";

    private final PdfService pdfService;

    @Autowired
    public PngController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @ResponseBody
    @RequestMapping(value = "{path}", method = RequestMethod.GET, produces = IMAGE_PNG)
    public byte[] getPng(@PathVariable String path) throws IOException {
        logger.info("Receive png request path = " + path);
        Path document = pdfService.getDocument("11.png");
        return Files.readAllBytes(document);
    }

}
