package dev.festus.blog.blogpost.service;

import dev.festus.blog.blogpost.model.BlogPost;
import dev.festus.blog.blogpost.repository.BlogPostRepository;
import dev.festus.blog.blogpost.repository.BlogPostService;
import dev.festus.blog.blogpost.model.BlogPostTitle;
import dev.festus.blog.exception.customExceptions.ConflictRequestException;
import dev.festus.blog.exception.customExceptions.NotValidException;
import dev.festus.blog.exception.customExceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class BlogPostServiceImpl implements BlogPostService {
    private final BlogPostRepository blogPostDao;
    private final BlogPostMapperService mapperService;

    @Override
    public BlogPost createBlogPost(BlogPost blogPost) throws NotValidException, ConflictRequestException {
        //validations
        if (blogPost.getTitle().isBlank() || blogPost.getPost().isBlank()) {
            throw new NotValidException("Please add a title and a post");
        }
        //check if title already exists
        if (blogPostExistByTitle(blogPost.getTitle())) {
            throw new ConflictRequestException("A blog post already exists with this title");
        }
        blogPost.setReadTime(calculateReadTime(blogPost.getPost().length()));
        blogPostDao.save(blogPost);
        return blogPost;
    }

    @Override
    public List<BlogPostTitle> getAllBlogs() {
        return blogPostDao
                .findAll()
                .stream()
                .map(mapperService)
                .collect(Collectors.toList());
    }

    @Override
    public BlogPost getBlogById(int id) throws ResourceNotFoundException {
        return blogPostDao
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No blog exists with the id"));
    }

    @Override
    public BlogPost getBlogByTitle(String title) throws ResourceNotFoundException {
        return blogPostDao
                .findBlogsByTitle(title)
                .orElseThrow(()-> new ResourceNotFoundException("No blog exists with the title"));
    }
    @Override
    public void deleteBlogById(int id) throws ResourceNotFoundException {
        if (blogPostExists(id))
            throw new ResourceNotFoundException("Oops! could not delete because the id does not exist");
        blogPostDao.deleteById(id);
    }
    private boolean blogPostExists(int id){
        return blogPostDao.existsById(id);
    }
    private boolean blogPostExistByTitle(String title){
        return blogPostDao.existsByTitle(title);
    }
    private String calculateReadTime(int noOfLines){
         return String.valueOf(noOfLines / 10);
    }
}
