package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import javax.management.InvalidApplicationException;
import javax.management.InvalidAttributeValueException;
import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.max;

@Service
@RequiredArgsConstructor
public class PostsService {

    // Auto inject the postsRepository
    @Autowired
    private PostsRepository postsRepository;

    // This function return all the posts from my database
    public List<Post> findAll() {
        return postsRepository.findAll();
    }

    // This function return the post with the given id
    public Post getPost(Long id) {
        Optional<Post> postSpec = postsRepository.findById(id);

        if (postSpec.isEmpty()) throw new EmptyResultDataAccessException(-10);

        return postSpec.get();
    }

    // This function help adding the specific post (in body of route) to the database,
    // if successfully it return that Object else return throw a Exception with code 1002, message "Post khong dung dinh dang".
    public Post add(Post post) {
        if (post.getTitle() == null || post.getDescription() == null) throw new InvalidDataAccessApiUsageException("Failed");
        if (post.getLikeCount() != 0) throw new InvalidDataAccessApiUsageException("Failed");
        return postsRepository.save(post);
    }

    // This function help to delete the specific post (in body of route) of the database,
    // if successfully it return "Delete Successfully" else throw the Exception with the code 1001, content: "Doi tuong khong ton tai"
    public void delete(Long id) {
        postsRepository.deleteById(id);
    }

    // This function help to update the specific post with the id variable
    // if successfully it return the updated post else throw the Exception with the code 1001, content: "Doi tuong khong ton tai"
    public Post edit(Long id, Post post) {
        Optional<Post> postSpec = postsRepository.findById(id);

        if (postSpec.isEmpty()) throw new EmptyResultDataAccessException(-10);

        post.setId(postSpec.get().getId());
        return postsRepository.save(post);
    }

    // This function help to increase the number like of specific post by 1
    // if successfully it return the updated post else throw the Exception with the code 1001, content: "Doi tuong khong ton tai"
    public Post like(Long id) {
        Optional<Post> postSpec = postsRepository.findById(id);

        if (postSpec.isEmpty()) throw new EmptyResultDataAccessException(-10);

        Post post = postSpec.get();
        post.setLikeCount(post.getLikeCount() + 1);
        return postsRepository.save(post);

    }

    // This function help to decrease the number like of specific post by 1. The number like is always >= 0
    // if successfully it return the updated post else throw the Exception with the code 1001, content: "Doi tuong khong ton tai"
    public Post unLike(Long id) {
        Optional<Post> postSpec = postsRepository.findById(id);

        if (postSpec.isEmpty()) throw new EmptyResultDataAccessException(-10);

        Post post = postSpec.get();
        post.setLikeCount(max(0, post.getLikeCount() - 1));
        return postsRepository.save(post);
    }
}
