package com.Backend.Backend.ServicesInterfaces;

import com.Backend.Backend.Dtos.CommentCreationDto;
import com.Backend.Backend.Dtos.CommentRequestDto;

public interface ICommentServices {
    void addComment(CommentCreationDto commentCreationDto);
    void updateComment(CommentRequestDto commentRequestDto);
    void deleteComment(Long id);
}
