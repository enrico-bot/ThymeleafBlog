package ch.supsi.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

@WebServlet(value = "/blogpost")
@SuppressWarnings("serial")
public class BlogHandler extends HttpServlet {
    HashMap<Integer, BlogPost> postArray = new HashMap<>();
    static  int i =0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = "[" + (new ArrayList<>(postArray.values())).stream()
                .map(BlogPost::getJSON)
                .collect(Collectors.joining(",")) + "]";
        resp.getWriter().println(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlogPost obj = reqToBlogPost(req);

        postArray.put(i++, obj);
        resp.getWriter().println(BlogPost.getJSON(obj));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlogPost obj = reqToBlogPost(req);
        if (!postArray.containsKey(obj.title))
            resp.getWriter().println("Error[601]: Post " + obj.title + " does not exists.");
        else {
            postArray.put(i++, obj);
            resp.getWriter().println(BlogPost.getJSON(obj));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlogPost obj = reqToBlogPost(req);
        postArray.remove(i++);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("PATCH")) {
            BlogPost obj = reqToBlogPost(req);
            if (!postArray.containsKey(i))
                resp.getWriter().println("Error[601]: PATCH " + obj.title + " does not exists.");
            else {
                BlogPost old = postArray.get(i);
                if (obj.author.equalsIgnoreCase("")) obj.author = old.author;
                if (obj.text.equalsIgnoreCase("")) obj.text = old.text;
                postArray.put(i++, obj);
                resp.getWriter().println(BlogPost.getJSON(obj));
            }
        } else super.service(req, resp);
    }


    private static BlogPost reqToBlogPost(HttpServletRequest req) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (req.getHeader("Content-Type").equals("application/x-www-form-urlencoded")) {
            return new BlogPost(req.getParameter("title"), req.getParameter("text"), req.getParameter("author"));
        } else {
            String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            return mapper.readValue(body, BlogPost.class);
        }
    }

}
