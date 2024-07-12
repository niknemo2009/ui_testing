package net_ukr_actions.model;

public record User(String login, String password) {
    public String getEmail() {
        return login + "@ukr.net";
    }
}
