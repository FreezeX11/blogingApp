package com.Backend.Backend.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@DiscriminatorValue("ADMIN")
@Data @AllArgsConstructor
@Table(name = "Admin")
public class Admin extends ParentUser { }
