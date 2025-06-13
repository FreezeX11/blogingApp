package com.Backend.Backend.Dtos;

import com.Backend.Backend.Enumerations.BlogType;
import lombok.Data;

import java.util.Collection;

@Data
public class BlogRequestDto {
    private String content;
    private Collection<BlogType> blogTypes;
}
