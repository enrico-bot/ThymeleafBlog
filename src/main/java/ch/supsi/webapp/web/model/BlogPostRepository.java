package ch.supsi.webapp.web.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    boolean existsById(long id);

    BlogPost findTop1ById(long id);

    BlogPost findFirstByOrderByIdDesc();
}
