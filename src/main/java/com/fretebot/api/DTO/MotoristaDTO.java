package com.fretebot.api.DTO;

import com.fretebot.api.Entity.Motorista;

public class MotoristaDTO {

    private Long idMotorista;
    private String nome;
    private String cpf;
    private String cnh;

    // Resumo da empresa — evita nested objects profundos na resposta
    private Long idEmpresa;
    private String nomeFantasiaEmpresa;

    // Construtor que mapeia a entity
    public MotoristaDTO(Motorista motorista) {
        this.idMotorista        = motorista.getIdMotorista();
        this.nome               = motorista.getNome();
        this.cpf                = motorista.getCpf();
        this.cnh                = motorista.getCnh();
        this.idEmpresa          = motorista.getEmpresa() != null ? motorista.getEmpresa().getIdEmpresa() : null;
        this.nomeFantasiaEmpresa = motorista.getEmpresa() != null ? motorista.getEmpresa().getNomeFantasia() : null;
    }

    public Long getIdMotorista()            { return idMotorista; }
    public String getNome()                 { return nome; }
    public String getCpf()                  { return cpf; }
    public String getCnh()                  { return cnh; }
    public Long getIdEmpresa()              { return idEmpresa; }
    public String getNomeFantasiaEmpresa()  { return nomeFantasiaEmpresa; }
}
