package com.Backend.Backend.Repositories;

import com.Backend.Backend.Entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
