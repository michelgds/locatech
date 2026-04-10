package br.com.fiap.locatech.services;

import br.com.fiap.locatech.dtos.AluguelRequestDTO;
import br.com.fiap.locatech.entities.Aluguel;
import br.com.fiap.locatech.repositories.AluguelRepository;
import br.com.fiap.locatech.repositories.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {
    private final AluguelRepository aluguelRepository;
    private final VeiculoRepository veiculoRepository;

    public AluguelService(AluguelRepository aluguelRepository, VeiculoRepository veiculoRepository) {
        this.aluguelRepository = aluguelRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public List<Aluguel> findAllAlugueis(int page, int size) {
        int offset = (page -1) * size;
        return this.aluguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findAluguelById(Long id) {
        return this.aluguelRepository.findById(id);
    }

    public void saveAluguel(AluguelRequestDTO aluguel) {
        var entity = this.calculaAluguel(aluguel);
        var save = this.aluguelRepository.save(entity);
        Assert.state(save == 1, "Erro ao salvar aluguel " + aluguel.pessoaId());
    }

    public void updateAluguel(Aluguel aluguel, Long id) {
        var update = this.aluguelRepository.update(aluguel, id);
        if (update == 0 ) {
            throw new RuntimeException("Aluguel não encontrado");
        }
    }

    public void delete(Long id) {
        var delete = this.aluguelRepository.delete(id);
        if (delete == 0 ) {
            throw new RuntimeException("Aluguel não encontrado");
        }
    }

    private Aluguel calculaAluguel(AluguelRequestDTO aluguelRequestDTO) {
        var veiculo = this.veiculoRepository.findById(aluguelRequestDTO.veiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        var quntidadeDias = BigDecimal.valueOf(
                aluguelRequestDTO.dataFim().getDayOfYear() - aluguelRequestDTO.dataInicio().getDayOfYear()
        );

        var valor = veiculo.getValorDiaria().multiply(quntidadeDias);
        return new Aluguel(aluguelRequestDTO, valor);
    }
}
