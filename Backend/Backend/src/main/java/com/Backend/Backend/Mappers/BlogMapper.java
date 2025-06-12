package com.Backend.Backend.Mappers;

import com.Backend.Backend.Dtos.BlogCreationDto;
import com.Backend.Backend.Dtos.BlogResponseDto;
import com.Backend.Backend.Entities.Blog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class BlogMapper {
    private final BloggerMapper bloggerMapper;

    public Blog toBlog(BlogCreationDto blogCreationDto) {
        Blog blog = new Blog();

        blog.setContent(blogCreationDto.getContent());
        blog.setBlogger(bloggerMapper.toBlogger(blogCreationDto.getBloggerResponseDto()));
        blog.setComments(new ArrayList<>());
        blog.setBlogTypes(blogCreationDto.getBlogTypes());

        return blog;
    }

    public BlogResponseDto toBlogResponseDto(Blog blog) {
        BlogResponseDto blogResponseDto = new BlogResponseDto();

        blogResponseDto.setBlogTypes(blog.getBlogTypes());
        blogResponseDto.setBlogger(bloggerMapper.toBloggerResponseDto(blog.getBlogger()));
        blogResponseDto.setContent(blog.getContent());

        return blogResponseDto;
    }

    public Blog toBlog(BlogResponseDto blogResponseDto) {
        Blog blog = new Blog();

        blog.setId(blogResponseDto.getId());
        blog.setContent(blogResponseDto.getContent());
        blog.setBlogger(bloggerMapper.toBlogger(blogResponseDto.getBlogger()));
        blog.setComments(blogResponseDto.getComments());
        blog.setLike(blogResponseDto.getLike());
        blog.setBlogTypes(blogResponseDto.getBlogTypes());

        return blog;
    }

}
