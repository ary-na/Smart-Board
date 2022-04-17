package app.smartboard.model;

import java.io.Serializable;

public class Profile {
    private String firstName;
    private String lastName;
    private Byte[] profilePhoto;

    public Profile(String firstName, String lastName, Byte[] profilePhoto) {
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

    public Byte[] getProfilePhoto() {
        return profilePhoto;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setProfilePhoto(Byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}

