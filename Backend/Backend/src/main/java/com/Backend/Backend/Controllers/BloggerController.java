package com.Backend.Backend.Controllers;

import com.Backend.Backend.Dtos.UserRequestDto;
import com.Backend.Backend.Dtos.UserResponseDto;
import com.Backend.Backend.Services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/bloggers")
public class BloggerController {
    private final UserServices bloggerServices;

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateBlogger(
            @PathVariable Long id,
            @RequestBody UserRequestDto userRequestDto
    ) {
        return new ResponseEntity<>(
                bloggerServices.updateBlogger(id, userRequestDto),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        bloggerServices.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(bloggerServices.getBlogger(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        return new ResponseEntity<>(bloggerServices.getBloggers(), HttpStatus.OK);
    }
}
