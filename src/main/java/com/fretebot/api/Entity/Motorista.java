package com.fretebot.api.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cache.config.CacheNamespaceHandler;

@Entity
@Table(name = "TBL_MOTORISTA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MOTORISTA")
    private Long idMotorista;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "CNH")
    private String cnh;
}