package com.Backend.Backend.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequestDto {
    @NotNull(message = "Blogger ID can't be null")
    private Long bloggerId;

    @NotNull(message = "Blogger ID can't be null")
    private Long blogId;

    @NotBlank(message = "Comment content can't be null")
    private String content;
}
