package dev.festus.blog.blogpost.service;

import dev.festus.blog.blogpost.model.BlogPost;
import dev.festus.blog.blogpost.model.BlogPostResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class BlogPostMapper implements Function<BlogPost, BlogPostResponse> {
    @Override
    public BlogPostResponse apply(BlogPost blogPost) {
        return new BlogPostResponse(
                blogPost.getId(),
                blogPost.getTitle(),
                blogPost.getBlogType()
        );
    }
}
