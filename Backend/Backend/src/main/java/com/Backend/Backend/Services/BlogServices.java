package com.Backend.Backend.Services;

import com.Backend.Backend.Dtos.*;
import com.Backend.Backend.Entities.Blog;
import com.Backend.Backend.Entities.Blogger;
import com.Backend.Backend.Entities.Favorites;
import com.Backend.Backend.Entities.ParentUser;
import com.Backend.Backend.Repositories.FavoriteRepository;
import com.Backend.Backend.ServicesInterfaces.IBlogServices;
import com.Backend.Backend.Mappers.BlogMapper;
import com.Backend.Backend.Repositories.BlogRepository;
import com.Backend.Backend.Repositories.ParentUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class BlogServices implements IBlogServices {
    private final BlogRepository blogRepository;
    private final ParentUserRepository parentUserRepository;
    private final FavoriteRepository favoriteRepository;
    private final BlogMapper blogMapper;

    public BlogResponseDto blogCreation(BlogRequestDto blogRequestDto) {
        ParentUser user = parentUserRepository.findById(blogRequestDto.getBloggerId())
                .orElseThrow(() -> new RuntimeException("User with id:" + blogRequestDto.getBloggerId() + "not found :-("));

        if (!(user instanceof Blogger blogger)) {
            throw new IllegalArgumentException("User is not a blogger and cannot create a blog.");
        }

        return blogMapper.toBlogResponseDto(blogRepository.save(blogMapper.toBlog(blogger, blogRequestDto)));
    }

    public BlogResponseDto updateBlog(Long blogId, BlogRequestDto blogRequestDto) {
        Blog existingBlog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog with id:" + blogId +"not found :-("));

        ParentUser user = parentUserRepository.findById(existingBlog.getBlogger().getId())
                .orElseThrow(() -> new RuntimeException("User with id:" + existingBlog.getBlogger().getId() +"not found :-("));

        if (!(user instanceof Blogger blogger)) {
            throw new IllegalArgumentException("User is not a blogger.");
        }

        existingBlog.setTitle(blogRequestDto.getTitle());
        existingBlog.setBlogTypes(blogRequestDto.getBlogTypes());
        existingBlog.setContent(blogRequestDto.getContent());
    
        return blogMapper.toBlogResponseDto(blogRepository.save(existingBlog));
    }

    public void deleteBlog(Long blogId) {
        Blog existingBlog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog with id:" + blogId +"not found :-("));

        List<Favorites> allFavorites = favoriteRepository.findAll();

        for (Favorites favorite : allFavorites) {
            if (favorite.getBlogCollection().remove(existingBlog))
                favoriteRepository.save(favorite);
        }

        favoriteRepository.saveAll(allFavorites);
        blogRepository.delete(existingBlog);
    }

    public void appreciateBlog(Long id, BlogAppreciationDto blogAppreciationDto) {
        Blog existingBlog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Blog with id:" + id +"not found :-("));

        switch (blogAppreciationDto.getOperation()) {
            case "like" ->  existingBlog.setLike(Math.max(0, existingBlog.getLike()) + 1);
            case "dislike" -> existingBlog.setDislike(Math.max(0, existingBlog.getDislike()) + 1);
            case "unlike" -> existingBlog.setLike(Math.max(0, existingBlog.getLike()) - 1);
            case "not_dislike" -> existingBlog.setDislike(Math.max(0, existingBlog.getDislike()) - 1);
        }

        blogRepository.save(existingBlog);
    }
}
