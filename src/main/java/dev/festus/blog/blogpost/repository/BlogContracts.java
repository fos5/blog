package dev.festus.blog.blogpost.repository;

import dev.festus.blog.blogpost.model.BlogPostRequest;
import dev.festus.blog.blogpost.model.BlogPost;
import dev.festus.blog.blogpost.model.BlogPostResponse;
import dev.festus.blog.exception.customExceptions.NotValidException;
import dev.festus.blog.exception.customExceptions.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BlogContracts {
    BlogPostResponse addNewBlog(BlogPostRequest request) ;
    BlogPost getBlogById(long id) ;
    List<BlogPostResponse> getAllBlogs();
    List<BlogPostResponse> getBlogByTitle(String title);
    List<BlogPostResponse> getByDate(String date);
    List<BlogPostResponse> getByBlogType(String blogType);
    void deleteBlogById(long id);
    BlogPostResponse updateBlog(long id);


}
