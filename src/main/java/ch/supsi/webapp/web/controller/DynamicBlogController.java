package ch.supsi.webapp.web.controller;

import ch.supsi.webapp.web.model.entity.Category;
import ch.supsi.webapp.web.model.entity.Comment;
import ch.supsi.webapp.web.model.page.*;
import ch.supsi.webapp.web.model.entity.BlogPost;
import ch.supsi.webapp.web.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;

@Controller
public class DynamicBlogController {
    @Autowired
    BlogPostService blogPostService;

    @GetMapping("/")
    public String index(Model model) {
        long numOfPosts = blogPostService.numOfPosts();
        if (numOfPosts > 3) numOfPosts = 3;
        if (numOfPosts == 0) model.addAttribute("posts", new ArrayList<BlogPost>());
        else model.addAttribute("posts", blogPostService.getAll((int) numOfPosts));
        model.addAttribute("page", new IndexPage());
        return "index";
    }

    @GetMapping("/cancellati")
    public String cancellati(Model model) {
        long numOfPosts = blogPostService.numOfPosts();
        if (numOfPosts == 0) model.addAttribute("posts", new ArrayList<BlogPost>());
        else model.addAttribute("posts", blogPostService.getCancellati((int) numOfPosts));
        model.addAttribute("page", new IndexPage());
        return "cancellati";
    }


    // Ritorna la pagina di creazione di un BlogPost.
    @GetMapping("/blog/new")
    public String newBlogPost(Model model) {
        model.addAttribute("blogPost", new BlogPost());
        model.addAttribute("users", blogPostService.getAllUsers());
        model.addAttribute("categories", blogPostService.getAllCategories());
        model.addAttribute("page", new NewPage());
        return "new";
    }

    @PostMapping("/blog/new")
    public String newBlogPost(@ModelAttribute("blogPost") BlogPost blogPost, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        blogPost.setUser(blogPostService.findUserByUsername(user.getUsername()));
        blogPostService.add(blogPost);
        return "redirect:/";
    }

    @GetMapping("/blog/{id}")
    public String newBlogPost(@PathVariable long id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //(Si ê debole, avevo fretta D: )
        //if (blogPostService.get(id).isDeleted() && !user.getUsername().equals("admin"))
        if (blogPostService.get(id).isDeleted())
            return "redirect:/sorry";
        model.addAttribute("blogPost", blogPostService.get(id));
        model.addAttribute("page", new DetailsPage());
        return "details";
    }

    @GetMapping("/sorry")
    public String asd() {
        return "sorry";
    }

    @GetMapping("/blog/{id}/edit")
    public String editBlogPost(@PathVariable long id, Model model) {
        model.addAttribute("blogPost", blogPostService.get(id));
        if (blogPostService.get(id).isDeleted())
            return "redirect:/sorry";
        model.addAttribute("users", blogPostService.getAllUsers());
        model.addAttribute("categories", blogPostService.getAllCategories());
        model.addAttribute("page", new EditPage());
        return "new";
    }

    @PostMapping("/blog/{id}/edit")
    public String editBlogPost(@PathVariable long id, @ModelAttribute("blogPost") BlogPost blogPost, Model model) {
        blogPost.setId(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        blogPost.setUser(blogPostService.findUserByUsername(user.getUsername()));
        blogPostService.add(blogPost);
        return "redirect:/";
    }

    @GetMapping("/blog/{id}/delete")
    public String deleteBlogPost(@PathVariable long id, Model model) {
        blogPostService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("page", new LoginPage());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new ch.supsi.webapp.web.model.entity.User());
        model.addAttribute("page", new RegisterPage());
        return "register";
    }

    @PostMapping("/register")
    public String register(ch.supsi.webapp.web.model.entity.User newUser, Model model) {
        blogPostService.addUser(newUser);
        return "redirect:/login";
    }

    @GetMapping("/search")
    public String search(Model model) {
        List<BlogPost> blogPosts = blogPostService.searchAll(new Category("Cultura"));
        return "redirect:/search";
    }

    @PostMapping("/commenta")
    public String commentaPost(ch.supsi.webapp.web.model.entity.User newUser, Model model) {
        blogPostService.addUser(newUser);
        return "redirect:/commenta";
    }

    @GetMapping("/commenta/{id}")
    public String commentaGet(ch.supsi.webapp.web.model.entity.User newUser, @PathVariable long id, Model model) {
        /*
         * Non va...
         * */

        //model.addAttribute("user", new ch.supsi.webapp.web.model.entity.User());
        model.addAttribute("comment", new Comment());
        return "redirect:/commenta";
    }
}
