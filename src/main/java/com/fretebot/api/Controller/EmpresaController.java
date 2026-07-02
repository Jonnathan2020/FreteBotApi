package com.fretebot.api.Controller;

import com.fretebot.api.DTO.EmpresaCreateDTO;
import com.fretebot.api.DTO.EmpresaDTO;
import com.fretebot.api.DTO.EmpresaUpdateDTO;
import com.fretebot.api.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;


    // POST /empresas  —  Cadastra uma nova empresa
    @PostMapping
    public ResponseEntity<EmpresaDTO> cadastrarEmpresa(@RequestBody EmpresaCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.cadastrarEmpresa(dto));
    }


    // GET /empresas  —  Lista todas as empresas
    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> consultarEmpresas() {
        return ResponseEntity.ok(empresaService.consultarEmpresas());
    }


    // GET /empresas/{id}  —  Busca empresa por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> consultarEmpresaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.consultarEmpresaPorId(id));
    }


    // GET /empresas/cnpj/{cnpj}  —  Busca empresa por CNPJ
    // Útil para login/onboarding onde o usuário informa o CNPJ
    @GetMapping("/cnpj/{documento}")
    public ResponseEntity<EmpresaDTO> consultarEmpresaPorDocumento(@PathVariable String documento) {
        return ResponseEntity.ok(empresaService.consultarEmpresaPorDocumento(documento));
    }


    // PUT /empresas/{id}  —  Atualiza dados da empresa
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> alterarEmpresa(
            @PathVariable Long id,
            @RequestBody EmpresaUpdateDTO dto) {
        return ResponseEntity.ok(empresaService.alterarEmpresa(id, dto));
    }


    // DELETE /empresas/{id}  —  Remove a empresa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Long id) {
        empresaService.deletarEmpresa(id);
        return ResponseEntity.noContent().build();
    }
}