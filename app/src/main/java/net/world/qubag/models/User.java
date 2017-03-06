package net.world.qubag.models;

/**
 * Created by mvnpavan on 05/03/17.
 */

public class User {

    String username;

    String password;

    public User() {
        setUsername("");
        setPassword("");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
