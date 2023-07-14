package dev.festus.blog.blogpost.controller;

import dev.festus.blog.blogpost.model.BlogPost;
import dev.festus.blog.blogpost.model.BlogPostTitle;
import dev.festus.blog.blogpost.service.BlogPostServiceImpl;
import dev.festus.blog.exception.customExceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/blog")
@RequiredArgsConstructor
public class BlogPostController {
    private final BlogPostServiceImpl service;

    @GetMapping
    public List<BlogPostTitle> getAllPosts(){
        return service.getAllBlogs();
    }
    @GetMapping("/{id}")
    public BlogPost getBlogPostById(@PathVariable int id) throws ResourceNotFoundException {
       return service.getBlogById(id);
    }
}
