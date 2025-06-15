package com.Backend.Backend.Mappers;

import com.Backend.Backend.Dtos.BlogRequestDto;
import com.Backend.Backend.Dtos.BlogResponseDto;
import com.Backend.Backend.Entities.Blog;
import com.Backend.Backend.Entities.Blogger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class BlogMapper {
    private final UserMapper userMapper;

    public Blog toBlog(Blogger blogger, BlogRequestDto blogRequestDto) {
        Blog blog = new Blog();

        blog.setTitle(blogRequestDto.getTitle());
        blog.setContent(blogRequestDto.getContent());
        blog.setBlogTypes(blogRequestDto.getBlogTypes());
        blog.setBlogger(blogger);
        blog.setComments(new ArrayList<>());

        return blog;
    }

    public BlogResponseDto toBlogResponseDto(Blog blog) {
        BlogResponseDto blogResponseDto = new BlogResponseDto();

        blogResponseDto.setId(blog.getId());
        blogResponseDto.setTitle(blog.getTitle());
        blogResponseDto.setContent(blog.getContent());
        blogResponseDto.setUserResponseDto(userMapper.toUserResponseDto(blog.getBlogger()));
        blogResponseDto.setBlogTypes(blog.getBlogTypes());
        blogResponseDto.setComments(blog.getComments());
        blogResponseDto.setLike(blog.getLike());
        
        return blogResponseDto;
    }

//    public Blog toBlog(Blogger blogger, BlogResponseDto blogResponseDto) {
//        Blog blog = new Blog();
//
//        blog.setId(blogResponseDto.getId());
//        blog.setContent(blogResponseDto.getContent());
//        blog.setBlogger(blogger);
//        blog.setComments(blogResponseDto.getComments());
//        blog.setLike(blogResponseDto.getLike());
//        blog.setBlogTypes(blogResponseDto.getBlogTypes());
//
//        return blog;
//    }

}
