package ru.podyukov.mvc.dao;

import org.springframework.stereotype.Component;
import ru.podyukov.mvc.models.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddressDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/podyukov_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Address> index() {
        List<Address> addresses = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM addresses";
            ResultSet resultset = statement.executeQuery(query);

            while (resultset.next()) {
                Address address = new Address(
                        resultset.getInt("id"),
                        resultset.getString("city_name"),
                        resultset.getString("street_name"),
                        resultset.getString("house_number"),
                        resultset.getInt("flat_number")
                );
                addresses.add(address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return addresses;
    }

    public Address show(int id) {
        Address address = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM addresses WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next()) {
                address = new Address(
                        resultset.getInt("id"),
                        resultset.getString("city_name"),
                        resultset.getString("street_name"),
                        resultset.getString("house_number"),
                        resultset.getInt("flat_number")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }

    public void save(Address address) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO addresses (city_name, street_name, house_number, flat_number) VALUES (?, ?, ?, ?)");
            statement.setString(1, address.getCity_name());
            statement.setString(2, address.getStreet_name());
            statement.setString(3, address.getHouse_number());
            statement.setInt(4, address.getFlat_number());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Address address) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE addresses SET city_name=?, street_name=?, house_number=?, flat_number=? WHERE id=?");
            statement.setString(1, address.getCity_name());
            statement.setString(2, address.getStreet_name());
            statement.setString(3, address.getHouse_number());
            statement.setInt(4, address.getFlat_number());
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM addresses WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
