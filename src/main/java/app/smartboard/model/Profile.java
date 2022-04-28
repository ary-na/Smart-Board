package app.smartboard.model;

import java.io.Serializable;
public class Profile implements Serializable {
    private String firstName;
    private String lastName;
    private byte[] profilePhoto;

    public Profile() {}

    public Profile(String firstName, String lastName, byte[] profilePhoto) {
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

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}

