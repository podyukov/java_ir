package ru.podyukov.mvc.models;

import jakarta.validation.constraints.Size;

public class Switch {
    private int id;

    @Size(min = 2, max = 32, message = "It should be between 2 and 32 characters")
    private String name;

    public Switch() {}

    public Switch(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
