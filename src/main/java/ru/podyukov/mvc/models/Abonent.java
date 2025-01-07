package ru.podyukov.mvc.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class Abonent {
    private int id;

    @Size(min = 2, max = 30, message = "It should be between 2 and 30 characters")
    private String name;

    @Size(min = 1, max = 16, message = "It should be between 1 and 16 characters")
    private String phone;

    @Min(value = 0, message = "Number must be a positive integer or 0")
    private int address_id;

    @Min(value = 0, message = "Number must be a positive integer or 0")
    private int switch_id;

    public Abonent() {}

    public Abonent(int id, String name, String phone, int address_id, int switch_id) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address_id = address_id;
        this.switch_id = switch_id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getSwitch_id() {
        return switch_id;
    }

    public void setSwitch_id(int switch_id) {
        this.switch_id = switch_id;
    }
}
