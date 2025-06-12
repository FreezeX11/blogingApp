package com.Backend.Backend.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequestDto {
    @NotNull(message = "ID can't be null")
    private Long id;

    private String content;
}
