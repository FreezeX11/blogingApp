package com.Backend.Backend.Mappers;

import com.Backend.Backend.Dtos.CommentCreationDto;
import com.Backend.Backend.Entities.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentMapper {
    private final BloggerMapper bloggerMapper;

    public Comment toComment(CommentCreationDto commentCreationDto) {
        Comment comment = new Comment();

        comment.setId(commentCreationDto.getId());
        comment.setContent(commentCreationDto.getContent());
        comment.setBlogger(bloggerMapper.toBlogger(commentCreationDto.getBlogger()));
        return comment;
    }
}
