package com.example.demo.repository;

import com.example.demo.models.Empresa;
import com.example.demo.models.Pessoa;
import com.example.demo.models.Salario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SalarioRepository extends CrudRepository<Salario, Long> {

    Optional<Salario> findFirstByPessoaOrderByDataDesc(Pessoa pessoa);
    List<Salario> findAllByPessoa_Id(Long id);

}
