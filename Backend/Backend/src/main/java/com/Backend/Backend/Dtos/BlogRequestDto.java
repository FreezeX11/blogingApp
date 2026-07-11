package com.Backend.Backend.Dtos;

import com.Backend.Backend.Enumerations.BlogType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class BlogRequestDto {
    @NotBlank(message = "The title shouldn't be empty")
    private String title;

    @NotBlank(message = "The content shouldn't be empty")
    private String content;

    @Size(min = 1, message = "Au moins un type de blog est requis")
    private Collection<BlogType> blogTypes = new ArrayList<>();

    private Long bloggerId;
}
