package ch.supsi.webapp.web;

import ch.supsi.webapp.web.model.BlogPost;
import ch.supsi.webapp.web.model.BlogPostRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class DynamicBlogController {
    @Autowired
    BlogPostService blogPostService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", blogPostService.getAll(3));
        return "index";
    }

    // Ritorna la pagina di creazione di un BlogPost.
    @GetMapping("/new")
    public String newBlogPost(Model model) {
        model.addAttribute("blogPost", new BlogPost());
        return "bp-new";
    }

    //TODO: Chiedere come fare, se metto /new/post, a non riadattare le dipendeze css
    @PostMapping("/new")
    public String newBlogPost(@ModelAttribute BlogPost blogPost, Model model) {
        blogPostService.add(blogPost);
        return "redirect:/";
    }
}
