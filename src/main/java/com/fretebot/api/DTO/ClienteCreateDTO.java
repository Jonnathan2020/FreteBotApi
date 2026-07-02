package com.fretebot.api.DTO;

import lombok.Getter;

@Getter
public class ClienteCreateDTO {
    private String documento;
    public String nome;
    public Long telefone;
}
