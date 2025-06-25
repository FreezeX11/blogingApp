package com.Backend.Backend.Mappers;

import com.Backend.Backend.Dtos.CommentRequestDto;
import com.Backend.Backend.Entities.Blogger;
import com.Backend.Backend.Entities.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentMapper {

    public Comment toComment(Blogger blogger, CommentRequestDto commentRequestDto) {
        Comment comment = new Comment();

        comment.setContent(commentRequestDto.getContent());
        comment.setBlogger(blogger);

        return comment;
    }
}
