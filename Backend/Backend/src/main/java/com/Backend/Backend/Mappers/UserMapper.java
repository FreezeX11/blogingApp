package com.Backend.Backend.Mappers;

import com.Backend.Backend.Dtos.BloggerCreationDto;
import com.Backend.Backend.Dtos.UserRequestDto;
import com.Backend.Backend.Dtos.UserResponseDto;
import com.Backend.Backend.Entities.Blogger;
import com.Backend.Backend.Entities.ParentUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserMapper {

    public UserResponseDto toUserResponseDto(ParentUser user) {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setProfileImage(user.getProfileImage());

        return userResponseDto;
    }

    public Blogger toBlogger(UserResponseDto userResponseDto) {
        Blogger blogger = new Blogger();

        blogger.setUsername(userResponseDto.getUsername());
        blogger.setEmail(userResponseDto.getEmail());
        blogger.setProfileImage(userResponseDto.getProfileImage());

        return blogger;
    }

    public Blogger toBlogger(UserRequestDto userRequestDto) {
        Blogger blogger = new Blogger();

        blogger.setUsername(userRequestDto.getUsername());
        blogger.setEmail(userRequestDto.getEmail());
        blogger.setProfileImage(userRequestDto.getProfileImage());

        return blogger;
    }

    public Blogger toBlogger(BloggerCreationDto bloggerCreationDto) {
        Blogger blogger = new Blogger();

        blogger.setUsername(bloggerCreationDto.getUsername());
        blogger.setEmail(bloggerCreationDto.getEmail());
        blogger.setPassword(bloggerCreationDto.getPassword());
        blogger.setProfileImage(bloggerCreationDto.getProfileImage());

        return blogger;
    }

}
