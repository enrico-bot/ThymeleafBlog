package ch.supsi.webapp.web;

import ch.supsi.webapp.web.model.BlogPost;
import ch.supsi.webapp.web.model.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogPostController {

    @Autowired
    BlogPostService blogPostService;

    @RequestMapping(value = "/blogpost", method = RequestMethod.GET)
    public List<BlogPost> get() {
        return blogPostService.getAllBlogPosts();
    }

    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.GET)
    public BlogPost getPerson(@PathVariable long id) {
        return blogPostService.get(id);
    }

    @RequestMapping(value = "/blogpost", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> post(@RequestBody BlogPost blogPost) {
        return blogPostService.add(blogPost);
    }

    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RequestStatus> deletePost(@PathVariable long id) {
        return blogPostService.delete(id);
    }

    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RequestStatus> putPost(@PathVariable long id, @RequestBody BlogPost blogPost) {
        return blogPostService.update(id, blogPost);
    }
}