package ch.supsi.webapp.web.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BlogPost {
    // FIELDS
    @Id
    @GeneratedValue
    private long id;

    private String title;
    private LocalDateTime date;
    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    private boolean deleted;

    @OneToMany
    private List<Comment> comment = new ArrayList<>();


    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }


    // METHODS




    public BlogPost() {
        this.date = LocalDateTime.now();
        this.deleted = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
