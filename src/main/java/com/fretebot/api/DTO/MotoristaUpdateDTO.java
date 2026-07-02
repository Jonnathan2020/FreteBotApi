package com.fretebot.api.DTO;

public class MotoristaUpdateDTO {

    private String nome;
    private String cnh;        // CNH pode ser renovada/corrigida
    private Long idEmpresa;    // Permite transferir motorista entre empresas

    public String getNome()             { return nome; }
    public void setNome(String v)       { this.nome = v; }
    public String getCnh()              { return cnh; }
    public void setCnh(String v)        { this.cnh = v; }
    public Long getIdEmpresa()          { return idEmpresa; }
    public void setIdEmpresa(Long v)    { this.idEmpresa = v; }
}