package com.qwy_games.fllaf_messenger.authservice.auth.requests;

public class LoginRequest {

    private String link;
    private String password;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
