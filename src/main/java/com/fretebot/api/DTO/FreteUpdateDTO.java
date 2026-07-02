package com.fretebot.api.DTO;

import java.time.LocalDateTime;

public class FreteUpdateDTO {

    private String cepOrigem;
    private String cidadeOrigem;
    private String cepDestino;
    private String cidadeDestino;
    private LocalDateTime dataColeta;
    private LocalDateTime dataEntrega;
    private Double pesoTotal;
    private Double volumeTotal;
    private String tipoCarga;
    // Vínculo
    private Long idCliente;          // ID do cliente dono do frete

    // ── Getters e Setters
    public String getCepOrigem()               { return cepOrigem; }
    public void setCepOrigem(String v)         { this.cepOrigem = v; }
    public String getCidadeOrigem()            { return cidadeOrigem; }
    public void setCidadeOrigem(String v)      { this.cidadeOrigem = v; }
    public String getCepDestino()              { return cepDestino; }
    public void setCepDestino(String v)        { this.cepDestino = v; }
    public String getCidadeDestino()           { return cidadeDestino; }
    public void setCidadeDestino(String v)     { this.cidadeDestino = v; }
    public LocalDateTime getDataColeta()       { return dataColeta; }
    public void setDataColeta(LocalDateTime v) { this.dataColeta = v; }
    public LocalDateTime getDataEntrega()      { return dataEntrega; }
    public void setDataEntrega(LocalDateTime v){ this.dataEntrega = v; }
    public Double getPesoTotal()               { return pesoTotal; }
    public void setPesoTotal(Double v)         { this.pesoTotal = v; }
    public Double getVolumeTotal()             { return volumeTotal; }
    public void setVolumeTotal(Double v)       { this.volumeTotal = v; }
    public String getTipoCarga()               { return tipoCarga; }
    public void setTipoCarga(String v)         { this.tipoCarga = v; }
}