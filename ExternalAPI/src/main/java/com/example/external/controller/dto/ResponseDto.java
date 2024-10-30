package com.example.external.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private int status;
    private String message;

    public static ResponseDto success() {
        return new ResponseDto(200, "success");
    }

    public static ResponseDto error() {
        return new ResponseDto(400, "error");
    }
}
