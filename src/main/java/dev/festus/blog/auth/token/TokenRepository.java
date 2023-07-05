package dev.festus.blog.auth.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    @Query(value = """
            SELECT token  FROM Token
            INNER JOIN app_users u ON token.user_id = u.id
            WHERE u.id = :id AND (token.expired = 0 OR token.revoked = 0);
            """)
    List<Token> findAllValidTokenByUserId(@Param("id") long id);
    Optional<Token> findByToken(String token);
}
