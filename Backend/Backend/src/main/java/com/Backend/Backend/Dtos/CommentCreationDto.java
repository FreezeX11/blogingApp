package com.Backend.Backend.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentCreationDto {
    private BloggerRequestDto blogger;

    @NotBlank(message = "Content can't be null")
    private String content;
}
