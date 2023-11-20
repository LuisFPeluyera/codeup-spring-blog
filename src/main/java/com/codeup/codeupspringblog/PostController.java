package com.codeup.codeupspringblog;

import com.codeup.codeupspringblog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PostController {


    private final PostRepository postDao;

    public PostController(PostRepository postDao) {

        this.postDao = postDao;
    }



    @GetMapping("/posts")
    public String indexPage(Model model){
        ArrayList<Post> arrayList = new ArrayList<>();


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
    public String getCreate(){




        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String postCreate(String title, String body, Model model){

        Post newPost = new Post(title, body);
        postDao.save(newPost);


        return "redirect:/posts";
    }

    @PostMapping("/posts/delete")
    public String postDelete(@RequestParam int id, Model model){

        postDao.deleteById(id);


        return "redirect:/posts";
    }


}
