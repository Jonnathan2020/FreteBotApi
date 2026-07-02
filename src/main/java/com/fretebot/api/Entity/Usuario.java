package com.fretebot.api.Entity;

import com.fretebot.api.Entity.Enums.TipoUsuarioEnum;
import com.fretebot.api.Entity.Enums.TipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TBl_USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @Column(name = "NOME",nullable = false)
    private String nome;

    @Column(name= "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name= "SENHA",nullable = false)
    private String senha;

    @Column(name= "TELEFONE",nullable = false)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_USUARIO_ENUM")
    private TipoUsuarioEnum tipo;

    @Column(name = "ATIVO")
    private Boolean ativo;

    @Column(name = "DT_CADASTRO")
    private LocalDateTime dataCadastro;
}
