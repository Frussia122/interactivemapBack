package com.example.apiWithDb.repository;

import com.example.apiWithDb.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findBylogin(String login);

    @Transactional
    @Modifying
    @Query("update User u set u.password = ?2 where u.login = ?1")
    void updatePassword(String email, String password);

}
