package com.hackdocs.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/google")
public class GoogleController {

    @PostMapping("")
    public void process() {
    }

}
