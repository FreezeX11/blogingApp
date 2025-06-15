package com.Backend.Backend.Dtos;

import lombok.Data;


@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private byte[] profileImage;
}
