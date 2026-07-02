package com.fretebot.api.Controller;

import com.fretebot.api.DTO.FreteCreateDTO;
import com.fretebot.api.DTO.FreteDTO;
import com.fretebot.api.DTO.FreteUpdateDTO;
import com.fretebot.api.Entity.Enums.StatusFreteEnum;
import com.fretebot.api.Service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController  = @Controller + @ResponseBody em todos os métodos
//                    (retorno é serializado para JSON automaticamente)
// @RequestMapping  = prefixo base de todos os endpoints deste controller
@RestController
@RequestMapping("/fretes")
public class FreteController {

    // O controller só conhece o service — nunca acessa o repositório diretamente.
    // Responsabilidade do controller: receber HTTP → chamar service → devolver resposta.
    @Autowired
    private FreteService freteService;


    // ============================================================
    // POST /fretes
    // Cadastra um novo frete
    // ============================================================
    // @RequestBody faz o Spring desserializar o JSON do body para o DTO
    // ResponseEntity<FreteDTO> permite controlar o status HTTP devolvido (201 Created)
    @PostMapping("registro")
    public ResponseEntity<FreteDTO> cadastrarFrete(@RequestBody FreteCreateDTO dto) {
        FreteDTO freteCriado = freteService.cadastrarFrete(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(freteCriado);
    }


    // ============================================================
    // GET /fretes
    // Lista todos os fretes
    // ============================================================
    @GetMapping
    public ResponseEntity<List<FreteDTO>> consultarFretes() {
        return ResponseEntity.ok(freteService.consultarFretes());
    }


    // ============================================================
    // GET /fretes/{id}
    // Busca um frete específico pelo ID
    // ============================================================
    // @PathVariable extrai o {id} da URL e injeta como parâmetro
    @GetMapping("/{id}")
    public ResponseEntity<FreteDTO> consultarFretePorId(@PathVariable Long id) {
        return ResponseEntity.ok(freteService.consultarFretePorId(id));
    }


    // ============================================================
    // GET /fretes/cliente/{idCliente}
    // Lista todos os fretes de um cliente
    // ============================================================
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<FreteDTO>> consultarFretesPorCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(freteService.consultarFretesPorCliente(idCliente));
    }


    // ============================================================
    // GET /fretes/status/{status}
    // Lista fretes filtrados por status
    // Ex: GET /fretes/status/EM_TRANSPORTE
    // ============================================================
    // O Spring converte automaticamente a String da URL para o enum,
    // desde que o nome bata com a constante (ex: "PENDENTE", "EM_TRANSPORTE")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<FreteDTO>> consultarFretesPorStatus(@PathVariable StatusFreteEnum status) {
        return ResponseEntity.ok(freteService.consultarFretesPorStatus(status));
    }


    // ============================================================
    // PUT /fretes/{id}
    // Atualiza dados de um frete (não muda status)
    // ============================================================
    @PutMapping("/{id}")
    public ResponseEntity<FreteDTO> alterarFrete(
            @PathVariable Long id,
            @RequestBody FreteUpdateDTO dto) {
        return ResponseEntity.ok(freteService.alterarFrete(id, dto));
    }


    // ============================================================
    // DELETE /fretes/{id}
    // Remove um frete
    // ============================================================
    // 204 No Content = operação bem-sucedida, sem corpo na resposta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFrete(@PathVariable Long id) {
        freteService.deletarFrete(id);
        return ResponseEntity.noContent().build();
    }


    // ============================================================
    // ── ENDPOINTS DE MUDANÇA DE STATUS ─────────────────────────
    //
    // Usamos PATCH porque é uma atualização parcial (só o status).
    // Cada transição tem sua própria rota para deixar a API
    // semântica e autodescritiva — em vez de um PATCH genérico
    // que recebe o status no body.
    // ============================================================

    // PENDENTE → AGUARDANDO_COTACAO
    // PATCH /fretes/{id}/aguardar-cotacao
    @PatchMapping("/{id}/aguardar-cotacao")
    public ResponseEntity<FreteDTO> aguardarCotacao(@PathVariable Long id) {
        return ResponseEntity.ok(freteService.aguardarCotacao(id));
    }

    // AGUARDANDO_COTACAO → COTADO
    // PATCH /fretes/{id}/cotar
    @PatchMapping("/{id}/cotar")
    public ResponseEntity<FreteDTO> cotarFrete(@PathVariable Long id) {
        return ResponseEntity.ok(freteService.cotarFrete(id));
    }

    // COTADO → APROVADO
    // PATCH /fretes/{id}/aprovar
    @PatchMapping("/{id}/aprovar")
    public ResponseEntity<FreteDTO> aprovarFrete(@PathVariable Long id) {
        return ResponseEntity.ok(freteService.aprovarFrete(id));
    }

    // APROVADO → EM_TRANSPORTE
    // PATCH /fretes/{id}/iniciar-transporte
    @PatchMapping("/{id}/transporte")
    public ResponseEntity<FreteDTO> iniciarTransporte(@PathVariable Long id) {
        return ResponseEntity.ok(freteService.iniciarTransporte(id));
    }

    // EM_TRANSPORTE → CONCLUIDO
    // PATCH /fretes/{id}/concluir
    @PatchMapping("/{id}/concluir")
    public ResponseEntity<FreteDTO> concluirFrete(@PathVariable Long id) {
        return ResponseEntity.ok(freteService.concluirFrete(id));
    }

    // QUALQUER STATUS → CANCELADO
    // PATCH /fretes/{id}/cancelar
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<FreteDTO> cancelarFrete(@PathVariable Long id) {
        return ResponseEntity.ok(freteService.cancelarFrete(id));
    }
}