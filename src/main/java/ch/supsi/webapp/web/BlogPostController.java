package ch.supsi.webapp.web;

import ch.supsi.webapp.web.model.BlogPost;
import ch.supsi.webapp.web.model.BlogPostRepository;
import ch.supsi.webapp.web.model.Person;
import ch.supsi.webapp.web.model.RequestStatus;
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
    private int idCounter = 0;

    @RequestMapping(value = "/blogpost", method = RequestMethod.GET)
    public List<BlogPost> get() {
        return persons;
    }

    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.GET)
    public BlogPost getPerson(@PathVariable long id) {
        for (BlogPost person : persons) {
            if (person.id == id) {
                return person;
            }
        }
        return null;
    }

    @RequestMapping(value = "/blogpost", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> post(@RequestBody BlogPost blogPost) {
        blogPost.id = idCounter++;
        blogPostRepository.save(blogPost);
        return new ResponseEntity<>(blogPost, HttpStatus.OK);
    }

    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RequestStatus> deletePost(@PathVariable long id) {
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).id == id) {
                persons.remove(i);
                return new ResponseEntity<>(new RequestStatus(true), HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(new RequestStatus(false), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RequestStatus> putPost(@PathVariable long id, @RequestBody BlogPost blogPost) {
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).id == id) {
                if (blogPost.author!=null){
                    persons.get(i).author = blogPost.author;
                }
                if (blogPost.title!=null){
                    persons.get(i).title = blogPost.title;
                }
                if (blogPost.text !=null){
                    persons.get(i).text = blogPost.text;
                }
                return new ResponseEntity<>(new RequestStatus(true), HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(new RequestStatus(false), HttpStatus.NOT_FOUND);
    }
}