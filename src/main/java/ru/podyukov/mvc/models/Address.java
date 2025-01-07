package ru.podyukov.mvc.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class Address {
    private int id;

    @Size(min = 2, max = 32, message = "It should be between 2 and 32 characters")
    private String city_name;

    @Size(min = 2, max = 32, message = "It should be between 2 and 32 characters")
    private String street_name;

    @Size(min = 2, max = 8, message = "It should be between 2 and 8 characters")
    private String house_number;

    @Min(value = 0, message = "Number must be a positive integer or 0")
    private int flat_number;

    public Address() {}

    public Address(int id, String city_name, String street_name, String house_number, int flat_number) {
        this.id = id;
        this.city_name = city_name;
        this.street_name = street_name;
        this.house_number = house_number;
        this.flat_number = flat_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public int getFlat_number() {
        return flat_number;
    }

    public void setFlat_number(int flat_number) {
        this.flat_number = flat_number;
    }
}
