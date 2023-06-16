package dev.festus.blog.blogpost.repository;

import dev.festus.blog.blogpost.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
    Optional<BlogPost> findBlogsByTitle(String title);
    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN TRUE" +
            "ELSE FALSE END " +
            "FROM blogPost b" +
            "WHERE b.title = ?1")
    boolean existsByTitle(String title);
}
