package ch.supsi.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@WebServlet(value = "/blogpost")
@SuppressWarnings("serial")
public class BlogHandler extends HttpServlet {
    ArrayList<BlogPost> postArray = new ArrayList<BlogPost>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = postArray.stream()
                .map(BlogPost::getJSON)
                .collect(Collectors.joining("\n"));

        resp.getWriter().println("This is a GET: " +result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        BlogPost obj = null;
        // Attenzione, si può leggere il contenuto del body una volta sola
        if(req.getHeader("Content-Type").equals("application/x-www-form-urlencoded")){
            obj = new BlogPost(req.getParameter("title"),req.getParameter("text"),req.getParameter("author"));
        }
        else {
            String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            obj = mapper.readValue(body, BlogPost.class);
        }
        postArray.add(obj);
        resp.getWriter().println(BlogPost.getJSON(obj));
    }
}
