package com.Backend.Backend.ServicesInterfaces;

import com.Backend.Backend.Dtos.BlogAppreciationDto;
import com.Backend.Backend.Dtos.CommentAppreciationDto;
import com.Backend.Backend.Dtos.CommentRequestDto;

public interface ICommentServices {
    void addComment(CommentRequestDto commentRequestDto);
    void updateComment(Long id, CommentRequestDto commentRequestDto);
    void deleteComment(Long id);
    void appreciateComment(Long id, CommentAppreciationDto commentAppreciationDto);
}
