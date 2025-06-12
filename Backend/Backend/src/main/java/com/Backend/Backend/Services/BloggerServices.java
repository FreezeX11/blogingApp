package com.Backend.Backend.Services;

import com.Backend.Backend.Dtos.BloggerCreationDto;
import com.Backend.Backend.Dtos.BloggerRequestDto;
import com.Backend.Backend.Dtos.BloggerResponseDto;
import com.Backend.Backend.Entities.Blogger;
import com.Backend.Backend.Entities.Favorites;
import com.Backend.Backend.Entities.ParentUser;
import com.Backend.Backend.Mappers.BloggerMapper;
import com.Backend.Backend.Repositories.ParentUserRepository;
import com.Backend.Backend.ServicesInterfaces.IBloggerServices;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class BloggerServices implements IBloggerServices {
    private final ParentUserRepository parentUserRepository;
    private final BloggerMapper bloggerMapper;

    public void bloggerCreation(BloggerCreationDto bloggerCreationDto) {
        String email = bloggerCreationDto.getEmail();
        String username = bloggerCreationDto.getUsername();

        if(!(parentUserRepository.findByEmail(email).isPresent() && parentUserRepository.findByUsername(username).isPresent())) {
            Blogger blogger = bloggerMapper.toBlogger(bloggerCreationDto);
            blogger.setComments(new ArrayList<>());
            blogger.setBlogs(new ArrayList<>());
            blogger.setFavorites(new Favorites());

            parentUserRepository.save(blogger);
        }
        throw new DuplicateKeyException("An account is already associate with this credential!!!");

    }

    public BloggerResponseDto updateBlogger(BloggerRequestDto bloggerRequestDto) {
        ParentUser user = parentUserRepository.findById(bloggerRequestDto.getId())
                .orElseThrow(() -> new RuntimeException("User with id:" + bloggerRequestDto.getId() + "not found :-("));
        if (user instanceof Blogger blogger) {
            blogger.setEmail(bloggerRequestDto.getEmail());
            blogger.setUsername(bloggerRequestDto.getUsername());
            blogger.setProfileImage(bloggerRequestDto.getProfileImage());
            return bloggerMapper.toBloggerResponseDto(parentUserRepository.save(blogger));
        }
        throw new RuntimeException("User with id:" + bloggerRequestDto.getId() + "is not a blogger :-(");
    }

    public void deleteBlogger(Long bloggerId) {
        ParentUser user = parentUserRepository.findById(bloggerId)
                .orElseThrow(() -> new RuntimeException("User with id:" + bloggerId + "not found :-("));
        if (!(user instanceof Blogger blogger))
            throw new RuntimeException("User with id:" + bloggerId + "is not a blogger :-(");

        parentUserRepository.delete(blogger);
    }

}
