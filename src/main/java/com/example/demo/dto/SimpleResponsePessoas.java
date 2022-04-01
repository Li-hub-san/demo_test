package com.example.demo.dto;

import com.example.demo.models.Pessoa;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SimpleResponsePessoas extends SimpleResponse {
    private List<Pessoa> pessoas;
}
