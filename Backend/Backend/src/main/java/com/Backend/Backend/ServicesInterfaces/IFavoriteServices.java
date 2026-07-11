package com.Backend.Backend.ServicesInterfaces;


import com.Backend.Backend.Dtos.FavoriteDto;

public interface IFavoriteServices {
    void addBlogToFavorites(FavoriteDto favoriteDto);
    void removeBlogToFavorites(FavoriteDto favoriteDto);
}
