// ============================================================
// FreteDTO.java  —  resposta da API (o que o cliente recebe)
// ============================================================
package com.fretebot.api.DTO;

import com.fretebot.api.Entity.Enums.StatusFreteEnum;
import com.fretebot.api.Entity.Frete;

import java.time.LocalDateTime;

// DTO de saída: representa um frete completo retornado pela API.
// Construído a partir da entity Frete — nunca expõe a entity diretamente,
// o que protege o modelo de domínio e evita serialização circular com JPA.
public class FreteDTO {

    private Long idFrete;
    private String cepOrigem;
    private String cidadeOrigem;
    private String cepDestino;
    private String cidadeDestino;
    private LocalDateTime dataColeta;
    private LocalDateTime dataEntrega;
    private Double pesoTotal;
    private Double volumeTotal;
    private StatusFreteEnum status;
    private String descricaoStatus;   // ex: "EM TRANSPORTE" — facilita o front sem precisar mapear o enum
    private Long idCliente;           // resumo do cliente (só o ID; use um ClienteResumoDTO se precisar de mais campos)
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private String tipoCarga;

    // ── Construtor que recebe a entity e mapeia tudo aqui dentro.
    //    Mantém o mesmo padrão do AgendamentoDTO do projeto.
    public FreteDTO(Frete frete) {
        this.idFrete          = frete.getIdFrete();
        this.cepOrigem        = frete.getCepOrigem();
        this.cidadeOrigem     = frete.getCidadeOrigem();
        this.cepDestino       = frete.getCepDestino();
        this.cidadeDestino    = frete.getCidadeDestino();
        this.dataColeta       = frete.getDataColeta();
        this.dataEntrega      = frete.getDataEntrega();
        this.pesoTotal        = frete.getPesoTotal();
        this.volumeTotal      = frete.getVolumeTotal();
        this.status           = frete.getStatus();
        // Descrição legível do enum (ex: "AGUARDANDO COTAÇÃO")
        this.descricaoStatus  = frete.getStatus() != null ? frete.getStatus().getDescricao() : null;
        this.idCliente        = frete.getCliente() != null ? frete.getCliente().getIdCliente() : null;
        this.dataCriacao      = frete.getDataCriacao();
        this.dataAtualizacao  = frete.getDataAtualizacao();
        this.tipoCarga        = frete.getTipoCarga();
    }

    // ── Getters (sem setters: DTO de saída é imutável após construção)
    public Long getIdFrete()                  { return idFrete; }
    public String getCepOrigem()              { return cepOrigem; }
    public String getCidadeOrigem()           { return cidadeOrigem; }
    public String getCepDestino()             { return cepDestino; }
    public String getCidadeDestino()          { return cidadeDestino; }
    public LocalDateTime getDataColeta()      { return dataColeta; }
    public LocalDateTime getDataEntrega()     { return dataEntrega; }
    public Double getPesoTotal()              { return pesoTotal; }
    public Double getVolumeTotal()            { return volumeTotal; }
    public StatusFreteEnum getStatus()        { return status; }
    public String getDescricaoStatus()        { return descricaoStatus; }
    public Long getIdCliente()                { return idCliente; }
    public LocalDateTime getDataCriacao()     { return dataCriacao; }
    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public String getTipoCarga()              { return tipoCarga; }
}
