package com.Backend.Backend.Dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CommentResponseDto {
    private Long id;
    private UserResponseDto userResponseDto;
    private Date creationDate;
    private String content;
    private Long like = 0L;
    private Long dislike = 0L;
}
