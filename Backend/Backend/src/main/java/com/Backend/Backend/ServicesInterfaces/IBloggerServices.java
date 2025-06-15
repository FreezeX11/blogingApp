package com.Backend.Backend.ServicesInterfaces;

import com.Backend.Backend.Dtos.BloggerCreationDto;
import com.Backend.Backend.Dtos.UserRequestDto;
import com.Backend.Backend.Dtos.UserResponseDto;

public interface IBloggerServices {
    void bloggerCreation(BloggerCreationDto bloggerCreationDto);
    UserResponseDto updateBlogger(Long id, UserRequestDto userRequestDto);
    void deleteBlogger(Long id);
}
