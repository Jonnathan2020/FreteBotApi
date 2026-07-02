package com.fretebot.api.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_ITEM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ITEM")
    private Long idItem;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "PESO")
    private Double peso;

    @Column(name = "ALTURA")
    private Double altura;

    @Column(name = "LARGURA")
    private Double largura;

    @Column(name = "COMPRIMENTO")
    private Double comprimento;

    @Column(name = "TIPO_ITEM")
    private String tipoItem;
}