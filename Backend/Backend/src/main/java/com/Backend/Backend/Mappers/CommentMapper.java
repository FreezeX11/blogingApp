package com.Backend.Backend.Mappers;

import com.Backend.Backend.Dtos.CommentRequestDto;
import com.Backend.Backend.Dtos.CommentResponseDto;
import com.Backend.Backend.Dtos.UserResponseDto;
import com.Backend.Backend.Entities.Blogger;
import com.Backend.Backend.Entities.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentMapper {
    private final UserMapper userMapper;

    public Comment toComment(Blogger blogger, CommentRequestDto commentRequestDto) {
        Comment comment = new Comment();

        comment.setContent(commentRequestDto.getContent());
        comment.setBlogger(blogger);

        return comment;
    }

    public CommentResponseDto toCommentResponseDto(Comment comment) {
        CommentResponseDto commentResponseDto = new CommentResponseDto();

        commentResponseDto.setId(comment.getId());
        commentResponseDto.setUserResponseDto(userMapper.toUserResponseDto(comment.getBlogger()));
        commentResponseDto.setCreationDate(comment.getCreationDate());
        commentResponseDto.setContent(comment.getContent());
        commentResponseDto.setLike(comment.getLike());
        commentResponseDto.setDislike(comment.getDislike());

        return commentResponseDto;
    }
}
