package dev.festus.blog.blogpost.service;

import dev.festus.blog.blogpost.model.BlogPost;
import dev.festus.blog.blogpost.model.BlogPostTitle;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BlogPostMapperService implements Function<BlogPost, BlogPostTitle> {
    @Override
    public BlogPostTitle apply(BlogPost blogPost) {
        return new BlogPostTitle(
                blogPost.getId(),
                blogPost.getTitle(),
                blogPost.getDate()
        );
    }
}
