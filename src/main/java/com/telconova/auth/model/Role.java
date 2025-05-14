package com.telconova.auth.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "\"role\"", schema = "public")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Column(length = 255)
    private String description;

    public Role() {
    }
    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}