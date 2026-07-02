package com.fretebot.api.DTO;

public class VeiculoCreateDTO {

    private String placa;         // Placa do veículo (única)
    private String modelo;        // Ex: "Volvo FH", "Scania R450"
    private String tipo;          // Ex: "Caminhão", "Van", "Carreta"
    private Double capacidadeKg;  // Capacidade máxima de carga em kg
    private Long idEmpresa;       // Empresa proprietária do veículo

    public String getPlaca()              { return placa; }
    public void setPlaca(String v)        { this.placa = v; }
    public String getModelo()             { return modelo; }
    public void setModelo(String v)       { this.modelo = v; }
    public String getTipo()               { return tipo; }
    public void setTipo(String v)         { this.tipo = v; }
    public Double getCapacidadeKg()       { return capacidadeKg; }
    public void setCapacidadeKg(Double v) { this.capacidadeKg = v; }
    public Long getIdEmpresa()            { return idEmpresa; }
    public void setIdEmpresa(Long v)      { this.idEmpresa = v; }
}