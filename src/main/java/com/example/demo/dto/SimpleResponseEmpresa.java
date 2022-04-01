package com.example.demo.dto;

import com.example.demo.models.Empresa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResponseEmpresa extends SimpleResponse{
    private Empresa empresa;
}
