package ch.supsi.webapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BlogPost {
    public String title, text, author;

    public BlogPost(String title, String text, String author) {
        this.title = title;
        this.text = text;
        this.author = author;
    }
    public BlogPost(){
        this("","","");
    }

    public static String getJSON(BlogPost p){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(p);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "JSON ERROR";
        }
    }
}
