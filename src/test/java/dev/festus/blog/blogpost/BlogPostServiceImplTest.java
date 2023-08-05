//package dev.festus.blog.blogpost;
//
//import dev.festus.blog.blogpost.model.BlogPost;
//import dev.festus.blog.blogpost.model.BlogPostTitle;
//import dev.festus.blog.blogpost.repository.BlogPostRepository;
//import dev.festus.blog.blogpost.service.BlogPostMapperService;
//import dev.festus.blog.blogpost.service.BlogPostServiceImpl;
//import dev.festus.blog.exception.customExceptions.ResourceNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class BlogPostServiceImplTest {
//    @Mock
//    BlogPostRepository blogPostRepository;
//    @Mock
//    BlogPostMapperService mapperService;
//    @InjectMocks
//    BlogPostServiceImpl blogPostService;
//    @BeforeEach
//    void setUp() {
//        blogPostService = new BlogPostServiceImpl(blogPostRepository, mapperService);
//    }
//
//    @Test
//    void createBlogPost() {
//    }
//
//    @Test
//    void getAllBlogs() {
//        BlogPost post = new BlogPost();
//        BlogPost post1 = new BlogPost();
//        List<BlogPost> posts = List.of(post,post1);
//        when(blogPostRepository.findAll()).thenReturn(posts);
//        List<BlogPostTitle> allBlogs = blogPostService.getAllBlogs();
//        assertNotNull(allBlogs);
//    }
//
//    @Test
//    void getBlogById() throws ResourceNotFoundException {
//        int id = 23;
//        BlogPost blogPost = new BlogPost();
//        blogPost.setId(id);
//        when(blogPostRepository.findById(id)).thenReturn(Optional.of(blogPost));
//        //actual
//        BlogPost blogById = blogPostService.getBlogById(id);
//        assertEquals(blogById.getId(),23);
//        assertEquals(blogById.getPost(), blogPost.getPost());
//    }
//    @Test
//    void shouldThrowExceptionWhenBlogNotFound(){
//
//    }
//
//    @Test
//    void getBlogByTitle() throws ResourceNotFoundException {
//        String title = "hello";
//        BlogPost blogPost = new BlogPost();
//        blogPost.setTitle(title);
//        when(blogPostRepository.findBlogsByTitle(title)).thenReturn(Optional.of(blogPost));
//        //actual
//        BlogPost blogById = blogPostService.getBlogByTitle(title);
//        assertEquals(blogById.getTitle(),"hello");
//        assertEquals(blogById.getPost(), blogPost.getPost());
//    }
//}