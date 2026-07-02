package com.fretebot.api.DTO;

public class MotoristaCreateDTO {

    private String nome;       // Nome completo do motorista
    private String cpf;        // CPF do motorista
    private String cnh;        // Número da CNH
    private Long idEmpresa;    // Empresa à qual o motorista pertence

    public String getNome()             { return nome; }
    public void setNome(String v)       { this.nome = v; }
    public String getCpf()              { return cpf; }
    public void setCpf(String v)        { this.cpf = v; }
    public String getCnh()              { return cnh; }
    public void setCnh(String v)        { this.cnh = v; }
    public Long getIdEmpresa()          { return idEmpresa; }
    public void setIdEmpresa(Long v)    { this.idEmpresa = v; }
}