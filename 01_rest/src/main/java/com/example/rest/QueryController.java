package com.example.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController {
    @GetMapping("query-test")
    // /query-test?q=asdf&type=pop&page=10
    public String queryParams(
            // Query의 내용은
            // @RequestParam으로 가져올 수 있다.
            @RequestParam("q")
            String q,  // "asdf"
            @RequestParam(value = "type", required = false)
            String type,
            @RequestParam(value = "page", defaultValue = "0")
            Integer page
    ) {
        System.out.println(q);
        System.out.println(type);
        System.out.println(page);
        return q + " " + type + " " + page;
    }
}
