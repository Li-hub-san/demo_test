package com.example.demo.dto;

import com.example.demo.models.Pessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResponsePessoa extends SimpleResponse{
    private Pessoa pessoa;
}
