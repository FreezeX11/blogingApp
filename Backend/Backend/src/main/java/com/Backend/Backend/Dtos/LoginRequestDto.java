package com.Backend.Backend.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotBlank(message = "Username can't be null")
    private String username;

    @NotBlank(message = "password can't be null")
    private String password;
}
