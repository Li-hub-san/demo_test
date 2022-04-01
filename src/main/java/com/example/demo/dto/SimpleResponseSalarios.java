package com.example.demo.dto;

import com.example.demo.models.Salario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SimpleResponseSalarios extends SimpleResponse{
    private List<Salario> salarios;
}
