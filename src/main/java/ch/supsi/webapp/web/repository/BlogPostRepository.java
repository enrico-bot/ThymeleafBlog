package ch.supsi.webapp.web.repository;

import ch.supsi.webapp.web.model.entity.BlogPost;
import ch.supsi.webapp.web.model.entity.Category;
import ch.supsi.webapp.web.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    boolean existsById(long id);

    List<BlogPost> findAllByUserAndTitleContainingOrderByTitle(User user, String title);

    @Query("SELECT p FROM BlogPost p WHERE p.title LIKE %:title%")
    List<BlogPost>findAllContainingTitle(@Param("title") String title);

    BlogPost findTop1ById(long id);

    List<BlogPost> findByCategory(Category category);
    BlogPost findFirstByOrderByIdDesc();

}
/*
// create database bottani CHARACTER SET=latin1

Trovare tutti i blogbost che hanno un determinato utente e il loro titolo contiene Title, ordinati per Title
findAllByUserAndTitleContainingOrderByTitle(User user, String title);

Using Like: select ... like :username
List<User> findByUsernameLike(String username);
StartingWith: select ... like :username%
List<User> findByUsernameStartingWith(String username);
EndingWith: select ... like %:username
List<User> findByUsernameEndingWith(String username);
Containing: select ... like %:username%
List<User> findByUsernameContaining(String username);

dove p sono tutti i blogPost che trova
@Query("SELECT p FROM BlogPost p WHERE p.title LIKE %:title%");
*/