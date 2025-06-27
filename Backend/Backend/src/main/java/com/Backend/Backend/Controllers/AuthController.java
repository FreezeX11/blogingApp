package com.Backend.Backend.Controllers;

import com.Backend.Backend.Dtos.BloggerCreationDto;
import com.Backend.Backend.Dtos.JwtResponseDto;
import com.Backend.Backend.Dtos.LoginRequestDto;
import com.Backend.Backend.Mappers.UserMapper;
import com.Backend.Backend.Repositories.ParentUserRepository;
import com.Backend.Backend.SecurityConfig.JWT.JwtUtils;
import com.Backend.Backend.Services.UserServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private final UserServices userServices;
    private final JwtUtils jwtUtils;


    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody BloggerCreationDto bloggerCreationDto) {
        userServices.bloggerCreation(bloggerCreationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken
                .unauthenticated(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationRequest);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateToken(userDetails.getUsername());
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        JwtResponseDto jwtResponseDto = new JwtResponseDto(
                jwtToken,
                userDetails.getUsername(),
                authorities
        );

        return new ResponseEntity<>(jwtResponseDto, HttpStatus.OK);
    }

}
