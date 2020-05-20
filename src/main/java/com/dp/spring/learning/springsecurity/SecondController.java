package com.dp.spring.learning.springsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/second")
@Slf4j
public class SecondController {

    @GetMapping("/{id}")
    public String findById(@PathVariable long id) {
        log.info("get second controller called");
        return "second controller called - " + id;
    }

    @GetMapping("/")
    public String findAll() {
        log.info("findAll called in secondController");
        return "4, 5, 6";
    }
}
