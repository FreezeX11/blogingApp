package com.Backend.Backend.Repositories;

import com.Backend.Backend.Entities.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorites, Long> {
}
