package com.example.demo.controllers;

import com.example.demo.dto.*;
import com.example.demo.models.Empresa;
import com.example.demo.models.Pessoa;
import com.example.demo.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
@CrossOrigin(origins = "http://localhost:3000")
public class PessoaController {
    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/add")
    public ResponseEntity<SimpleResponse> add(@RequestBody Pessoa pessoa) {
        SimpleResponsePessoa sr = new SimpleResponsePessoa();

        Optional<Pessoa> pessoaOptional = pessoaService.addPessoa(pessoa);
        if (pessoaOptional.isPresent()) {
            sr.setStatus(true);
            sr.setMessage("Pessoa adicionada");
            sr.setPessoa(pessoaOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SimpleResponse> deletePessoa(@PathVariable Long id){
        SimpleResponse sr = new SimpleResponse();

        boolean empresaApagada = pessoaService.deletePessoa(id);
        if (empresaApagada){
            sr.setStatus(true);
            sr.setMessage("Apagado com sucesso");

            return ResponseEntity.status(HttpStatus.OK).body(sr);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sr);
    }

    @GetMapping("/get/{empresaId}")
    public ResponseEntity<SimpleResponse> getByEmpresa(@PathVariable Long empresaId) {
        SimpleResponsePessoas sr = new SimpleResponsePessoas();

        List<Pessoa> pessoas = pessoaService.getByEmpresa(empresaId);
        if (pessoas != null) {
            sr.setStatus(true);
            sr.setMessage("Pessoas encontradas");
            sr.setPessoas(pessoas);
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sr);
    }

}
