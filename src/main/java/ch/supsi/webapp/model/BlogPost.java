package ch.supsi.webapp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BlogPost {
    private String title, text, author;

    @JsonCreator
    public BlogPost(@JsonProperty("title") String title,
                    @JsonProperty("text") String text,
                    @JsonProperty("author") String author) {

        this.title = title;
        this.text = text;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }
}
