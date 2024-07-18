
package com.ConsultorioOdontologico.consultorioOdontologico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginDto {
    
    private String username;
    private String contrasenia;

    public LoginDto() {
    }

    public LoginDto(String username, String contrasenia) {
        this.username = username;
        this.contrasenia = contrasenia;
    }

}
