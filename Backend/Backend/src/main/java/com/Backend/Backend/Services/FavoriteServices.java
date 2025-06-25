package com.Backend.Backend.Services;

import com.Backend.Backend.Dtos.BlogResponseDto;
import com.Backend.Backend.Dtos.FavoriteDto;
import com.Backend.Backend.Entities.Blog;
import com.Backend.Backend.Entities.Blogger;
import com.Backend.Backend.Entities.Favorites;
import com.Backend.Backend.Entities.ParentUser;
import com.Backend.Backend.Mappers.BlogMapper;
import com.Backend.Backend.Repositories.BlogRepository;
import com.Backend.Backend.Repositories.FavoriteRepository;
import com.Backend.Backend.Repositories.ParentUserRepository;
import com.Backend.Backend.ServicesInterfaces.IFavoriteServices;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FavoriteServices implements IFavoriteServices {
    private final BlogRepository blogRepository;
    private final ParentUserRepository parentUserRepository;
    private final FavoriteRepository favoriteRepository;
    private final BlogMapper blogMapper;

    public void addBlogToFavorites(FavoriteDto favoriteDto) {
        ParentUser user = parentUserRepository.findById(favoriteDto.getBloggerId())
                .orElseThrow(() -> new RuntimeException("User with id:" + favoriteDto.getBloggerId() + "not found :-("));

        Blog blog = blogRepository.findById(favoriteDto.getBlogId())
                .orElseThrow(() -> new RuntimeException("Blog with id:" + favoriteDto.getBlogId() +"not found :-("));

        if (!(user instanceof Blogger blogger)) {
            throw new IllegalArgumentException("User is not a blogger.");
        } else {
            blogger.getFavorites().getBlogCollection().add(blog);
            parentUserRepository.save(blogger);
        }
    }

    public void removeBlogToFavorites(FavoriteDto favoriteDto) {
        ParentUser user = parentUserRepository.findById(favoriteDto.getBloggerId())
                .orElseThrow(() -> new RuntimeException("User with id:" + favoriteDto.getBloggerId() + "not found :-("));

        Blog blog = blogRepository.findById(favoriteDto.getBlogId())
                .orElseThrow(() -> new RuntimeException("Blog with id:" + favoriteDto.getBlogId() +"not found :-("));

        if (!(user instanceof Blogger blogger)) {
            throw new IllegalArgumentException("User is not a blogger.");
        } else {
            blogger.getFavorites().getBlogCollection().remove(blog);
            parentUserRepository.save(blogger);
        }
    }

    @Transactional
    public List<BlogResponseDto> getFavoriteBlogs(Long favoriteId) {
        Favorites favorites =  favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new RuntimeException("Favorite with ID " + favoriteId + "not found"));

        return favorites.getBlogCollection().stream()
                .map(blogMapper::toBlogResponseDto)
                .collect(Collectors.toList());
    }
}
