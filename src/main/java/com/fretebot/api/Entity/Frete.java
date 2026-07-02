package com.fretebot.api.Entity;

import com.fretebot.api.Entity.Enums.StatusFreteEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TBL_FRETE")
public class Frete {

    /*
    { id: 2044, status: "transito", origem: "Sao Paulo", destino: "Campinas", peso: 500, tipo: "Eletronicos", valor: 380, phone: null, criadoEm: new Date() },
    { id: 2045, status: "confirmado", origem: "Santos", destino: "Sao Paulo", peso: 1200, tipo: "Alimentos", valor: 520, phone: null, criadoEm: new Date() },
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FRETE")
    private Long idFrete;

    @Column(name = "CEP_ORIGEM")
    private String cepOrigem;

    @Column(name = "ENDERECO_ORIGEM")
    private String cidadeOrigem;

    @Column(name = "CEP_DESTINO")
    private String cepDestino;

    @Column(name = "ENDERECO_DESTINO")
    private String cidadeDestino;

    @Column(name = "DT_COLETA")
    private LocalDateTime dataColeta;

    @Column(name = "DT_ENTREGA")
    private LocalDateTime dataEntrega;

    @Column(name = "PESO_TOTAL")
    private Double pesoTotal;

    @Column(name = "VOLUME_TOTAL")
    private Double volumeTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_FRETE")
    private StatusFreteEnum status;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

    @Column(name = "DT_CRIACAO")
    private LocalDateTime dataCriacao;

    @Column(name = "DT_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    @Column(name = "TIPO_CARGA")
    private String tipoCarga;
}