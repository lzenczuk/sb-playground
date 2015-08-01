package pl.lukaz.sptw.service.user.inmemory.model;

import java.util.Arrays;
import java.util.List;

/**
 * @author lzenczuk 31/07/2015
 */
public class User {
    private String id;
    private String name;
    private String password;
    private boolean confirmed;
    private boolean active;
    private List<String> roles;

    public User() {
    }

    public User(boolean active, boolean confirmed, String id, String name, String password, String... roles) {
        this.active = active;
        this.confirmed = confirmed;
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = Arrays.asList(roles);
    }

    public static User activeAndConfirmed(String id, String name, String password, String... roles){
        return new User(true, true, id, name, password, roles);
    }

    public static User newAdded(String id, String name, String password, String... roles){
        return new User(false, false, id, name, password , roles);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
