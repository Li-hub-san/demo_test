package com.example.demo.repository;

import com.example.demo.models.Empresa;
import com.example.demo.models.Pessoa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
    List<Pessoa> findAllByEmpresa_Id(Long id);
}
