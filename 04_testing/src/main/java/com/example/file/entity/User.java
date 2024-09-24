package com.example.file.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(unique = true)
    private String username;
    @Setter
    private String password;
    // 프로필 이미지를 저장하고 싶다면?
    // <img src="">
    @Setter
    private String profileImgUrl;
}
