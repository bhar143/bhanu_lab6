package com.grl.springMVC.College.repository;

import com.grl.springMVC.College.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    //    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User getUserByUsername(String username);
}
