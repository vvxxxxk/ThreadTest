package com.example.webflux.controller;

import com.example.webflux.controller.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@RestController
@RequestMapping("/api/v1/webflux")
public class WebFluxController {

    @PostMapping(value = "/basic/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ResponseDto>> basic(@PathVariable("id") Long id) {
        log.info("[webflux] basic request {} start", id);
        return Mono.delay(Duration.ofMillis(200))
                .map(ignore -> {
                    log.info("[Spring WebFlux] basic request {} end", id);
                    ResponseDto response = ResponseDto.success();
                    return ResponseEntity.ok(response);
                });
    }
}
