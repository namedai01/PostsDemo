package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.model.Report;
import com.example.demo.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostsController {

    // Auto bean the postsService
    @Autowired
    private PostsService postsService;

    // ------------------------------------------------------------------------------------------------------------------------------
    // The route is : http://localhost:8081/posts with method GET
    // This route return all the posts from my database
    @GetMapping("/posts")
    public List<Post> getPosts() {
        return postsService.findAll();
    }

    // ------------------------------------------------------------------------------------------------------------------------------
    // Example, the route is : http://localhost:8081/posts/100 with method GET
    // This route return the post with the id = 100
    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable(name = "id") Long id) {
        return postsService.getPost(id);
    }

    // ------------------------------------------------------------------------------------------------------------------------------
    // Example, the route is : http://localhost:8081/posts with method POST
    // This route help adding the specific post (in body of route) to the database,
    // if successfully it return "Add Post Successfully" else return "Add Post Failed".
    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) {
        return postsService.add(post);
    }

    // ------------------------------------------------------------------------------------------------------------------------------
    // Example, the route is : http://localhost:8081/100 with method DELETE
    // This route help to delete the specific post (in body of route) of the database,
    // if successfully it return "Delete Successfully" else throw the Exception with the code 1001, content: "Doi tuong khong ton tai"
    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable(name = "id") Long id) {
        postsService.delete(id);
        return "Delete Successfully";
    }

    // ------------------------------------------------------------------------------------------------------------------------------
    // Example, the route is : http://localhost:8081/100 with method PUT
    // This route help to update the specific post with the id variable
    // if successfully it return the updated post else throw the Exception with the code 1001, content: "Doi tuong khong ton tai"
    @PutMapping("/posts/{id}")
    public Post editPost(@PathVariable(name = "id") Long id, @RequestBody Post post) {
        return postsService.edit(id, post);
    }

    // ------------------------------------------------------------------------------------------------------------------------------
    // Example, the route is : http://localhost:8081/100/like with method PUT
    // This route help to increase the number like of specific post by 1
    // if successfully it return the updated post else throw the Exception with the code 1001, content: "Doi tuong khong ton tai"
    @PutMapping("/posts/{id}/like")
    public Post likePost(@PathVariable(name = "id") Long id) {
        return postsService.like(id);
    }

    // ------------------------------------------------------------------------------------------------------------------------------
    // Example, the route is : http://localhost:8081/100/like with method PUT
    // This route help to decrease the number like of specific post by 1. The number like is always > 0
    // if successfully it return the updated post else throw the Exception with the code 1001, content: "Doi tuong khong ton tai"
    @PutMapping("/posts/{id}/unlike")
    public Post unlikePost(@PathVariable(name = "id") Long id) {
        return postsService.unLike(id);
    }

    // ------------------------------------------------------------------------------------------------------------------------------
    // Example, the route is : http://localhost:8081/1/report with method POST
    // This route help to send a report to check whether post is spam or impolite
    // if successfully it return "OK" or "NOT OK" else throw the Exception with the code 1001, content: "Doi tuong khong ton tai"
    @PostMapping("/posts/{id}/report")
    public String sendReport(@PathVariable(name = "id") Long id, @RequestBody Report report) {
        return postsService.sendReport(id, report);
//        return postsService.sendReport();
    }
}
