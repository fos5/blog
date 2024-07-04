package dev.festus.blog.blogpost;

import dev.festus.blog.blogpost.model.BaseResponse;
import dev.festus.blog.blogpost.model.BlogPost;
import dev.festus.blog.blogpost.model.BlogPostRequest;
import dev.festus.blog.blogpost.model.BlogPostResponse;
import dev.festus.blog.blogpost.service.BlogPostService;
import dev.festus.blog.exception.customExceptions.NotValidException;
import dev.festus.blog.exception.customExceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/blog")
public class BlogPostController {
    private final BlogPostService service;

    public BlogPostController(BlogPostService service) {
        this.service = service;
    }

    @GetMapping
    public List<BlogPostResponse> getAllPosts(){
        return service.getAllBlogs();
    }
    @GetMapping("/{id}")
    public BlogPost getBlogPostById(@PathVariable long id) throws ResourceNotFoundException {
       return service.getBlogById(id);
    }
    @PostMapping
    public ResponseEntity<Void> addNewPost(@RequestBody BlogPostRequest request, UriComponentsBuilder uriComponentsBuilder) throws NotValidException {
        BlogPost blogPost = service.addNewBlog(request);
        URI locationOfNewBlogPost = uriComponentsBuilder.path("blog/{id}")
                .buildAndExpand(blogPost.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewBlogPost).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable long id) throws ResourceNotFoundException {
         service.deleteBlogById(id);
         return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}