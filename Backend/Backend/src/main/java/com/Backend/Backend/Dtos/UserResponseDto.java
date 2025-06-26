package com.Backend.Backend.Dtos;

import lombok.Data;


@Data
public class UserResponseDto {
    private Long id;
    private String token;
    private String username;
    private String email;
    private String password;
    private byte[] profileImage;
}
