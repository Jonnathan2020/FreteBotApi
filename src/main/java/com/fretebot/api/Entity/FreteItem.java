package com.fretebot.api.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_FRETE_ITEM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FRETE_ITEM")
    private Long idFreteItem;

    @ManyToOne
    @JoinColumn(name = "ID_FRETE")
    private Frete frete;

    @ManyToOne
    @JoinColumn(name = "ID_ITEM")
    private Item item;

    @Column(name = "QTD_ITENS")
    private Integer quantidade;
}