package com.Backend.Backend.Services;

import com.Backend.Backend.Dtos.CommentRequestDto;
import com.Backend.Backend.Entities.Comment;
import com.Backend.Backend.Mappers.CommentMapper;
import com.Backend.Backend.Repositories.CommentRepository;
import com.Backend.Backend.ServicesInterfaces.ICommentServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServices implements ICommentServices {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public void addComment(CommentRequestDto commentRequestDto) {
        commentRepository.save(commentMapper.toComment(commentRequestDto));
    }

    public void updateComment(Long id, CommentRequestDto commentRequestDto) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment with id:" + id + "not found :-("));

        existingComment.setContent(commentRequestDto.getContent());
        commentRepository.save(existingComment);
    }

    public void deleteComment(Long id) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment with id:" + id + "not found :-("));
        commentRepository.delete(existingComment);
    }
}
