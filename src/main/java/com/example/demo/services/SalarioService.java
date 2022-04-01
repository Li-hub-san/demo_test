package com.example.demo.services;

import com.example.demo.models.Empresa;
import com.example.demo.models.Pessoa;
import com.example.demo.models.Salario;
import com.example.demo.repository.PessoaRepository;
import com.example.demo.repository.SalarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalarioService {
    private final SalarioRepository salarioRepository;
    private final PessoaRepository pessoaRepository;

    public SalarioService(SalarioRepository salarioRepository,
                          PessoaRepository pessoaRepository) {
        this.salarioRepository = salarioRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Optional<Salario> addSalario(Salario salario) {
        if (salario == null ||
                salario.getId() != null ||
                salario.getQuantidade() < 700 ||
                salario.getData() == null) {
            return Optional.empty();
        }

        //faltou acabar verificações mas fiquei baralhada...
//        Optional<Pessoa> pessoOptional = pessoaRepository.findById();
//        if (pessoOptional.isEmpty()) {
//            return Optional.empty();
//        }

        return Optional.of(salarioRepository.save(salario));
    }

    public boolean deleteSalario(Long id) {
        // TODO: no time
        return false;
    }

    public Salario getByPessoa(Long pessoaId) {
        // TODO: no time, query missing :(
        return null;
    }
}
