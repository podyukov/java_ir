package ru.podyukov.mvc.models;

import jakarta.validation.constraints.Min;

public class Zone {
    private int id;

    @Min(value = 0, message = "Number must be a positive integer or 0")
    private int switch_id;

    @Min(value = 0, message = "Number must be a positive integer or 0")
    private int address_id;

    public Zone() {}

    public Zone(int id, int switch_id, int address_id) {
        this.id = id;
        this.switch_id = switch_id;
        this.address_id = address_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSwitch_id() {
        return switch_id;
    }

    public void setSwitch_id(int switch_id) {
        this.switch_id = switch_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
}
