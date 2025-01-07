package ru.podyukov.mvc.dao;

import org.springframework.stereotype.Component;
import ru.podyukov.mvc.models.Switch;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SwitchDAO {

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

    public List<Switch> index() {
        List<Switch> switches = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM switches";
            ResultSet resultset = statement.executeQuery(query);

            while (resultset.next()) {
                Switch switch1 = new Switch(
                        resultset.getInt("id"),
                        resultset.getString("name")
                );
                switches.add(switch1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return switches;
    }

    public Switch show(int id) {
        Switch switch1 = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM switches WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next()) {
                switch1 = new Switch(resultset.getInt("id"), resultset.getString("name"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return switch1;
    }

    public void save(Switch switch1) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO switches (name) VALUES (?)");
            statement.setString(1, switch1.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Switch switch1) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE switches SET name=? WHERE id=?");
            statement.setString(1, switch1.getName());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM switches WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
