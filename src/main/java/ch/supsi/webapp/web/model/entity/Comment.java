package ch.supsi.webapp.web.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime date;
    @OneToMany
    private List<Comment> subc = new ArrayList<>();
    @ManyToOne
    private User user;

    public Comment(){
        this.date = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Comment> getSubc() {
        return subc;
    }

    public void setSubc(List<Comment> comment) {
        this.subc = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
