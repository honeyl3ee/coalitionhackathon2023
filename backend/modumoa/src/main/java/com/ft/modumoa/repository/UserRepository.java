package com.ft.modumoa.repository;


import com.ft.modumoa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByIntraId(String intraId);

}
