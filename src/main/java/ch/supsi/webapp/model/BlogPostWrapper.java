package ch.supsi.webapp.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

public class BlogPostWrapper {
    private BlogPost blogPost;

    public BlogPostWrapper(HttpServletRequest req) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (req.getHeader("Content-Type").equals("application/x-www-form-urlencoded")) {
            blogPost = new BlogPost(req.getParameter("title"), req.getParameter("text"), req.getParameter("author"));
        } else {
            String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            blogPost = mapper.readValue(body, BlogPost.class);
        }
    }

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public String getJSON() {
        try {
            return new ObjectMapper().writeValueAsString(blogPost);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "JSON Parsing error";
        }

    }
}
