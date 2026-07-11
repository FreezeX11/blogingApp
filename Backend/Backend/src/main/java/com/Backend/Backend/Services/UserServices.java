package com.Backend.Backend.Services;

import com.Backend.Backend.Dtos.BloggerCreationDto;
import com.Backend.Backend.Dtos.UserRequestDto;
import com.Backend.Backend.Dtos.UserResponseDto;
import com.Backend.Backend.Entities.Blogger;
import com.Backend.Backend.Entities.Favorites;
import com.Backend.Backend.Entities.ParentUser;
import com.Backend.Backend.Mappers.UserMapper;
import com.Backend.Backend.Repositories.ParentUserRepository;
import com.Backend.Backend.ServicesInterfaces.IBloggerServices;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class UserServices implements IBloggerServices {
    private final ParentUserRepository parentUserRepository;
    private final UserMapper userMapper;

    public void bloggerCreation(BloggerCreationDto bloggerCreationDto) {
        String email = bloggerCreationDto.getEmail();
        String username = bloggerCreationDto.getUsername();

        if(!(parentUserRepository.findByEmail(email).isPresent() && parentUserRepository.findByUsername(username).isPresent())) {
            Blogger blogger = userMapper.toBlogger(bloggerCreationDto);
            blogger.setFavorites(new Favorites());
            blogger.setCreationDate(new Date());

            parentUserRepository.save(blogger);
            return;
        }
        throw new DuplicateKeyException("An account is already associate with this credential!!!");

    }

    public UserResponseDto updateBlogger(Long id, UserRequestDto userRequestDto) {
        ParentUser user = parentUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id:" + id + "not found :-("));
        if (user instanceof Blogger blogger) {
            blogger.setEmail(userRequestDto.getEmail());
            blogger.setUsername(userRequestDto.getUsername());
            blogger.setProfileImage(userRequestDto.getProfileImage());
            return userMapper.toUserResponseDto(parentUserRepository.save(blogger));
        }
        throw new RuntimeException("User with id:" + id + "is not a blogger :-(");
    }

    public void deleteAccount(Long id) {
        ParentUser user = parentUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id:" + id + "not found :-("));
        if (!(user instanceof Blogger blogger))
            throw new RuntimeException("User with id:" + id + "is not a blogger :-(");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (!(blogger.getUsername().equals(userDetails.getUsername()))) {
            throw new RuntimeException("It's not your account");
        }
        parentUserRepository.delete(blogger);
    }

    public UserResponseDto getBlogger(Long id) {
        ParentUser user = parentUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id:" + id + "not found :-("));
        if (!(user instanceof Blogger blogger))
            throw new RuntimeException("User with id:" + id + "is not a blogger :-(");

        return userMapper.toUserResponseDto(user);
    }

    public List<UserResponseDto> getBloggers() {
        return parentUserRepository.findAll().stream()
                .filter(user -> user instanceof Blogger)
                .map(user -> (Blogger) user)
                .map(userMapper::toUserResponseDto)
                .toList(); // can be optimized
    }

//    public void updateUserStatus(Long userId, String status) {
//
//    }

}
