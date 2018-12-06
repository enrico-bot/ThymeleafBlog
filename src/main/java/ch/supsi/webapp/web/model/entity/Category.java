package ch.supsi.webapp.web.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
    @Id
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
