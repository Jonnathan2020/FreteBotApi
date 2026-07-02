package com.fretebot.api.DTO;

import lombok.Getter;

@Getter
public class ClienteUpdateDTO {
    private Long idCliente;
    private String documento;
    public String nome;
    public Long telefone;
}
