package rikkei.academy.model;

import java.io.Serializable;

public class Category implements Serializable {
    private int id;
    private String name;
    private User user;
    public Category() {
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
