package br.com.fiap.locatech.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequestDTO(
        @NotNull(message = "PessoaId é obrigatório")
        Long pessoaId,
        @NotNull(message = "VeiculoId é obrigatório")
        Long veiculoId,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}
