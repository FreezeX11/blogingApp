package com.Backend.Backend.Dtos;

import com.Backend.Backend.Enumerations.BlogType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Collection;

@Data
public class BlogRequestDto {
    @NotBlank(message = "The title shouldn't be empty")
    private String title;

    @NotBlank(message = "The content shouldn't be empty")
    private String content;

    @NotBlank(message = "The blog type shouldn't be empty")
    private Collection<BlogType> blogTypes;

    private Long bloggerId;
}
