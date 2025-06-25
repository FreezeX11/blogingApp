package com.Backend.Backend.Services;

import com.Backend.Backend.Dtos.BlogAppreciationDto;
import com.Backend.Backend.Dtos.CommentAppreciationDto;
import com.Backend.Backend.Dtos.CommentRequestDto;
import com.Backend.Backend.Entities.Blog;
import com.Backend.Backend.Entities.Blogger;
import com.Backend.Backend.Entities.Comment;
import com.Backend.Backend.Entities.ParentUser;
import com.Backend.Backend.Mappers.CommentMapper;
import com.Backend.Backend.Repositories.BlogRepository;
import com.Backend.Backend.Repositories.CommentRepository;
import com.Backend.Backend.Repositories.ParentUserRepository;
import com.Backend.Backend.ServicesInterfaces.ICommentServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServices implements ICommentServices {
    private final ParentUserRepository parentUserRepository;
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public void addComment(CommentRequestDto commentRequestDto) {
        ParentUser user = parentUserRepository.findById(commentRequestDto.getBloggerId())
                .orElseThrow(() -> new RuntimeException("User with id:" + commentRequestDto.getBloggerId() + "not found :-("));

        Blog blog = blogRepository.findById(commentRequestDto.getBlogId())
                .orElseThrow(() -> new RuntimeException("Blog with id:" + commentRequestDto.getBlogId() + "not found :-("));

        if(!(user instanceof Blogger blogger)) {
            throw new IllegalArgumentException("User is not a blogger and cannot create a blog.");
        } else {
            Comment comment = commentMapper.toComment(blogger, commentRequestDto);

            blog.getComments().add(comment);
            blogRepository.save(blog);
        }

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

    public void appreciateComment(Long id, CommentAppreciationDto commentAppreciationDto) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment with id:" + id +"not found :-("));

        switch (commentAppreciationDto.getOperation()) {
            case "like" ->  existingComment.setLike(Math.max(0, existingComment.getLike()) + 1);
            case "dislike" -> existingComment.setDislike(Math.max(0, existingComment.getDislike()) + 1);
            case "unlike" -> existingComment.setLike(Math.max(0, existingComment.getLike()) - 1);
            case "not_dislike" -> existingComment.setDislike(Math.max(0, existingComment.getDislike()) - 1);
        }

        commentRepository.save(existingComment);
    }
}
