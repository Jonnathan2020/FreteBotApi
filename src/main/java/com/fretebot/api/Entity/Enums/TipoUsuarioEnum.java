package com.fretebot.api.Entity.Enums;

import lombok.Getter;

@Getter
public enum TipoUsuarioEnum {

    ADMIN(1, "ADMIN"),
    USUARION1(2, "ADMINISTRADOR PAINEL"),
    USUARION2(3, "VENDEDOR"),
    USUARION3(4, "OPERADOR");

    private int id;
    private String descricao;

    TipoUsuarioEnum(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    // Sobrescrita do metodo toString para retorno da descrição
    @Override
    public String toString() {
        return this.descricao;
    }
}
