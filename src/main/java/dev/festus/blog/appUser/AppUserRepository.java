package dev.festus.blog.appUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
     Optional<AppUser> findByEmail(String email);
    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN TRUE" +
            "ELSE FALSE END " +
            "FROM blogPost b" +
            "WHERE b.title = ?1")
    boolean existsByEmail(String email);
}
