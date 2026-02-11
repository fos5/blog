package dev.festus.blog.blogpost;

import dev.festus.blog.blogpost.model.BlogPost;
import dev.festus.blog.blogpost.model.BlogPostRequest;
import dev.festus.blog.blogpost.model.BlogPostResponse;
import dev.festus.blog.blogpost.service.BlogPostService;
import dev.festus.blog.exception.customExceptions.NotValidException;
import dev.festus.blog.exception.customExceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/blog")
@RequiredArgsConstructor
public class BlogPostController {
    private final BlogPostService service;

    @GetMapping
    public List<BlogPostResponse> getAllPosts(){
        return service.getAllBlogs();
    }
    @GetMapping("/{id}")
    public BlogPost getBlogPostById(@PathVariable long id) {
       return service.getBlogById(id);
    }
    @PostMapping
    public BlogPostResponse addNewPost(@RequestBody BlogPostRequest request) {
        return service.addNewBlog(request);
    }
}