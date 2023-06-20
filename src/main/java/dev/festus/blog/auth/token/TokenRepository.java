package dev.festus.blog.auth.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    @Query(value = """
            SELECT t from Token t INNER JOIN User u/s on t.user.id = u.id\s
            WHERE u.id = :id AND (t.expired = false or t.revoked = false)\s
            """)
    List<Token> findAllValidTokenByUserId(long id);
    Optional<Token> findToken(String token);
}
