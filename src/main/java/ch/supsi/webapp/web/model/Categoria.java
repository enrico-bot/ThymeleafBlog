package ch.supsi.webapp.web.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Categoria {
    @Id
    public long Id;
    String name;
}
