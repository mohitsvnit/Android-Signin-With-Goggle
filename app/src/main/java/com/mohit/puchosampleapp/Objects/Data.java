package com.mohit.puchosampleapp.Objects;

/**
 * Created by mohit on 10/10/16.
 */

public class Data {
    private String email;
    private String title;
    private String details;

    public Data(String email,String title,String details) {
        this.email = email;
        this.title = title;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
