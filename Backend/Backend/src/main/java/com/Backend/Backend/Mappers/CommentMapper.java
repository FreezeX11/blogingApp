package com.Backend.Backend.Mappers;

import com.Backend.Backend.Dtos.CommentRequestDto;
import com.Backend.Backend.Entities.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentMapper {
    private final UserMapper userMapper;

    public Comment toComment(CommentRequestDto commentRequestDto) {
        Comment comment = new Comment();

        comment.setId(commentRequestDto.getId());
        comment.setContent(commentRequestDto.getContent());
        comment.setBlogger(userMapper.toBlogger(commentRequestDto.getBlogger()));
        return comment;
    }
}
