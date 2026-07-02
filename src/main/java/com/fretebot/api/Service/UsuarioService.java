
package com.fretebot.api.Service;

import com.fretebot.api.DTO.UsuarioCreateDTO;
import com.fretebot.api.DTO.UsuarioUpdateDTO;
import com.fretebot.api.Entity.Usuario;
import com.fretebot.api.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // CONSULTAR TODOS
    public List<Usuario> consultarUsuarios() {
        return usuarioRepository.findAll();
    }

    // EXPRESSÃO REGULAR PARA VALIDAR EMAIL
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    // VALIDAR EMAIL
    public boolean isEmailValido(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // CADASTRAR USUARIO
    public Usuario cadastrarUsuario(UsuarioCreateDTO dto) {

        if (!isEmailValido(dto.getEmail())) {
            throw new IllegalArgumentException("E-mail inválido!");
        }

        boolean emailExiste = usuarioRepository.existsByEmail(dto.getEmail());

        if (emailExiste) {
            throw new IllegalArgumentException("E-mail já cadastrado!");
        }

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(new BCryptPasswordEncoder().encode(dto.getSenha()))
                .telefone(dto.getTelefone())
                .tipo(dto.getTipo())
                .ativo(true)
                .dataCadastro(LocalDateTime.now())
                .build();

        return usuarioRepository.save(usuario);
    }

    // ALTERAR USUARIO
    public Usuario alterarUsuario(Long idUsuario, UsuarioUpdateDTO dto) {

        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuário não encontrado!"));

        if (dto.getNome() != null) {
            usuarioExistente.setNome(dto.getNome());
        }

        if (dto.getSenha() != null) {
            usuarioExistente.setSenha(dto.getSenha());
        }

        if (dto.getTelefone() != null) {
            usuarioExistente.setTelefone(dto.getTelefone());
        }

        if (dto.getTipo() != null) {
            usuarioExistente.setTipo(dto.getTipo());
        }

        if (dto.getAtivo() != null) {
            usuarioExistente.setAtivo(dto.getAtivo());
        }

        return usuarioRepository.save(usuarioExistente);
    }

    // DELETAR USUARIO
    public void delete(Long idUsuario) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuário não encontrado!"));

        usuarioRepository.delete(usuario);
    }

    // CONSULTAR POR NOME
    public List<Usuario> consultarUsuarioPorNome(String nome) {
        return usuarioRepository.findByNomeIgnoreCaseContaining(nome);
    }

    // CONSULTAR POR ID
    public Usuario consultarUsuarioPorId(Long idUsuario) {

        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuário não encontrado!"));
    }
}

