package com.dp.spring.learning.springsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/first")
@Slf4j
public class FirstController {

    @GetMapping("/{id}")
    public String findById(@PathVariable long id) {
        log.info("get first controller called");
        return "first controller called - " + id;
    }

    @GetMapping("/")
    public String findAll() {
        log.info("findAll called in firstController");
        return "1, 2, 3";
    }
}
