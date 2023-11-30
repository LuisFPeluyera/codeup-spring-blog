package com.codeup.codeupspringblog.repository;

import com.codeup.codeupspringblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserById(int id);
    User findByUsername(String username);

}
