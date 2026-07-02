package com.fretebot.api.Entity.Enums;

import lombok.Getter;

@Getter
public enum StatusFreteEnum {
    PENDENTE(1, "PENDENTE"),
    AGUARDANDO_COTACAO(2, "AGUARDANDO COTAÇÃO"),
    COTADO(3,"COTADO"),
    APROVADO(4, "APROVADO"),
    EM_TRANSPORTE(5, "EM TRANSPORTE"),
    CONCLUIDO(6, "CONCLUÍDO"),
    CANCELADO(7, "CANCELADO");

    private int id;
    private String descricao;

    StatusFreteEnum(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    // Sobrescrita do metodo toString para retorno da descrição
    @Override
    public String toString() {
        return this.descricao;
    }
}
