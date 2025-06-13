package com.Backend.Backend.Services;

import com.Backend.Backend.Dtos.*;
import com.Backend.Backend.Entities.Blog;
import com.Backend.Backend.Entities.Blogger;
import com.Backend.Backend.Entities.ParentUser;
import com.Backend.Backend.ServicesInterfaces.IBlogServices;
import com.Backend.Backend.Mappers.BlogMapper;
import com.Backend.Backend.Repositories.BlogRepository;
import com.Backend.Backend.Repositories.ParentUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class BlogServices implements IBlogServices {
    private final BlogRepository blogRepository;
    private final ParentUserRepository parentUserRepository;
    private final BlogMapper blogMapper;

    public void blogCreation(BlogCreationDto blogCreationDto) {
        ParentUser user = parentUserRepository.findById(blogCreationDto.getBloggerId())
                .orElseThrow(() -> new RuntimeException("User with id:" + blogCreationDto.getBloggerId() + "not found :-("));

        if (user instanceof Blogger blogger) {
            blogMapper.toBlogResponseDto(blogRepository.save(blogMapper.toBlog(blogger, blogCreationDto)));
        }
    }

    public BlogResponseDto updateBlog(Long blogId, BlogRequestDto blogRequestDto) {
        Blog existingBlog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog with id:" + blogId +"not found :-("));

        ParentUser user = parentUserRepository.findById(existingBlog.getBlogger().getId())
                .orElseThrow(() -> new RuntimeException("User with id:" + existingBlog.getBlogger().getId() +"not found :-("));

        if (user instanceof Blogger) {
            existingBlog.setBlogTypes(blogRequestDto.getBlogTypes());
            existingBlog.setContent(blogRequestDto.getContent());
        }
        return blogMapper.toBlogResponseDto(existingBlog);
    }

    public void deleteBlog(Long blogId) {
        Blog existingBlog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog with id:" + blogId +"not found :-("));
        blogRepository.delete(existingBlog);
    }

    public void appreciateBlog(AppreciationDto appreciationDto) {
        Blog existingBlog = blogRepository.findById(appreciationDto.getBlogId())
                .orElseThrow(() -> new RuntimeException("Blog with id:" + appreciationDto.getBlogId() +"not found :-("));
        Long likeCount = "like".equals(appreciationDto.getOperation() )
                ? existingBlog.getLike() + 1
                : existingBlog.getLike() - 1;
        existingBlog.setLike(likeCount);
        blogRepository.save(existingBlog);
    }
}
