package com.fretebot.api.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_VEICULO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VEICULO")
    private Long idVeiculo;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;

    @Column(name = "PLACA")
    private String placa;

    @Column(name = "MODELO")
    private String modelo;

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "CAPACIDADE_KG")
    private Double capacidadeKg;
}