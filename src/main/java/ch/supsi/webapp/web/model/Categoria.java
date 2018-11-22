package ch.supsi.webapp.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Categoria {
    @Id
    @Column(name = "CATEGORY_ID")
    public long Id;
    public String name;
}
