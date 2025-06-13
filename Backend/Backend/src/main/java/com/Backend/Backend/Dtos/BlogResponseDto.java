package com.Backend.Backend.Dtos;

import com.Backend.Backend.Entities.Comment;
import com.Backend.Backend.Enumerations.BlogType;
import lombok.Data;

import java.util.Collection;

@Data
public class BlogResponseDto {
    private Long id;
    private String content;
    private Long BloggerId;
    private Collection<BlogType> blogTypes;
    private Collection<Comment> comments;
    private Long like;
}
