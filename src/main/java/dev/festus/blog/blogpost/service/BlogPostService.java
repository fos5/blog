package dev.festus.blog.blogpost.service;

import dev.festus.blog.blogpost.model.BlogPost;
import dev.festus.blog.blogpost.model.BlogPostRequest;
import dev.festus.blog.blogpost.model.BlogPostResponse;
import dev.festus.blog.blogpost.repository.BlogContracts;
import dev.festus.blog.blogpost.repository.BlogRepository;
import dev.festus.blog.exception.customExceptions.NotValidException;
import dev.festus.blog.exception.customExceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogPostService implements BlogContracts {

    private final BlogRepository blogRepository;
    private final BlogPostMapper mapperService;
    @Override
    public BlogPostResponse addNewBlog(BlogPostRequest request) {
        if (request.title() == null || request.title().isBlank()){
            throw new NotValidException("Please Enter a title for the blog");
        }
        BlogPost newBlog = BlogPost.builder()
                .title(request.title())
                .blogType(request.blogType())
                .post(request.post())
                .timeStamp(LocalDateTime.now())
                .readingDuration(calculateReadTime(request.post().length()))
                .build();
        blogRepository.save(newBlog);
       return mapperService.apply(newBlog);
    }

    @Override
    public BlogPost getBlogById(long id)  {
        return blogRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No blog exists with the id"));
    }

    @Override
    public List<BlogPostResponse> getAllBlogs() {
        return blogRepository
                .findAll()
                .stream()
                .map(mapperService)
                .collect(Collectors.toList());
    }

    @Override
    public List<BlogPostResponse> getBlogByTitle(String title) {
        return blogRepository
                .findBlogByTitle(title)
                .stream()
                .map(mapperService)
                .toList();
    }

    @Override
    public List<BlogPostResponse> getByDate(String date) {
        return null;
    }

    @Override
    public List<BlogPostResponse> getByBlogType(String blogType) {
        return blogRepository
                .findBlogByBlogType(blogType)
                .stream()
                .map(mapperService)
                .toList();
    }

    @Override
    public void deleteBlogById(long id)  {
        if (blogPostExists(id))
            throw new ResourceNotFoundException("Oops! could not delete because the id does not exist");
        blogRepository.deleteById(id);
    }
    @Override
    public BlogPostResponse updateBlog(long id) {
        //Todo:
        return null;
    }

    private boolean blogPostExists(long id){
        return blogRepository.existsById(id);
    }

    private String calculateReadTime(int noOfLines){
        return String.valueOf(noOfLines / 10);
    }
}
