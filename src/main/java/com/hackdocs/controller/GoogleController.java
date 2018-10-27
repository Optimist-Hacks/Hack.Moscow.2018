package com.hackdocs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/google")
public class GoogleController {

    private static Logger logger = LoggerFactory.getLogger(GoogleController.class);

    @PostMapping("")
    public void process(@RequestBody String payload) {
        logger.info("Receive google request payload = " + payload);
    }

}
