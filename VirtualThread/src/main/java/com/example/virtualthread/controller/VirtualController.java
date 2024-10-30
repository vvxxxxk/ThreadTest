package com.example.virtualthread.controller;

import com.example.virtualthread.controller.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/virtual")
public class VirtualController {

    @PostMapping(value = "/basic/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> basic(@PathVariable Long id) throws InterruptedException {
        log.info("[virtual] basic request {} start", id);

//        if (Thread.currentThread().isVirtual()) {
//            System.out.println("This is a virtual thread.");
//            // 가상 스레드에 최적화된 처리 로직
//        } else {
//            System.out.println("This is a platform thread.");
//            // 플랫폼 스레드에 맞는 처리 로직
//        }
        Thread.sleep(200);

//        Thread.ofVirtual().start(() -> {
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                throw new RuntimeException(e);
//            }
//            log.info("[virtual] basic request {} end", id);
//        }).join();

        ResponseDto response = ResponseDto.success();
        return ResponseEntity.ok(response);
    }
}
