package org.shop.Shop.models;

//import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//@Entity
public class User {// extends AbstractEntity {

    private int id;
    private static int nextId = 1;

    @NotBlank(message = "Name is required!")
    private String name;

    @NotBlank(message = "Password is required!")
    @Size(min = 8, message = "Password must have at least 8 characters!")
    private String password;

    @NotBlank(message = "Email is required!")
    @Email(message = "Invalid email!")
    private String email;

    private UserType type;

    @DecimalMin(value = "10", message = "You must be at least 10 years old!")
    private int age;

    public User() {
        id = nextId;
        nextId ++;
    }

    public User(String name, String password, String email, int age, UserType type) {
        this();
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}
