package br.com.fiap.locatech.controllers;

import br.com.fiap.locatech.entities.Veiculo;
import br.com.fiap.locatech.services.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/veiculos")
@Tag(name = "Veiculo", description = "Endpoints para gerenciamento de veiculos")
public class VeiculoController {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @Operation(
            summary = "Listar veiculos",
            description = "Endpoint para listar os veiculos disponiveis para aluguel. " +
                    "É possível paginar os resultados utilizando os parâmetros 'page' e 'size'. " +
                    "Exemplo: /veiculos?page=1&size=10"
    )
    @GetMapping
    public ResponseEntity<List<Veiculo>> findAllVeiculos(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("Foi acessado o endpoint de veiculos: /veiculos");
        var veiculos = this.veiculoService.findAllVeiculos(page, size);
        return ResponseEntity.ok(veiculos);
    }

    @Operation(
            summary = "Buscar veiculo por id",
            description = "Endpoint para buscar um veiculo por id. " + "Exemplo: /veiculos/1"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veiculo>> findVeiculo(@PathVariable("id") Long id) {
        logger.info("/veiculos/" + id);
        var veiculo = this.veiculoService.findVeiculoById(id);
        return ResponseEntity.ok(veiculo);
    }

    @PostMapping
    public ResponseEntity<Void> saveVeiculo(@RequestBody Veiculo veiculo) {
        logger.info("POST --> /veiculos");
        this.veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(
            @PathVariable("id") Long id,
            @RequestBody Veiculo veiculo
    ) {
        logger.info("PUT --> /veiculos/" + id);
        this.veiculoService.updateVeiculo(veiculo, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable("id") Long id) {
        logger.info("DELETE --> /veiculos/" + id);
        this.veiculoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

 }
