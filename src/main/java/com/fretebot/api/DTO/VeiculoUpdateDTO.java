package com.fretebot.api.DTO;

public class VeiculoUpdateDTO {

    private String modelo;
    private String tipo;
    private Double capacidadeKg;
    private Long idEmpresa;       // Permite transferir veículo entre empresas

    public String getModelo()             { return modelo; }
    public void setModelo(String v)       { this.modelo = v; }
    public String getTipo()               { return tipo; }
    public void setTipo(String v)         { this.tipo = v; }
    public Double getCapacidadeKg()       { return capacidadeKg; }
    public void setCapacidadeKg(Double v) { this.capacidadeKg = v; }
    public Long getIdEmpresa()            { return idEmpresa; }
    public void setIdEmpresa(Long v)      { this.idEmpresa = v; }
}