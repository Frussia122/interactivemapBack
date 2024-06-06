package com.example.apiWithDb.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "app_user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = true, name = "email")
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;
    private String surName;

    @OneToOne(mappedBy = "user")
    private ForgotPassword forgotPassword;
}
