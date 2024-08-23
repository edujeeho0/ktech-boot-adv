package com.example.file;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
//    private static final Logger log =
//            // 이 클래스(LogTestController)에서 발생한 메시지를
//            // 기록하는 로거 생성
//            LoggerFactory.getLogger(LogTestController.class);

    @GetMapping("/log")
    public void logTest() {
        // 5단계의 level이 있고,
        // 아래로 갈수록 심각한 상황을 나타낸다.
        log.trace("A TRACE message");
        log.debug("A DEBUG message");
        log.info("A INFO message");
        log.warn("A WARN message");
        log.error("A ERROR message");
    }
}
