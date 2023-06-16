package dev.festus.blog.blogpost.controller;

import dev.festus.blog.blogpost.model.BlogPost;
import dev.festus.blog.blogpost.service.BlogPostServiceImpl;
import dev.festus.blog.exception.customExceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/blog")
@RequiredArgsConstructor
public class BlogPostController {
    private final BlogPostServiceImpl service;

    @GetMapping("/{id}")
    public BlogPost getBlogPostById(@RequestParam int id) throws ResourceNotFoundException {
       return service.getBlogById(id);
    }
}
