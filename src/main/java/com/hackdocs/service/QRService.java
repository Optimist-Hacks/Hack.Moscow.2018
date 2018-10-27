package com.hackdocs.service;

import com.hackdocs.model.businessModels.Document;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class QRService {

    private static Logger logger = LoggerFactory.getLogger(QRService.class);
    private static final int SIZE = 250;

    public File generateQR(Document document) {
        String id = document.getId();
        logger.info("Generate QR for id");
        return QRCode.from(id).to(ImageType.PNG).withSize(SIZE, SIZE).file();
    }

}
