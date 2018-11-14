package ch.supsi.webapp.web.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BlogPost {
    public String title, text, author;
    public long id;
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
