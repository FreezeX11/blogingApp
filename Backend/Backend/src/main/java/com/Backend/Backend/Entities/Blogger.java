package com.Backend.Backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@DiscriminatorValue("BLOGGER")
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "Blogger")
public class Blogger extends ParentUser {

    @OneToMany(
            mappedBy = "blogger",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
    private Collection<Blog> blogs = new ArrayList<>();

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "favorite_id")
    private Favorites favorites;

    @OneToMany(
            mappedBy = "blogger",
            cascade = CascadeType.ALL
    )
    private Collection<Comment> comments = new ArrayList<>();
}
