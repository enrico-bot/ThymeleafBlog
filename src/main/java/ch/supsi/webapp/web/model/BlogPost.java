package ch.supsi.webapp.web.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BlogPost {

    @Id
    public long id;

    public String title, author;
    private String date;
    @Column(columnDefinition = "TEXT")
    public String text;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "CATEGORY_ID")
    public Categoria categoria;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Utente user;

    @JsonCreator
    public BlogPost(@JsonProperty("title") String title,
                    @JsonProperty("text") String text,
                    @JsonProperty("author") String author) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.date = LocalDateTime.now().toString();
    }

    // Per hybernate
    public BlogPost() {
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

    public String getDate() {
        return date;
    }
}
