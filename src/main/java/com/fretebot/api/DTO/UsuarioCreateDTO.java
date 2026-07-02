
package com.fretebot.api.DTO;

import com.fretebot.api.Entity.Enums.TipoUsuarioEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateDTO {

    private String nome;

    private String email;

    private String senha;

    private String telefone;

    private TipoUsuarioEnum tipo;
}
