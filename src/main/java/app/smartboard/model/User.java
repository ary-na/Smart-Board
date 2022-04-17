package app.smartboard.model;

import java.io.Serializable;

public class User implements Serializable, Login {

    private String username;
    private String password;
    private Profile profile;

    public User(String username, String password, Profile profile){
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    @Override
    public void login() {

    }
}
