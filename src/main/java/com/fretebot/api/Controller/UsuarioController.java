
package com.fretebot.api.Controller;

import com.fretebot.api.DTO.UsuarioCreateDTO;
import com.fretebot.api.DTO.UsuarioUpdateDTO;
import com.fretebot.api.Entity.Usuario;
import com.fretebot.api.Service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario() {

        return ResponseEntity.ok(
                usuarioService.consultarUsuarios()
        );
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> consultarUsuarioPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                usuarioService.consultarUsuarioPorId(id)
        );
    }

    // BUSCAR POR NOME
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Usuario>> getUsuarioByName(
            @PathVariable String nome) {

        return ResponseEntity.ok(
                usuarioService.consultarUsuarioPorNome(nome)
        );
    }

    // CADASTRAR USUÁRIO
    @PostMapping("/registro")
    public ResponseEntity<Usuario> cadastrarUsuario(
            @RequestBody UsuarioCreateDTO usuarioCreateDTO) {

        Usuario usuarioCriado =
                usuarioService.cadastrarUsuario(usuarioCreateDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioCriado);
    }

    // ALTERAR USUÁRIO
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {

        Usuario usuarioAtualizado =
                usuarioService.alterarUsuario(id, usuarioUpdateDTO);

        return ResponseEntity.ok(usuarioAtualizado);
    }

    // DELETAR USUÁRIO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        usuarioService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
