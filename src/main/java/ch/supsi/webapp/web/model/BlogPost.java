package ch.supsi.webapp.web.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class BlogPost {
    @Id
    public long id;
    public String title, author;
    @Column(columnDefinition = "TEXT")
    public String text;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Categoria categoria;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Utente utente;

    @JsonCreator
    public BlogPost(@JsonProperty("title") String title,
                    @JsonProperty("text") String text,
                    @JsonProperty("author") String author) {
        this.title = title;
        this.text = text;
        this.author = author;
    }

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
}
