package ch.supsi.webapp.web.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Utente {
    @Id
    public long Id;
    @ManyToOne
    public Ruolo ruolo;
}
