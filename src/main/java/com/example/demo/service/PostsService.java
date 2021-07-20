package com.example.demo.service;

import com.example.demo.ReportType;
import com.example.demo.dto.request.CreatePost;
import com.example.demo.dto.request.CreateReport;
import com.example.demo.dto.request.UpdatePost;
import com.example.demo.dto.response.AppProperties;
import com.example.demo.entity.Post;
import com.example.demo.entity.Report;
import com.example.demo.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.Math.max;

@Service
@RequiredArgsConstructor
public class PostsService {
    // Auto inject the postsRepository
    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private AppProperties appProperties;

    // ------------------------------------------------------------------------------------------------------------------------------
    // This function return all the posts from my database
    public List<Post> findAll() {
        return postsRepository.findAll();
    }

    // ------------------------------------------------------------------------------------------------------------------------------
    // This function return the post with the given id
    public Post getPost(Long id) {
        Optional<Post> postSpec = postsRepository.findById(id);

        if (postSpec.isEmpty()) throw new EmptyResultDataAccessException(-10);

        return postSpec.get();
    }

    // ------------------------------------------------------------------------------------------------------------------------------
    // This function help adding the specific post (in body of route) to the database,
    // if successfully it return that Object else return throw a Exception with code 1002, message "Post khong dung dinh dang".
    public Post add(CreatePost createPost) {
        if (createPost.getTitle() == null || createPost.getDescription() == null)
            throw new InvalidDataAccessApiUsageException("Failed");

        Post entityPost = new Post();
        entityPost.setTitle(createPost.getTitle());
        entityPost.setDescription(createPost.getDescription());

        return postsRepository.save(entityPost);
    }

    // ------------------------------------------------------------------------------------------------------------------------------
    // This function help to delete the specific post (in body of route) of the database,
    // if successfully it return "Delete Successfully" else throw the Exception with the code 1001, content: "Doi tuong khong ton tai"
    public void delete(Long id) {
        postsRepository.deleteById(id);
    }

    // This function help to update the specific post with the id variable
    // if successfully it return the updated post else throw the Exception with the code 1001, content: "Doi tuong khong ton tai"
    public Post edit(Long id, UpdatePost updatePost) {
        Optional<Post> postSpec = postsRepository.findById(id);

        if (postSpec.isEmpty()) throw new EmptyResultDataAccessException(-10);

        Post post = postSpec.get();
        post.setTitle(updatePost.getTitle());
        post.setDescription(updatePost.getDescription());

        return postsRepository.save(post);
    }

    // ------------------------------------------------------------------------------------------------------------------------------
    // This function help to increase the number like of specific post by 1
    // if successfully it return the updated post else throw the Exception with the code 1001, content: "Doi tuong khong ton tai"
    public Post like(Long id) {
        Optional<Post> postSpec = postsRepository.findById(id);

        if (postSpec.isEmpty()) throw new EmptyResultDataAccessException(-10);

        Post post = postSpec.get();
        post.setLikeCount(post.getLikeCount() + 1);
        return postsRepository.save(post);

    }

    // ------------------------------------------------------------------------------------------------------------------------------
    // This function help to decrease the number like of specific post by 1. The number like is always >= 0
    // if successfully it return the updated post else throw the Exception with the code 1001, content: "Doi tuong khong ton tai"
    public Post unLike(Long id) {
        Optional<Post> postSpec = postsRepository.findById(id);

        if (postSpec.isEmpty()) throw new EmptyResultDataAccessException(-10);

        Post post = postSpec.get();
        post.setLikeCount(max(0, post.getLikeCount() - 1));
        return postsRepository.save(post);
    }

    // ------------------------------------------------------------------------------------------------------------------------------
    // Function to check report Spam
    public String spam(String[] arr) {
        Map<String, Integer> mp = new HashMap<>();
        int count = 0;

        // Count the frequency of each word
        for (int i = 0; i <= arr.length - 1; i++) {
            if (mp.containsKey(arr[i])) {
                mp.put(arr[i], mp.get(arr[i]) + 1);
            } else {
                mp.put(arr[i], 1);
            }
        }

        // Count the number of word which is duplicated
        for (Map.Entry<String, Integer> entry : mp.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value != 1) count++;
        }

        if (count >= 2) return "NOT OK";

        return "OK";
    }

    // Function to check report Impolite
    public String impolite(String[] arr) {
        HashSet<String> keywords = appProperties.getKeywords();

        // Check the keyword from application.properties whether there are impolite word
        for (int i = 0; i <= arr.length - 1; i++) {
            if (keywords.contains(arr[i])) return "NOT OK";
        }

        return "OK";
    }

    // This function help to send report
    // if successfully it return OK / NOT OK else throw the Exception with the code 1001, content: "Doi tuong khong ton tai"
    public String sendReport(Long id, CreateReport createReport) {
        // Validation
        if (id == null || id >= 10000000000L) throw new InvalidDataAccessApiUsageException("Failed");

        if (createReport.getType() == null
                || (!createReport.getType().equals(ReportType.SPAM.getValue())) && (!createReport.getType().equals(ReportType.IMPOLITE.getValue()))
        ) throw new InvalidDataAccessApiUsageException("Failed");

        if (createReport.getNote() != null
                && createReport.getNote().length() >= 201
        ) throw new InvalidDataAccessApiUsageException("Failed");

        Optional<Post> postSpec = postsRepository.findById(id);
        if (postSpec.isEmpty()) throw new InvalidDataAccessApiUsageException("Failed");
        // End Validation

        String description = postSpec.get().getDescription();
        String[] arr = description.split("[ .,-;*]+");
        // String description = "  Nguyễn. Đức Quốc,     Đại   Đại Đại   Đại     Đức  ";
        if (createReport.getType().equals(ReportType.SPAM.getValue())) {
            return spam(arr);
        } else if (createReport.getType().equals(ReportType.IMPOLITE.getValue())) {
            return impolite(arr);
        } else throw new InvalidDataAccessApiUsageException("Failed");
    }
}
