package com.Backend.Backend.Dtos;

import com.Backend.Backend.Entities.Favorites;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class BloggerResponseDto {
    private Long id;
    private String username;
    private String email;
    private byte[] profileImage;
}
