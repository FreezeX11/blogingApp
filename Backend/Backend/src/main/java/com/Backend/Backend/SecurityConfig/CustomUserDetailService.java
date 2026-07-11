package com.Backend.Backend.SecurityConfig;

import com.Backend.Backend.Entities.Blogger;
import com.Backend.Backend.Entities.ParentUser;
import com.Backend.Backend.Repositories.ParentUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final ParentUserRepository parentUserRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ParentUser user = parentUserRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User:" + username + "was not found"));

       UserDetails blogger = User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .disabled(!(user.isEnabled()))
                    .authorities("USER")
                    .build();

       UserDetails admin = User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .disabled(!(user.isEnabled()))
                    .authorities("USER", "ADMIN")
                    .build();

       return user instanceof Blogger ? blogger : admin;
    }
}
