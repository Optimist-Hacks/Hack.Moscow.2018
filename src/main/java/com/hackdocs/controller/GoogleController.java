package com.hackdocs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackdocs.ResponseHelper;
import com.hackdocs.model.Request;
import com.hackdocs.model.Response;
import com.hackdocs.service.Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/google")
public class GoogleController {

    private static Logger logger = LoggerFactory.getLogger(GoogleController.class);
    private final ResponseHelper responseHelper;
    private final Logic logic;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public GoogleController(ResponseHelper responseHelper, Logic logic) {
        this.responseHelper = responseHelper;
        this.logic = logic;
    }

    @PostMapping("")
    public ResponseEntity<Response> process(@RequestBody String payload) throws IOException {
        logger.info("Receive google request payload = " + payload);

        Request request = objectMapper.readValue(payload, Request.class);

        Response response = logic.processRequest(request);
        logger.info("Send response = " + objectMapper.writeValueAsString(response));

        return responseHelper.ok(response);
    }

}
