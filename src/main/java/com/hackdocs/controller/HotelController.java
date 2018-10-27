package com.hackdocs.controller;

import com.hackdocs.service.logic.HotelLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/v1/hotel")
public class HotelController {

    private static Logger logger = LoggerFactory.getLogger(HotelController.class);
    private static final String HOTEL = "hotel";

    @GetMapping("")
    public String hotel() {
        logger.info("Receive hotel request");
        return HOTEL;
    }

    @GetMapping("/clear")
    public ResponseEntity hotelClear() {
        logger.info("Receive clear hotel request");
        HotelLogic.COMPLETED_DOCUMENTS.clear();
        return ResponseEntity.ok("");
    }

}
