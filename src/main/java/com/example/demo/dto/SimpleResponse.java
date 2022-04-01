package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResponse {
    private boolean status;
    private String message;

    public SimpleResponse() {
        this.status = false;
        this.message = "Ocorreu um erro";
    }
}
