package com.hackdocs.controller;

import com.hackdocs.ResponseHelper;
import com.hackdocs.model.Request;
import com.hackdocs.model.Response;
import com.hackdocs.service.logic.Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/google")
public class GoogleController {

    private static Logger logger = LoggerFactory.getLogger(GoogleController.class);
    private final ResponseHelper responseHelper;
    private final Logic logic;

    @Autowired
    public GoogleController(ResponseHelper responseHelper, Logic logic) {
        this.responseHelper = responseHelper;
        this.logic = logic;
    }

    @PostMapping("")
    public ResponseEntity<Response> process(@RequestBody Request request) {
        logger.info("Receive google request payload = " + request);

        Response response = logic.processRequest(request);
        logger.info("Send response = " + response);

        return responseHelper.ok(response);
    }

}
