package dev.festus.blog.blogpost.repository;

import dev.festus.blog.blogpost.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<BlogPost,Long> {
    Optional<BlogPost> findBlogByTitle(String title);
    Optional<BlogPost> findBlogByBlogType(String blogType);
//    Optional<List<BlogPost>> findBlogByUserEmail(String email);
}
