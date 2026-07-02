package com.fretebot.api.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_CLIENTE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "TELEFONE")
    private Long telefone;

    @Column(name = "DOCUMENTO", nullable = true)
    private String documento;


}