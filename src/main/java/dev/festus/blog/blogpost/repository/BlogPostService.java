package dev.festus.blog.blogpost.repository;

import dev.festus.blog.blogpost.model.BlogPost;
import dev.festus.blog.blogpost.model.BlogPostTitle;
import dev.festus.blog.exception.customExceptions.ConflictRequestException;
import dev.festus.blog.exception.customExceptions.NotValidException;
import dev.festus.blog.exception.customExceptions.ResourceNotFoundException;

import java.util.List;

public interface BlogPostService {
    BlogPost createBlogPost(BlogPost blogPost) throws NotValidException, ConflictRequestException;
    List<BlogPostTitle> getAllBlogs();
    BlogPost getBlogById(int id) throws ResourceNotFoundException;
    BlogPost getBlogByTitle(String title) throws ResourceNotFoundException;
    void deleteBlogById(int id) throws ResourceNotFoundException;
}
