package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {"id", "role", "createTime"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private int role;

    @Column(name = "create_time", nullable = false)
    private long createTime;

    public User(Long id, String username, String password, int role, long createTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.createTime = createTime;
    }
}