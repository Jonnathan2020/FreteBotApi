package com.fretebot.api.Service;

import com.fretebot.api.DTO.LoginDTO;
import com.fretebot.api.DTO.LoginResponseDTO;
import com.fretebot.api.Entity.Usuario;
import com.fretebot.api.Repository.UsuarioRepository;
import com.fretebot.api.Security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public LoginResponseDTO login(LoginDTO dto) {

        Usuario usuario = usuarioRepository
                .findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuário não encontrado"));

        boolean senhaCorreta = passwordEncoder.matches(
                dto.getSenha(),
                usuario.getSenha()
        );

        if (!senhaCorreta) {
            throw new IllegalArgumentException("Senha inválida");
        }

        String token = jwtService.gerarToken(usuario.getEmail());

        return new LoginResponseDTO(
                token,
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}

