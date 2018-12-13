package ch.supsi.webapp.web.service;

import ch.supsi.webapp.web.model.entity.BlogPost;
import ch.supsi.webapp.web.model.entity.Category;
import ch.supsi.webapp.web.model.entity.Role;
import ch.supsi.webapp.web.model.entity.User;
import ch.supsi.webapp.web.repository.BlogPostRepository;
import ch.supsi.webapp.web.model.RequestStatus;
import ch.supsi.webapp.web.repository.CategoryRepository;
import ch.supsi.webapp.web.repository.RoleRepository;
import ch.supsi.webapp.web.repository.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


// Logica applicativa lato server
// create database bottani CHARACTER SET=latin1
@Service
public class BlogPostService {

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {
        if (userRepository.findAll().size() == 0) {
            roleRepository.save(new Role("ROLE_USER"));
            roleRepository.save(new Role("ROLE_ADMIN"));
            User admin = new User(
                    "admin",
                    new Role("ROLE_ADMIN"),
                    new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(admin);

        }

        if (categoryRepository.findAll().size() == 0) {
            categoryRepository.save(new Category("Cultura"));
            categoryRepository.save(new Category("Scienza"));
            categoryRepository.save(new Category("Sport"));
        }
    }


    public BlogPost get(long id) {
        return blogPostRepository.findById(id).get();
    }

    public List<BlogPost> getAll(int size) {
        return blogPostRepository.findAll(PageRequest.of(0, size, Sort.Direction.DESC, "date")).getContent();
    }

    public ResponseEntity<BlogPost> add(BlogPost blogPost) {
        blogPostRepository.save(blogPost);
        return new ResponseEntity<>(blogPost, HttpStatus.OK);
    }

    public ResponseEntity<RequestStatus> delete(long id) {
        BlogPost blogPost = blogPostRepository.findTop1ById(id);
        if (blogPost != null) {
            blogPostRepository.delete(blogPost);
            return new ResponseEntity<>(new RequestStatus(true), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new RequestStatus(false), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<RequestStatus> update(long id, BlogPost blogPost) {
        BlogPost oldBlogPost = blogPostRepository.findTop1ById(id);
        if (oldBlogPost != null) {
            if (blogPost.getUser() != null) {
                oldBlogPost.setUser(blogPost.getUser());
            }
            if (blogPost.getTitle() != null) {
                oldBlogPost.setTitle(blogPost.getTitle());
            }
            if (blogPost.getText() != null) {
                oldBlogPost.setText(blogPost.getText());
            }
            if (blogPost.getCategory() != null) {
                oldBlogPost.setCategory(blogPost.getCategory());
            }
            blogPostRepository.save(oldBlogPost);
            return new ResponseEntity<>(new RequestStatus(true), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new RequestStatus(false), HttpStatus.NOT_FOUND);
    }

    public List<BlogPost> getAllBlogPosts() {
        return Lists.newArrayList(blogPostRepository.findAll());
    }

    public long numOfPosts() {
        return blogPostRepository.count();
    }

    public List<User> getAllUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }

    public List<Role> getAllRoles() {
        return Lists.newArrayList(roleRepository.findAll());
    }

    public List<Category> getAllCategories() {
        return Lists.newArrayList(categoryRepository.findAll());
    }

    public User findUserByUsername(String username) {
        return userRepository.findById(username).get();
    }

    public void addUser(User newUser) {
        User admin = new User(
                newUser.getUserName(),
                new Role("ROLE_USER"),
                new BCryptPasswordEncoder().encode(newUser.getPassword()));
        userRepository.save(admin);
    }
}
