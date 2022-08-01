package com.preaceleracion.alkemy.auth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@Data
public class UserDTO {

    @Email(message = "El usuario debe ser un email")
    private String username;
    @Size(min = 8)
    private String password;
}
