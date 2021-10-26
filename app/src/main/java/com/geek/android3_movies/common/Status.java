package com.geek.android3_movies.common;

public enum Status {

    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("Unknown");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}
