package org.example.mpp_ui.Domain;

public class User extends Entity<Long>{
    private final String username;
    private final String password;

    public User(Long id, String username, String Password) {
        super(id);
        this.username = username;
        this.password = Password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
