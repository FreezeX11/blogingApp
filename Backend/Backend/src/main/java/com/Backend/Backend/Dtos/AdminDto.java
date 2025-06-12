package com.Backend.Backend.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdminDto {
    private Long id;
    private String username;
    private String email;
    private byte[] profileImage;
}
