package com.example.myapp.repository;

import com.example.myapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("Select u from User u where u.email=?1")
    public User getUserByEmail(String email);
}
