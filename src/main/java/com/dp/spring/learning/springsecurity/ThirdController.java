package com.dp.spring.learning.springsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/third")
@Slf4j
public class ThirdController {

    @GetMapping("/{id}")
    public String findById(@PathVariable long id) {
        log.info("get third controller called");
        return "third controller called - " + id;
    }

    @GetMapping("/")
    public String findAll() {
        log.info("findAll called in thirdController");
        return "11, 15, 16";
    }
}
