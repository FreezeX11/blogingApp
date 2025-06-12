package com.Backend.Backend.Services;

import com.Backend.Backend.Dtos.FavoriteDto;
import com.Backend.Backend.Entities.Blog;
import com.Backend.Backend.Entities.Blogger;
import com.Backend.Backend.Entities.ParentUser;
import com.Backend.Backend.Repositories.BlogRepository;
import com.Backend.Backend.Repositories.FavoriteRepository;
import com.Backend.Backend.Repositories.ParentUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoriteServices {
    private final FavoriteRepository favoriteRepository;
    private final BlogRepository blogRepository;
    private final ParentUserRepository parentUserRepository;

    public void addToFavorite(FavoriteDto favoriteDto) {
        ParentUser user = parentUserRepository.findById(favoriteDto.getBloggerId())
                .orElseThrow(() -> new RuntimeException("User with id:" + favoriteDto.getId() + "not found :-("));

        Blog blog = blogRepository.findById(favoriteDto.getBlogId())
                .orElseThrow(() -> new RuntimeException("Blog with id:" + favoriteDto.getBlogId() +"not found :-("));

        if (user instanceof Blogger blogger) {
            blogger.getFavorites().getBlogCollection().add(blog);
        }
    }
}
