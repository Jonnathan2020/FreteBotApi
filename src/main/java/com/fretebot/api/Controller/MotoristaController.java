package com.fretebot.api.Controller;

import com.fretebot.api.DTO.MotoristaCreateDTO;
import com.fretebot.api.DTO.MotoristaDTO;
import com.fretebot.api.DTO.MotoristaUpdateDTO;
import com.fretebot.api.Service.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;


    // POST /motoristas  —  Cadastra um novo motorista
    @PostMapping
    public ResponseEntity<MotoristaDTO> cadastrarMotorista(@RequestBody MotoristaCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(motoristaService.cadastrarMotorista(dto));
    }


    // GET /motoristas  —  Lista todos os motoristas
    @GetMapping
    public ResponseEntity<List<MotoristaDTO>> consultarMotoristas() {
        return ResponseEntity.ok(motoristaService.consultarMotoristas());
    }


    // GET /motoristas/{id}  —  Busca motorista por ID
    @GetMapping("/{id}")
    public ResponseEntity<MotoristaDTO> consultarMotoristaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(motoristaService.consultarMotoristaPorId(id));
    }


    // GET /motoristas/empresa/{idEmpresa}  —  Lista motoristas de uma empresa
    // Permite que a empresa veja todos os motoristas vinculados a ela
    @GetMapping("/empresa/{idEmpresa}")
    public ResponseEntity<List<MotoristaDTO>> consultarMotoristasPorEmpresa(@PathVariable Long idEmpresa) {
        return ResponseEntity.ok(motoristaService.consultarMotoristasPorEmpresa(idEmpresa));
    }


    // PUT /motoristas/{id}  —  Atualiza dados do motorista
    @PutMapping("/{id}")
    public ResponseEntity<MotoristaDTO> alterarMotorista(
            @PathVariable Long id,
            @RequestBody MotoristaUpdateDTO dto) {
        return ResponseEntity.ok(motoristaService.alterarMotorista(id, dto));
    }


    // DELETE /motoristas/{id}  —  Remove o motorista
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMotorista(@PathVariable Long id) {
        motoristaService.deletarMotorista(id);
        return ResponseEntity.noContent().build();
    }
}