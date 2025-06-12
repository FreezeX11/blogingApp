package com.Backend.Backend.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BloggerRequestDto {
    @NotNull(message = "ID can't not be null")
    private Long id;

    @NotBlank(message = "Email should not be null")
    private String email;

    @NotBlank(message = "Email should not be null")
    private String username;

    private byte[] profileImage;
}
