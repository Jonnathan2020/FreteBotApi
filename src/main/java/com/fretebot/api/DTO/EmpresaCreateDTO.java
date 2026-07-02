package com.fretebot.api.DTO;

public class EmpresaCreateDTO {

    private String razaoSocial;   // Razão social da empresa (obrigatório)
    private String nomeFantasia;  // Nome fantasia (obrigatório)
    private String documento;          // CNPJ único (obrigatório)
    private Long idUsuario;       // ID do Usuario que será vinculado à empresa

    public String getRazaoSocial()          { return razaoSocial; }
    public void setRazaoSocial(String v)    { this.razaoSocial = v; }
    public String getNomeFantasia()         { return nomeFantasia; }
    public void setNomeFantasia(String v)   { this.nomeFantasia = v; }
    public String getDocumento()                 { return documento; }
    public void setDocumento(String v)           { this.documento = v; }
    public Long getIdUsuario()              { return idUsuario; }
    public void setIdUsuario(Long v)        { this.idUsuario = v; }
}