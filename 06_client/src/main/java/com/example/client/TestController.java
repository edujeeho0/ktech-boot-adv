package com.example.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final TemplateClient templateClient;
    public TestController(TemplateClient templateClient) {
        this.templateClient = templateClient;
    }

    @GetMapping("test")
    public String test() {
        templateClient.testGet();
        return "success";
    }
}
