package com.Backend.Backend.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequestDto {
    @NotNull(message = "ID can't be null")
    private Long bloggerId;

    @NotBlank(message = "Comment content can't be null")
    private String content;
}
