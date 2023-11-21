package com.codeup.codeupspringblog.repository;

import com.codeup.codeupspringblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Post getPostById(int id);
    Post deleteById(int id);


}
