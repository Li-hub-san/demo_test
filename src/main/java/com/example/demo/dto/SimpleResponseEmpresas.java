package com.example.demo.dto;

import com.example.demo.models.Empresa;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SimpleResponseEmpresas extends SimpleResponse {
    private List<Empresa> empresas;
}

