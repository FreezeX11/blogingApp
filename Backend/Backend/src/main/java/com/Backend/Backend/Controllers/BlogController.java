package com.Backend.Backend.Controllers;

import com.Backend.Backend.Dtos.*;
import com.Backend.Backend.Services.BlogServices;
import com.Backend.Backend.Services.CommentServices;
import com.Backend.Backend.Services.FavoriteServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/blogs")
@RestController
@AllArgsConstructor
public class BlogController {
    private final BlogServices blogServices;
    private final CommentServices commentServices;
    private final FavoriteServices favoriteServices;

    @PostMapping
    public ResponseEntity<BlogResponseDto> blogCreation(@Valid @RequestBody BlogRequestDto blogRequestDto) {
        BlogResponseDto blogResponseDto = blogServices.blogCreation(blogRequestDto);
        return new ResponseEntity<>(blogResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponseDto> updateBlog(
            @PathVariable Long id,
            @Valid @RequestBody BlogRequestDto blogRequestDto
    ) {
        BlogResponseDto blogResponseDto = blogServices.updateBlog(id, blogRequestDto);
        return new ResponseEntity<>(blogResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogServices.deleteBlog(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/appreciate")
    public ResponseEntity<Void> appreciateBlog(
            @PathVariable Long id,
            @Valid @RequestBody BlogAppreciationDto blogAppreciationDto
    ) {
        blogServices.appreciateBlog(id, blogAppreciationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BlogResponseDto>> getBlogs() {
        return new ResponseEntity<>(blogServices.getBlogs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponseDto> getBlog(@PathVariable Long id) {
        return new ResponseEntity<>(blogServices.getBlog(id), HttpStatus.OK);
    }

    @GetMapping("/blogger/{bloggerId}")
    public ResponseEntity<List<BlogResponseDto>> getBlogByBlogger(@PathVariable Long bloggerId) {
        return new ResponseEntity<>(blogServices.getBlogsByBlogger(bloggerId), HttpStatus.OK);
    }

    //Comment

    @PostMapping("/{id}/comments")
    public ResponseEntity<Void> addComment(
            @PathVariable Long id,
            @Valid @RequestBody CommentRequestDto commentRequestDto
    ) {
        commentServices.addComment(commentRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}/comments/{commentId}")
    public ResponseEntity<Void> updateComment(
            @PathVariable Long id,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequestDto commentRequestDto
    ) {
        commentServices.updateComment(commentId, commentRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentServices.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{id}/comments/{commentId}/appreciate")
    public ResponseEntity<Void> appreciateComment(
            @PathVariable Long commentId,
            @Valid @RequestBody CommentAppreciationDto commentAppreciationDto
    ) {
        commentServices.appreciateComment(commentId, commentAppreciationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Favorite

    @PostMapping("/favorites")
    public ResponseEntity<Void> addBlogToFavorites(@Valid @RequestBody FavoriteDto favoriteDto ) {
        favoriteServices.addBlogToFavorites(favoriteDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/favorites")
    public ResponseEntity<Void> removeBlogToFavorite(@Valid @RequestBody FavoriteDto favoriteDto ) {
        favoriteServices.removeBlogToFavorites(favoriteDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/favorites/{favoriteId}")
    public ResponseEntity<List<BlogResponseDto>> getFavoriteBlogs(@PathVariable Long favoriteId) {
        return new ResponseEntity<>(favoriteServices.getFavoriteBlogs(favoriteId), HttpStatus.OK);
    }

}
