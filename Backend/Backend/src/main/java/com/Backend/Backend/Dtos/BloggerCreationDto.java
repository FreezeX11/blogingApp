package com.Backend.Backend.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class BloggerCreationDto {
    @NotBlank(message = "the dev name should not be null")
    private String username;

    @NotBlank(message = "the email should not be null")
    private String email;

    @NotBlank(message = "the password should not be null")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 8 characters")
    private String password;

    @Nullable
    private byte[] profileImage;
}
