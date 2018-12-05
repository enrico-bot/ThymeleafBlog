package ch.supsi.webapp.web;

import ch.supsi.webapp.web.model.BlogPost;
import ch.supsi.webapp.web.model.BlogPostRepository;
import ch.supsi.webapp.web.model.RequestStatus;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


// Logica applicativa lato server
@Service
public class BlogPostService {
    @Autowired
    BlogPostRepository blogPostRepository;

    BlogPost get(long id) {
        return blogPostRepository.findById(id).get();
    }

    List<BlogPost> getAll(int size) {

        return blogPostRepository.findAll(PageRequest.of(0, size, Sort.Direction.DESC, "date")).getContent();
    }

    ResponseEntity<BlogPost> add(BlogPost blogPost) {
        if (blogPostRepository.findFirstByOrderByIdDesc() != null)
            blogPost.id = blogPostRepository.findFirstByOrderByIdDesc().id + 1;
        else blogPost.id = 1;
        blogPostRepository.save(blogPost);
        return new ResponseEntity<>(blogPost, HttpStatus.OK);
    }

    ResponseEntity<RequestStatus> delete(long id) {
        BlogPost blogPost = blogPostRepository.findTop1ById(id);
        if (blogPost != null) {
            blogPostRepository.delete(blogPost);
            return new ResponseEntity<>(new RequestStatus(true), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new RequestStatus(false), HttpStatus.NOT_FOUND);
    }

    ResponseEntity<RequestStatus> update(long id, BlogPost blogPost) {
        BlogPost oldBlogPost = blogPostRepository.findTop1ById(id);
        System.out.println(blogPost.title);
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
            if (blogPost.categoria != null) {
                oldBlogPost.categoria = blogPost.categoria;
            }
            blogPostRepository.save(oldBlogPost);
            return new ResponseEntity<>(new RequestStatus(true), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new RequestStatus(false), HttpStatus.NOT_FOUND);
    }

    List<BlogPost> getAllBlogPosts() {
        return Lists.newArrayList(blogPostRepository.findAll());
    }
}
