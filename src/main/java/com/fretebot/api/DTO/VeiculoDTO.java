package com.fretebot.api.DTO;

import com.fretebot.api.Entity.Veiculo;

public class VeiculoDTO {

    private Long idVeiculo;
    private String placa;
    private String modelo;
    private String tipo;
    private Double capacidadeKg;

    // Resumo da empresa vinculada
    private Long idEmpresa;
    private String nomeFantasiaEmpresa;

    // Construtor que mapeia a entity
    public VeiculoDTO(Veiculo veiculo) {
        this.idVeiculo           = veiculo.getIdVeiculo();
        this.placa               = veiculo.getPlaca();
        this.modelo              = veiculo.getModelo();
        this.tipo                = veiculo.getTipo();
        this.capacidadeKg        = veiculo.getCapacidadeKg();
        this.idEmpresa           = veiculo.getEmpresa() != null ? veiculo.getEmpresa().getIdEmpresa() : null;
        this.nomeFantasiaEmpresa = veiculo.getEmpresa() != null ? veiculo.getEmpresa().getNomeFantasia() : null;
    }

    public Long getIdVeiculo()              { return idVeiculo; }
    public String getPlaca()               { return placa; }
    public String getModelo()              { return modelo; }
    public String getTipo()                { return tipo; }
    public Double getCapacidadeKg()        { return capacidadeKg; }
    public Long getIdEmpresa()             { return idEmpresa; }
    public String getNomeFantasiaEmpresa() { return nomeFantasiaEmpresa; }
}