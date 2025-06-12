package com.Backend.Backend.ServicesInterfaces;

import com.Backend.Backend.Dtos.BloggerCreationDto;
import com.Backend.Backend.Dtos.BloggerRequestDto;
import com.Backend.Backend.Dtos.BloggerResponseDto;

public interface IBloggerServices {
    void bloggerCreation(BloggerCreationDto bloggerCreationDto);
    BloggerResponseDto updateBlogger(BloggerRequestDto bloggerRequestDto);
    void deleteBlogger(Long bloggerId);
}
