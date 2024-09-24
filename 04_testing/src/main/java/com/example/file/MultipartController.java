package com.example.file;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

@RestController
public class MultipartController {
    @PostMapping(
            value = "/multipart",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String multipart(
            @RequestParam("file")
            MultipartFile multipartFile
    ) throws IOException {
        System.out.println(multipartFile.getOriginalFilename());
        Path downloadPath =
                Path.of("media/" + multipartFile.getOriginalFilename());
        multipartFile.transferTo(downloadPath);

        return "http://localhost:8080/static/" + multipartFile.getOriginalFilename();
    }
}
