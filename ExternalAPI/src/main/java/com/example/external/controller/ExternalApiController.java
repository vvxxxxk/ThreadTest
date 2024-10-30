package com.example.external.controller;

import com.example.external.controller.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/external")
public class ExternalApiController {

    @PostMapping(value = "/basic/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> basic(@PathVariable Long id) throws InterruptedException {
        log.info("[external] basic request {} start", id);
        Thread.sleep(200);
        log.info("[external] basic request {} end", id);
        ResponseDto response = ResponseDto.success();
        return ResponseEntity.ok(response);
    }
}