package edu.school21.models;

public class User {
    private Long id;
    private String login;
    private String password;
    private boolean status;

    public User(Long id, String login, String password, boolean status) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User { " + id
                + " " + login +
                " " + password +
                " " + status;
    }
}
