package br.com.fiap.locatech.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequestDTO(
        @Schema(description = "ID da pessoa que está alugando o veículo", example = "1")
        @NotNull(message = "PessoaId é obrigatório")
        Long pessoaId,
        @Schema(description = "ID do veículo que está sendo alugado", example = "1")
        @NotNull(message = "VeiculoId é obrigatório")
        Long veiculoId,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}
