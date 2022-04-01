package com.example.demo.controllers;


import com.example.demo.dto.SimpleResponse;
import com.example.demo.dto.SimpleResponseEmpresa;
import com.example.demo.dto.SimpleResponsePessoas;
import com.example.demo.dto.SimpleResponseSalario;
import com.example.demo.models.Empresa;
import com.example.demo.models.Pessoa;
import com.example.demo.models.Salario;
import com.example.demo.services.SalarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salario")
@CrossOrigin(origins = "http://localhost:3000")
public class SalarioController {
    private final SalarioService salarioService;

    public SalarioController(SalarioService salarioService) {
        this.salarioService = salarioService;
    }

    @PostMapping("/add")
    public ResponseEntity<SimpleResponse> add(@RequestBody Salario salario) {
        SimpleResponseSalario sr = new SimpleResponseSalario();

        Optional<Salario> salarioOptional = salarioService.addSalario(salario);
        if (salarioOptional.isPresent()) {
            sr.setStatus(true);
            sr.setMessage("Salario adicionado");
            sr.setSalario(salarioOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SimpleResponse> delete(@PathVariable Long id){
        SimpleResponse sr = new SimpleResponse();

        boolean salarioApagado = salarioService.deleteSalario(id);
        if (salarioApagado){
            sr.setStatus(true);
            sr.setMessage("Salario eliminado com sucesso");

            return ResponseEntity.status(HttpStatus.OK).body(sr);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sr);
    }

    @GetMapping("/get/{pessoaId}")
    public ResponseEntity<SimpleResponse> getByPessoa(@PathVariable Long pessoaId) {
        SimpleResponseSalario sr = new SimpleResponseSalario();

       Salario salario = salarioService.getByPessoa(pessoaId);
        if (salario != null) {
            sr.setStatus(true);
            sr.setMessage("Centros encontrados");
            sr.setSalario(salario);
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sr);
    }
}
