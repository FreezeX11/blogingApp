package com.Backend.Backend.Mappers;

import com.Backend.Backend.Dtos.BlogCreationDto;
import com.Backend.Backend.Dtos.BlogResponseDto;
import com.Backend.Backend.Entities.Blog;
import com.Backend.Backend.Entities.Blogger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class BlogMapper {
    private final BloggerMapper bloggerMapper;

    public Blog toBlog(Blogger blogger, BlogCreationDto blogCreationDto) {
        Blog blog = new Blog();

        blog.setContent(blogCreationDto.getContent());
        blog.setBlogger(blogger);
        blog.setComments(new ArrayList<>());
        blog.setBlogTypes(blogCreationDto.getBlogTypes());

        return blog;
    }

    public BlogResponseDto toBlogResponseDto(Blog blog) {
        BlogResponseDto blogResponseDto = new BlogResponseDto();

        blogResponseDto.setId(blog.getId());
        blogResponseDto.setContent(blog.getContent());
        blogResponseDto.setBloggerId(blog.getBlogger().getId());
        blogResponseDto.setBlogTypes(blog.getBlogTypes());
        blogResponseDto.setComments(blog.getComments());
        blogResponseDto.setLike(blog.getLike());
        
        return blogResponseDto;
    }

    public Blog toBlog(Blogger blogger, BlogResponseDto blogResponseDto) {
        Blog blog = new Blog();

        blog.setId(blogResponseDto.getId());
        blog.setContent(blogResponseDto.getContent());
        blog.setBlogger(blogger);
        blog.setComments(blogResponseDto.getComments());
        blog.setLike(blogResponseDto.getLike());
        blog.setBlogTypes(blogResponseDto.getBlogTypes());

        return blog;
    }

}
