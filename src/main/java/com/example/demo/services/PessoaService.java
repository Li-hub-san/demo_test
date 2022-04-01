package com.example.demo.services;

import com.example.demo.models.Empresa;
import com.example.demo.models.Pessoa;
import com.example.demo.repository.EmpresaRepository;
import com.example.demo.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;
    private final EmpresaRepository empresaRepository;

    public PessoaService(PessoaRepository pessoaRepository, EmpresaRepository empresaRepository) {
        this.pessoaRepository = pessoaRepository;
        this.empresaRepository = empresaRepository;
    }

    public Optional<Pessoa> addPessoa(Pessoa pessoa) {
        if (pessoa == null ||
                pessoa.getId() != null ||
                pessoa.getNome() == null ||
                pessoa.getNome().isBlank() ||
                pessoa.getImagem() == null ||
                pessoa.getImagem().isBlank() ||
                pessoa.getIdade() < 16 ||
                pessoa.getEmail() == null ||
                pessoa.getEmail().isBlank() ||
                pessoa.getImagem().isBlank()) {
            return Optional.empty();
        }

        Optional<Empresa> empresaOptional = empresaRepository.findById(pessoa.getEmpresa().getId());
        if (empresaOptional.isEmpty()){
            return Optional.empty();
        }

        Empresa empresaDb = empresaOptional.get();
        pessoa.setEmpresa(empresaDb);

        return Optional.of(pessoaRepository.save(pessoa));
    }

    public boolean deletePessoa(Long id) {
        if (id == null) {
            return false;
        }

        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isEmpty()) {
            return false;
        }

        pessoaRepository.deleteById(id);
        return true;
    }

    public List<Pessoa> getByEmpresa(Long empresaId) {
        if (empresaId == null) {
            return null;
        }

        return pessoaRepository.findAllByEmpresa_Id(empresaId);
    }
}
