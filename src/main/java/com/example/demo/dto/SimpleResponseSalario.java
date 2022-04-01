package com.example.demo.dto;

import com.example.demo.models.Salario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResponseSalario extends SimpleResponse{
    private Salario salario;
}
