package com.Backend.Backend.Mappers;

import com.Backend.Backend.Dtos.AdminDto;
import com.Backend.Backend.Dtos.BloggerCreationDto;
import com.Backend.Backend.Dtos.BloggerRequestDto;
import com.Backend.Backend.Dtos.BloggerResponseDto;
import com.Backend.Backend.Entities.Admin;
import com.Backend.Backend.Entities.Blogger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BloggerMapper {

    public BloggerResponseDto toBloggerResponseDto(Blogger blogger) {
        BloggerResponseDto bloggerResponseDto = new BloggerResponseDto();

        bloggerResponseDto.setId(blogger.getId());
        bloggerResponseDto.setUsername(blogger.getUsername());
        bloggerResponseDto.setEmail(blogger.getEmail());
        bloggerResponseDto.setProfileImage(blogger.getProfileImage());

        return bloggerResponseDto;
    }

    public Blogger toBlogger(BloggerResponseDto bloggerResponseDto) {
        Blogger blogger = new Blogger();

        blogger.setUsername(bloggerResponseDto.getUsername());
        blogger.setEmail(bloggerResponseDto.getEmail());
        blogger.setProfileImage(bloggerResponseDto.getProfileImage());

        return blogger;
    }

    public Blogger toBlogger(BloggerRequestDto bloggerRequestDto) {
        Blogger blogger = new Blogger();

        blogger.setUsername(bloggerRequestDto.getUsername());
        blogger.setEmail(bloggerRequestDto.getEmail());
        blogger.setProfileImage(bloggerRequestDto.getProfileImage());

        return blogger;
    }

    public Blogger toBlogger(BloggerCreationDto bloggerCreationDto) {
        Blogger blogger = new Blogger();

        blogger.setUsername(bloggerCreationDto.getUsername());
        blogger.setEmail(bloggerCreationDto.getEmail());
        blogger.setPassword(bloggerCreationDto.getPassword());
        blogger.setProfileImage(bloggerCreationDto.getProfileImage());

        return blogger;
    }

    public AdminDto toAdminDto(Admin admin) {
        AdminDto adminDto = new AdminDto();

        adminDto.setId(admin.getId());
        adminDto.setUsername(admin.getUsername());
        adminDto.setEmail(admin.getEmail());
        adminDto.setProfileImage(admin.getProfileImage());

        return adminDto;
    }

}
