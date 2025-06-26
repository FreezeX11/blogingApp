package com.Backend.Backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponseDto {
    private String token;
    private String username;
    private List<String> authorities;
}
