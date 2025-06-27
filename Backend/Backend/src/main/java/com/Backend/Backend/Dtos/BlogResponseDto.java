package com.Backend.Backend.Dtos;

import com.Backend.Backend.Entities.Comment;
import com.Backend.Backend.Enumerations.BlogType;
import lombok.Data;

import java.util.Collection;

@Data
public class BlogResponseDto {
    private Long id;
    private UserResponseDto userResponseDto;
    private String title;
    private String content;
    private Collection<BlogType> blogTypes;
    private Collection<Comment> comments;
    private Long like;
    private Long dislike;
}
