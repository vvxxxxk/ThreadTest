package com.example.external.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/external")
public class ExternalApiController {

    @GetMapping
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("Health Check OK", HttpStatus.OK);
    }

    @GetMapping("/sleep/{id}")
    public ResponseEntity<String> sleep(@PathVariable Long id) throws InterruptedException {
        log.info("[sleep] request {} start", id);
        Thread.sleep(5000);
        log.info("[sleep] request {} end", id);

        return new ResponseEntity<>("response" + id, HttpStatus.OK);
    }

    @GetMapping("/loop/{id}")
    public ResponseEntity<String> loop(@PathVariable Long id) throws InterruptedException {
        log.info("[loop] request {} start", id);
        for (int i = 0; i < 100000000; i++)
            System.out.print("");
        log.info("[loop] request {} end", id);

        return new ResponseEntity<>("response" + id, HttpStatus.OK);
    }
}
