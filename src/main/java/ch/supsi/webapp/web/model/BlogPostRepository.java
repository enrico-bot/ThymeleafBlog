package ch.supsi.webapp.web.model;

import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepository extends CrudRepository<BlogPost, Integer> {
    boolean existsById(long id);
    BlogPost findTop1ById(long id);
    BlogPost findFirstByOrderByIdDesc();
}
