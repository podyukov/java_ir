package ru.podyukov.mvc.dao;

import org.springframework.stereotype.Component;
import ru.podyukov.mvc.models.Abonent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AbonentDAO {

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

    public List<Abonent> index() {
        List<Abonent> abonents = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM abonents";
            ResultSet resultset = statement.executeQuery(query);

            while (resultset.next()) {
                Abonent abonent = new Abonent(
                        resultset.getInt("id"),
                        resultset.getString("name"),
                        resultset.getString("phone"),
                        resultset.getInt("address_id"),
                        resultset.getInt("switch_id")
                );
                abonents.add(abonent);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return abonents;
    }

    public Abonent show(int id) {
        Abonent abonent = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM abonents WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next()) {
                abonent = new Abonent(
                        resultset.getInt("id"),
                        resultset.getString("name"),
                        resultset.getString("phone"),
                        resultset.getInt("address_id"),
                        resultset.getInt("switch_id")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return abonent;
    }

    public void save(Abonent abonent) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO abonents (name, phone, address_id, switch_id) VALUES (?, ?, ?, ?)");
            statement.setString(1, abonent.getName());
            statement.setString(2, abonent.getPhone());
            statement.setInt(3, abonent.getAddress_id());
            statement.setInt(4, abonent.getSwitch_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Abonent abonent) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE abonents SET name=?, phone=?, address_id=?, switch_id=? WHERE id=?");
            statement.setString(1, abonent.getName());
            statement.setString(2, abonent.getPhone());
            statement.setInt(3, abonent.getAddress_id());
            statement.setInt(4, abonent.getSwitch_id());
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM abonents WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
