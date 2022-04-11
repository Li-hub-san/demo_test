package com.example.demo.services;

import com.example.demo.models.Pessoa;
import com.example.demo.models.Salario;
import com.example.demo.repository.PessoaRepository;
import com.example.demo.repository.SalarioRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
                salario.getData() == null ||
                salario.getPessoa() == null ||
                salario.getPessoa().getId() == null) {
            return Optional.empty();
        }

        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(salario.getPessoa().getId());
        if (pessoaOptional.isEmpty()) {
            return Optional.empty();
        }

        Pessoa pessoaDb = pessoaOptional.get();

        Optional<Salario> salarioOptional = salarioRepository.findFirstByPessoaOrderByDataDesc(pessoaDb);
        if (salarioOptional.isPresent()) {
            Salario ultimoSalarioDb = salarioOptional.get();

            Date dataUltimoSalario = ultimoSalarioDb.getData();
            Date dataSalarioToAdd = salario.getData();

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(dataUltimoSalario);
            int ultimoSalarioMes = calendar.get(Calendar.MONTH);
            int ultimoSalarioAno = calendar.get(Calendar.YEAR);

            calendar.setTime(dataSalarioToAdd);
            int salarioToAddMes = calendar.get(Calendar.MONTH);
            int salarioToAddAno = calendar.get(Calendar.YEAR);

            if (ultimoSalarioMes == salarioToAddMes && ultimoSalarioAno == salarioToAddAno) {
                return Optional.empty();
            }
        }

        salario.setPessoa(pessoaDb);
        salario.setData(new Date());

        return Optional.of(salarioRepository.save(salario));
    }

    public boolean deleteSalario(Long salarioId) {
        if (salarioId == null) {
            return false;
        }

        Optional<Salario> salarioOptional = salarioRepository.findById(salarioId);
        if (salarioOptional.isEmpty()) {
            return false;
        }

        salarioRepository.deleteById(salarioId);
        return true;
    }

    public List<Salario> getByPessoa(Long pessoaId) {
        if (pessoaId == null) {
            return null;
        }

        return salarioRepository.findAllByPessoa_Id(pessoaId);
    }
}
