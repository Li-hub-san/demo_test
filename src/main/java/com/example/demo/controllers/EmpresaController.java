package com.example.demo.controllers;

import com.example.demo.dto.SimpleResponse;
import com.example.demo.dto.SimpleResponseEmpresa;
import com.example.demo.dto.SimpleResponseEmpresas;
import com.example.demo.models.Empresa;
import com.example.demo.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresa")
@CrossOrigin(origins = "http://localhost:3000")
public class EmpresaController {
    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping("/add")
    public ResponseEntity<SimpleResponse> addEmpresa(@RequestBody Empresa empresa) {
        SimpleResponseEmpresa sr = new SimpleResponseEmpresa();

        Optional<Empresa> empresaOptional = empresaService.addEmpresa(empresa);
        if (empresaOptional.isPresent()) {
            sr.setStatus(true);
            sr.setMessage("Centro comercial adicionado");
            sr.setEmpresa(empresaOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SimpleResponse> deleteEmpresa(@PathVariable Long id){
        SimpleResponse sr = new SimpleResponse();

        boolean empresaApagada = empresaService.deleteEmpresa(id);
        if (empresaApagada){
            sr.setStatus(true);
            sr.setMessage("Apagado com sucesso");

            return ResponseEntity.status(HttpStatus.OK).body(sr);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sr);
    }

    @GetMapping("/getAll")
    public ResponseEntity<SimpleResponse> getAll() {
        SimpleResponseEmpresas sr = new SimpleResponseEmpresas();

        List<Empresa> empresas = empresaService.getAll();
        if (empresas != null) {
            sr.setStatus(true);
            sr.setMessage("Centros encontrados");
            sr.setEmpresas(empresas);
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sr);
    }

}
