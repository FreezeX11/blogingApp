package com.Backend.Backend.Dtos;

import lombok.Data;


@Data
public class BloggerResponseDto {
    private Long id;
    private String username;
    private String email;
    private byte[] profileImage;
}
