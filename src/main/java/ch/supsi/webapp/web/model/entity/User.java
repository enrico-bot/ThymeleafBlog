package ch.supsi.webapp.web.model.entity;

import javax.persistence.*;

@Entity
public class User {
    @Id
    private String userName;
    @ManyToOne
    private Role role;
    private String password;

    public User() {
    }

    public User(String userName, Role role, String password) {
        this.userName = userName;
        this.role = role;
        this.password = password;
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
