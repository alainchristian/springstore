package com.example.myapp.repository;

import com.example.myapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query("select r from Role r where r.rName=?1")
    public Role findRoleByName(String name);
}
