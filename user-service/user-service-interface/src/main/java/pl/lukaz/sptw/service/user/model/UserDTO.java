package pl.lukaz.sptw.service.user.model;

import java.util.List;

/**
 * @author lzenczuk 31/07/2015
 */
public class UserDTO {
    private final String id;
    private final String name;
    private final String password;
    private final boolean confirmed;
    private final boolean active;
    private final List<String> roles;

    public UserDTO(boolean active, String id, String name, String password, boolean confirmed, List<String> roles) {
        this.active = active;
        this.id = id;
        this.name = name;
        this.password = password;
        this.confirmed = confirmed;
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "active=" + active +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", confirmed=" + confirmed +
                ", roles=" + roles +
                '}';
    }
}
