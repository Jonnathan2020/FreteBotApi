package com.fretebot.api.Controller;

import com.fretebot.api.DTO.VeiculoCreateDTO;
import com.fretebot.api.DTO.VeiculoDTO;
import com.fretebot.api.DTO.VeiculoUpdateDTO;
import com.fretebot.api.Service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;


    // POST /veiculos  —  Cadastra um novo veículo
    @PostMapping
    public ResponseEntity<VeiculoDTO> cadastrarVeiculo(@RequestBody VeiculoCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoService.cadastrarVeiculo(dto));
    }


    // GET /veiculos  —  Lista todos os veículos
    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> consultarVeiculos() {
        return ResponseEntity.ok(veiculoService.consultarVeiculos());
    }


    // GET /veiculos/{id}  —  Busca veículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> consultarVeiculoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.consultarVeiculoPorId(id));
    }


    // GET /veiculos/empresa/{idEmpresa}  —  Lista veículos de uma empresa
    // Permite que a empresa veja toda a sua frota
    @GetMapping("/empresa/{idEmpresa}")
    public ResponseEntity<List<VeiculoDTO>> consultarVeiculosPorEmpresa(@PathVariable Long idEmpresa) {
        return ResponseEntity.ok(veiculoService.consultarVeiculosPorEmpresa(idEmpresa));
    }


    // GET /veiculos/tipo/{tipo}  —  Filtra veículos por tipo
    // Ex: GET /veiculos/tipo/Caminhão  ou  GET /veiculos/tipo/Van
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<VeiculoDTO>> consultarVeiculosPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(veiculoService.consultarVeiculosPorTipo(tipo));
    }


    // PUT /veiculos/{id}  —  Atualiza dados do veículo
    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> alterarVeiculo(
            @PathVariable Long id,
            @RequestBody VeiculoUpdateDTO dto) {
        return ResponseEntity.ok(veiculoService.alterarVeiculo(id, dto));
    }


    // DELETE /veiculos/{id}  —  Remove o veículo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long id) {
        veiculoService.deletarVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}