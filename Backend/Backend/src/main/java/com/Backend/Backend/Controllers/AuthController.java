package com.Backend.Backend.Controllers;

import com.Backend.Backend.Dtos.BloggerCreationDto;
import com.Backend.Backend.Dtos.UserResponseDto;
import com.Backend.Backend.Dtos.LoginRequestDto;
import com.Backend.Backend.Entities.Admin;
import com.Backend.Backend.Entities.Blogger;
import com.Backend.Backend.Entities.ParentUser;
import com.Backend.Backend.Mappers.UserMapper;
import com.Backend.Backend.Repositories.ParentUserRepository;
import com.Backend.Backend.Services.UserServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private final ParentUserRepository parentUserRepository;
    private final UserServices userServices;
    private final UserMapper userMapper;


    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody BloggerCreationDto bloggerCreationDto) {
        userServices.bloggerCreation(bloggerCreationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        authenticationManager.authenticate(authenticationRequest);
        ParentUser user = parentUserRepository.findByUsername(loginRequestDto.getUsername()).get();
        if(user instanceof Blogger blogger) {
            UserResponseDto userResponseDto = userMapper.toUserResponseDto(blogger);
            return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
        } else if (user instanceof Admin admin) {
            UserResponseDto userResponseDto = userMapper.toUserResponseDto(admin);
            return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("a problem has occurred", HttpStatus.FORBIDDEN);
    }

}
