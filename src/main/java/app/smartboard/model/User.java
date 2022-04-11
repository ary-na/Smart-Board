package app.smartboard.model;

import java.io.Serializable;

public class User implements Serializable, Login {

    private String userName;
    private String psw;
    private Profile profile;

    public User(String userName, String psw, Profile profile){
        this.userName = userName;
        this.psw = psw;
        this.profile = profile;
    }

    @Override
    public void login() {

    }

    @Override
    public void logout() {

    }
}
