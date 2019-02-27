package ch.supsi.webapp.web.model.entity;

import javax.persistence.*;

@Entity
public class User {
    private String name;
    private String surname;
    @Id
    private String userName;
    @ManyToOne
    private Role role;
    private String password;

    public User() {
    }

    public User(String userName, String name, String surname, Role role, String password) {
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.role = role;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
