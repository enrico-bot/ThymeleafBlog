package ch.supsi.webapp.web;

import ch.supsi.webapp.web.model.BlogPost;
import ch.supsi.webapp.web.model.BlogPostRepository;
import ch.supsi.webapp.web.model.Person;
import ch.supsi.webapp.web.model.RequestStatus;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BlogPostController {

    @Autowired
    BlogPostRepository blogPostRepository;

    private List<BlogPost> persons = new ArrayList<>();

    @RequestMapping(value = "/blogpost", method = RequestMethod.GET)
    public List<BlogPost> get() {
        return Lists.newArrayList(blogPostRepository.findAll());
    }

    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.GET)
    public BlogPost getPerson(@PathVariable long id) {
        for (BlogPost person : blogPostRepository.findAll()) {
            if (person.id == id) {
                return person;
            }
        }
        return null;
    }

    @RequestMapping(value = "/blogpost", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> post(@RequestBody BlogPost blogPost) {
        if (blogPostRepository.findFirstByOrderByIdDesc() != null)
            blogPost.id = blogPostRepository.findFirstByOrderByIdDesc().id + 1;
        else blogPost.id = 0;
        blogPostRepository.save(blogPost);
        return new ResponseEntity<>(blogPost, HttpStatus.OK);
    }

    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RequestStatus> deletePost(@PathVariable long id) {
        BlogPost blogPost = blogPostRepository.findTop1ById(id);
        if (blogPost != null) {
            blogPostRepository.delete(blogPost);
            return new ResponseEntity<>(new RequestStatus(true), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new RequestStatus(false), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RequestStatus> putPost(@PathVariable long id, @RequestBody BlogPost blogPost) {
        BlogPost oldBlogPost = blogPostRepository.findTop1ById(id);
        if (oldBlogPost != null) {
            if (blogPost.author != null) {
                oldBlogPost.author = blogPost.author;
            }
            if (blogPost.title != null) {
                oldBlogPost.title = blogPost.title;
            }
            if (blogPost.text != null) {
                oldBlogPost.text = blogPost.text;
            }
            return new ResponseEntity<>(new RequestStatus(true), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new RequestStatus(false), HttpStatus.NOT_FOUND);
    }
}