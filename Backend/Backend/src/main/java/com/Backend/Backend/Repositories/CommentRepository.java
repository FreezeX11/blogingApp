package com.Backend.Backend.Repositories;

import com.Backend.Backend.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
