package com.fretebot.api.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_EMPRESA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPRESA")
    private Long idEmpresa;

    @Column(nullable = false)
    private String razaoSocial;

    @Column(nullable = false)
    private String nomeFantasia;

    @Column(nullable = false, unique = true)
    private String documento;

    private Double notaMedia;

    @OneToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;
}
