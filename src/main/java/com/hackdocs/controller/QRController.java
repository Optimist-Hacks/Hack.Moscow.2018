package com.hackdocs.controller;

import com.hackdocs.model.businessModels.Document;
import com.hackdocs.service.QRService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping(value = "/api/v1/qr")
public class QRController {

    private static Logger logger = LoggerFactory.getLogger(QRController.class);
    private final QRService qrService;

    @Autowired
    public QRController(QRService qrService) {
        this.qrService = qrService;
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    byte[] qr() throws IOException {
        logger.info("Receive qr request");
        File file = qrService.generateQR(new Document());
        return Files.readAllBytes(file.toPath());
    }

}
