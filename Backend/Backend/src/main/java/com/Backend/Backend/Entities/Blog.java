package com.Backend.Backend.Entities;

import com.Backend.Backend.Enumerations.BlogType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "Blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "blogger_id")
    private Blogger blogger;

    private String content;

    private String title;

    @CreationTimestamp
    private Date creationDate;

    @ElementCollection(targetClass = BlogType.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    Collection<BlogType> blogTypes;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "blog_id")
    private Collection<Comment> comments = new ArrayList<>();

    private Long like = 0L;
    private Long dislike = 0L;

}
