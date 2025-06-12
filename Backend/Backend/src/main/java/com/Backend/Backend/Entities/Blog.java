package com.Backend.Backend.Entities;

import com.Backend.Backend.Enumerations.BlogType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    private Blogger blogger;

    private String content;

    @CreationTimestamp
    private Date creationDate;

    @ElementCollection(targetClass = BlogType.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    Collection<BlogType> blogTypes;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Comment> comments;

    private Long like = 0L;

}
