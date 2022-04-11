package app.smartboard.model;

import java.io.Serializable;

public class Profile {
    private String firstName;
    private String lastName;
    private String profilePhoto;

    public Profile(String firstName, String lastName, String profilePhoto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePhoto = profilePhoto;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}

