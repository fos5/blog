package dev.festus.blog.blogpost.repository;

import dev.festus.blog.blogpost.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
    Optional<BlogPost> findBlogsByTitle(String title);
    boolean existsByTitle(String title);
}
