package com.example.demo.repository;

import com.example.demo.models.Empresa;
import com.example.demo.models.Salario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SalarioRepository extends CrudRepository<Salario, Long> {
    List<Salario> findAllByPessoa_Id(Long pessoaId);
}
