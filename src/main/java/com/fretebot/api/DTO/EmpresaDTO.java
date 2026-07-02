package com.fretebot.api.DTO;

import com.fretebot.api.Entity.Empresa;

// ============================================================
// EmpresaDTO.java  —  resposta da API
// ============================================================
// DTO de saída: representa uma Empresa retornada pela API.
// O campo usuarioId expõe só o ID do Usuario vinculado,
// evitando serialização circular e expor dados sensíveis.
public class EmpresaDTO {

    private Long idEmpresa;
    private String razaoSocial;
    private String nomeFantasia;
    private String documento;
    private Double notaMedia;
    private Long idUsuario; // Apenas o ID do Usuario vinculado

    // Construtor que mapeia a entity — mesmo padrão do FreteDTO
    public EmpresaDTO(Empresa empresa) {
        this.idEmpresa    = empresa.getIdEmpresa();
        this.razaoSocial  = empresa.getRazaoSocial();
        this.nomeFantasia = empresa.getNomeFantasia();
        this.documento    = empresa.getDocumento();
        this.notaMedia    = empresa.getNotaMedia();
        this.idUsuario    = empresa.getUsuario() != null ? empresa.getUsuario().getIdUsuario() : null;
    }

    public Long getIdEmpresa()      { return idEmpresa; }
    public String getRazaoSocial()  { return razaoSocial; }
    public String getNomeFantasia() { return nomeFantasia; }
    public String getDocumento()         { return documento; }
    public Double getNotaMedia()    { return notaMedia; }
    public Long getIdUsuario()      { return idUsuario; }
}


// ============================================================
// EmpresaCreateDTO.java  —  entrada para CADASTRAR uma empresa
// ============================================================
// notaMedia não entra aqui: começa como null e é atualizada
// conforme avaliações forem registradas.

/*

*/


// ============================================================
// EmpresaUpdateDTO.java  —  entrada para ATUALIZAR uma empresa
// ============================================================
// Todos os campos são opcionais (nullable).
// O service só toca no campo se vier não-nulo no JSON.

/*

*/