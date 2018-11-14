package ch.supsi.webapp.web.model;

import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepository extends CrudRepository<BlogPost, Integer> {
    BlogPost findTop1ById(int id);
}
