package com.Backend.Backend.Dtos;

import com.Backend.Backend.Enumerations.BlogType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Collection;

@Data
public class BlogCreationDto {
    @NotBlank(message = "The content shouldn't be empty")
    private String content;

    @NotBlank(message = "The content shouldn't be empty")
    private Collection<BlogType> blogTypes;

    private BloggerResponseDto bloggerResponseDto;
}
