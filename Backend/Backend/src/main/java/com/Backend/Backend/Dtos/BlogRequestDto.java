package com.Backend.Backend.Dtos;

import com.Backend.Backend.Enumerations.BlogType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Collection;

@Data
public class BlogRequestDto {
    @NotNull(message = "ID can't not be null")
    private Long id;

    private String content;
    private BloggerRequestDto blogger;
    private Collection<BlogType> blogTypes;
}
