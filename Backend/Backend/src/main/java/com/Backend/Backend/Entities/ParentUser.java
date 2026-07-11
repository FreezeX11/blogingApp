package com.Backend.Backend.Entities;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Role", discriminatorType = DiscriminatorType.STRING)
@Data
@Table(name = "ParentUser")
public class ParentUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Date creationDate;
    private String email;
    private String password;
    private byte[] profileImage;
    private boolean enabled = true;
}
