package ch.supsi.webapp;

import ch.supsi.webapp.model.BlogPostWrapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;


@WebServlet(value = "/blogpost/api/v1/*")
@SuppressWarnings("serial")
public class BlogHandler extends HttpServlet {
    private HashMap<Long, BlogPostWrapper> blogSet = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String allBlogPosts = getAllBlogPostsToJSONString();
        resp.getWriter().println(allBlogPosts);
    }

    private String getAllBlogPostsToJSONString() {
        return "[" + blogSet.entrySet().stream()
                .map(blogPost -> printSet(blogPost.getKey(), blogPost.getValue()))
                .collect(Collectors.joining(",")) + "]";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long currentTimeMillis = System.currentTimeMillis();
        addNewBlogPost(currentTimeMillis, req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] tokenizedID = tokenizeIDParameters(req);
        if (!isBlogPostIDValid(tokenizedID, resp)) return;

        addNewBlogPost(getBlogPostID(tokenizedID), req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] tokenizedID = tokenizeIDParameters(req);
        if (!isBlogPostIDValid(tokenizedID, resp)) return;

        blogSet.remove(getBlogPostID(tokenizedID));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request received");
        if (req.getMethod().equalsIgnoreCase("PATCH")) {
            System.out.println(req.getPathInfo());

        } else super.service(req, resp);
    }

    private void addNewBlogPost(long postID, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        blogSet.put(postID, new BlogPostWrapper(req));
        resp.getWriter().println(blogSet.get(postID).getJSON());
    }


    private String[] tokenizeIDParameters(HttpServletRequest req) {
        return req.getPathInfo().split("/");
    }


    // Check if uri parameter path info is correct
    private boolean isBlogPostIDValid(String[] tokens, HttpServletResponse resp) throws IOException {
        if (tokens.length != 2) {
            resp.getWriter().println("Error: Method PUT requires an id.");
            return false;
        }
        if (!StringUtils.isNumeric(tokens[1])) {
            resp.getWriter().println("Error: Method PUT. ID must be a number.");
            return false;
        }
        if (!blogSet.containsKey(Long.valueOf(tokens[1]))) {
            resp.getWriter().println("Error: Method PUT. ID not found. Use POST method instead.");
            return false;
        }
        return true;
    }

    private long getBlogPostID(String[] tokens) {
        return Long.valueOf(tokens[1]);
    }

    private static String printSet(Long key, BlogPostWrapper value) {
        return "{\"id\": " + key + ", " + "\"blogEntity\": " + value.getJSON() + "}";
    }

}
