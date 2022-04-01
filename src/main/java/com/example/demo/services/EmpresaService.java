package com.example.demo.services;

import com.example.demo.models.Empresa;
import com.example.demo.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Optional<Empresa> addEmpresa(Empresa empresa) {
        if (empresa == null ||
                empresa.getId() != null ||
                empresa.getNome() == null ||
                empresa.getNome().isBlank() ||
                empresa.getMorada() == null ||
                empresa.getMorada().isBlank() ||
                empresa.getImagem() == null ||
                empresa.getImagem().isBlank()) {
            return Optional.empty();
        }

        return Optional.of(empresaRepository.save(empresa));
    }

    public List<Empresa> getAll() {
        return (List<Empresa>) empresaRepository.findAll();
    }

    public boolean deleteEmpresa(Long id) {
        if (id == null) {
            return false;
        }

        Optional<Empresa> centroDb = empresaRepository.findById(id);
        if (centroDb.isEmpty()) {
            return false;
        }

        empresaRepository.deleteById(id);
        return true;
    }
}


