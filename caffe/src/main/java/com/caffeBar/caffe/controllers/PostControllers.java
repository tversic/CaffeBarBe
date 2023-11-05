package com.caffeBar.caffe.controllers;

import com.caffeBar.caffe.model.Post;
import com.caffeBar.caffe.repo.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/post/v1")
@CrossOrigin(origins = {"http://localhost", "http://localhost:8080"})
public class PostControllers {

    @Autowired
    PostRepository postRepo;

    @Transactional
    @GetMapping("/getAll")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok().body(postRepo.findAll());
    }

    @Transactional
    @PostMapping("/update")
    public ResponseEntity<String> updatePost(@RequestBody Map<String, String> requestBody) {
        Optional<Post> post = postRepo.findByPostId(Long.parseLong(requestBody.get("index")));
        if (post.isPresent()) {
            post.get().setPostContent(requestBody.get("content"));
            post.get().setPostTitle(requestBody.get("header"));
            postRepo.save(post.get());
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody Map<String, String> requestBody) {
        if (requestBody.get("header").isEmpty() || requestBody.get("content").isEmpty())
            return ResponseEntity.badRequest().build();
        postRepo.save(new Post(requestBody.get("header"), requestBody.get("content")));
        return ResponseEntity.ok().build();
    }

    @Transactional
    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePost(@RequestBody Map<String, String> requestBody) {
        if (requestBody.get("postId") != null && requestBody.get("postId").isEmpty())
            return ResponseEntity.badRequest().build();
        postRepo.deleteById(Long.parseLong(requestBody.get("postId")));
        return ResponseEntity.noContent().build();
    }
}
