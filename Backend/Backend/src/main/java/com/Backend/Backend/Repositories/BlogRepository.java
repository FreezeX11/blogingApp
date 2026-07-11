package com.Backend.Backend.Repositories;

import com.Backend.Backend.Dtos.BlogResponseDto;
import com.Backend.Backend.Entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByBloggerId(Long bloggerId);
}
