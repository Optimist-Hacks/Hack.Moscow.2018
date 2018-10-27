package com.hackdocs.controller;

import com.hackdocs.ResponseHelper;
import com.hackdocs.model.Request;
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

    @Autowired
    public GoogleController(ResponseHelper responseHelper) {
        this.responseHelper = responseHelper;
    }


    @PostMapping("")
    public ResponseEntity<String> process(@RequestBody Request request) {
        logger.info("Receive google request payload = " + request);


        String s = "{\n" +
                "  \"payload\": {\n" +
                "    \"google\": {\n" +
                "      \"expectUserResponse\": true,\n" +
                "      \"richResponse\": {\n" +
                "        \"items\": [\n" +
                "          {\n" +
                "            \"simpleResponse\": {\n" +
                "              \"textToSpeech\": \"this is a simple response\"\n" +
                "            }\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        return responseHelper.ok(s);
    }

}
