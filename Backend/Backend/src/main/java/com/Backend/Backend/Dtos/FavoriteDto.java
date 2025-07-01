package com.Backend.Backend.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FavoriteDto {
    @NotNull(message = "Blog ID can't be null")
    private Long blogId;

    @NotNull(message = "Blogger ID can't be null")
    private Long bloggerId;
}
