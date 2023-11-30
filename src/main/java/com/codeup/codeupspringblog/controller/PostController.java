package com.codeup.codeupspringblog.controller;

import com.codeup.codeupspringblog.model.Post;
import com.codeup.codeupspringblog.model.User;
import com.codeup.codeupspringblog.repository.PostRepository;
import com.codeup.codeupspringblog.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {


    private final PostRepository postDao;
    private final UserRepository userDao;


    public PostController(PostRepository postDao, UserRepository userDao) {

        this.postDao = postDao;
        this.userDao = userDao;
    }




    @GetMapping("/posts")
    public String indexPage(Model model){



        ArrayList<Post> posts = (ArrayList<Post>) postDao.findAll();
        model.addAttribute("posts", posts);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable int id, Model model){

        Post post = postDao.getPostById(id);


       model.addAttribute("post", post);

        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String getCreate(Model model){
    model.addAttribute(new Post());


        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String postCreate(@ModelAttribute ("post") Post post){
        User loggedInUser = (User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = userDao.getUserById(loggedInUser.getId());


        post.setAuthor(user);

        postDao.save(post);


        return "redirect:/posts";
    }

    @PostMapping("/posts/delete")
    public String postDelete(@RequestParam int id, Model model){

        postDao.deleteById(id);

        return "redirect:/posts";
    }

    @GetMapping("/posts/update/{id}")
    public String getUpdate(@PathVariable int id, Model model){

        Post post = postDao.getPostById(id);

        model.addAttribute("post", post);

        return "posts/update";
    }

    @PostMapping("/posts/update")
    public String postUpdate(@ModelAttribute ("post") Post post){



        postDao.save(post);

        return "redirect:/posts";
    }
}
