package com.example.dogsinternet;

public class DogImage {

    private String message;
    private String status;

    public DogImage(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "DogImage{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
