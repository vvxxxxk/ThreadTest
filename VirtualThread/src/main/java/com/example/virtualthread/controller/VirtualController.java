package com.example.virtualthread.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class VirtualController {

    @GetMapping("/virtual/{id}")
    public String virtual(@PathVariable Long id) throws InterruptedException {
        log.info("[virtual] request {} start", id);
        Thread thread = Thread.ofVirtual().start(() -> System.out.println("Hello World"));
        Thread.sleep(5000);
        log.info("[virtual] request {} end", id);
        return "OK";
    }

    @GetMapping("/none/{id}")
    public String none(@PathVariable Long id) throws InterruptedException {
        log.info("[none] request {} start", id);
        System.out.println("Hello World");
        log.info("[none] request {} end", id);
        return "OK";
    }
}
