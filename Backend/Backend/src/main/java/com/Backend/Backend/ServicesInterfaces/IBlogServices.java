package com.Backend.Backend.ServicesInterfaces;

import com.Backend.Backend.Dtos.BlogAppreciationDto;
import com.Backend.Backend.Dtos.BlogRequestDto;
import com.Backend.Backend.Dtos.BlogResponseDto;

public interface IBlogServices {
    BlogResponseDto blogCreation(BlogRequestDto blogRequestDto);
    BlogResponseDto updateBlog(Long blogId, BlogRequestDto blogRequestDto);
    void deleteBlog(Long id);
    void appreciateBlog(Long id, BlogAppreciationDto blogAppreciationDto);
}
