package com.fretebot.api.DTO;

public class EmpresaUpdateDTO {

    private String razaoSocial;
    private String nomeFantasia;
    private Double notaMedia;     // Pode ser atualizada por um processo de avaliação

    public String getRazaoSocial()          { return razaoSocial; }
    public void setRazaoSocial(String v)    { this.razaoSocial = v; }
    public String getNomeFantasia()         { return nomeFantasia; }
    public void setNomeFantasia(String v)   { this.nomeFantasia = v; }
    public Double getNotaMedia()            { return notaMedia; }
    public void setNotaMedia(Double v)      { this.notaMedia = v; }
}