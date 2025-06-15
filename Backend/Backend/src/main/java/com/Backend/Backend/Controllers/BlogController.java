package com.Backend.Backend.Controllers;

import com.Backend.Backend.Dtos.*;
import com.Backend.Backend.Services.BlogServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/blog")
@RestController
@AllArgsConstructor
public class BlogController {
    private final BlogServices blogServices;

    @PostMapping("/submit")
    public ResponseEntity<BlogResponseDto> blogCreation(@Valid @RequestBody BlogRequestDto blogRequestDto) {
        BlogResponseDto blogResponseDto = blogServices.blogCreation(blogRequestDto);
        return new ResponseEntity<>(blogResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponseDto> updateBlog(@PathVariable Long blogId, @Valid @RequestBody BlogRequestDto blogRequestDto) {
        BlogResponseDto blogResponseDto = blogServices.updateBlog(blogId, blogRequestDto);
        return new ResponseEntity<>(blogResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long blogId) {
        blogServices.deleteBlog(blogId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/appreciate")
    public ResponseEntity<Void> appreciateBlog(@Valid @RequestBody AppreciationDto appreciationDto) {
        blogServices.appreciateBlog(appreciationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
