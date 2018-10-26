package com.hackdocs.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/hello")
public class HelloController {

    @GetMapping("")
    @ApiOperation(value = "Hello World", response = String.class)
    public String helloWorld() {
        return "Hello world";
    }

}
