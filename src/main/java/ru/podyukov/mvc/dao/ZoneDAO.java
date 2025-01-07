package ru.podyukov.mvc.dao;

import org.springframework.stereotype.Component;
import ru.podyukov.mvc.models.Zone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ZoneDAO {

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

    public List<Zone> index() {
        List<Zone> zones = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM zones";
            ResultSet resultset = statement.executeQuery(query);

            while (resultset.next()) {
                Zone zone = new Zone(
                        resultset.getInt("id"),
                        resultset.getInt("switch_id"),
                        resultset.getInt("address_id")
                );
                zones.add(zone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return zones;
    }

    public Zone show(int id) {
        Zone zones = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM zones WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next()) {
                zones = new Zone(
                        resultset.getInt("id"),
                        resultset.getInt("switch_id"),
                        resultset.getInt("address_id")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return zones;
    }

    public void save(Zone zone) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO zones (switch_id, address_id) VALUES (?, ?) on conflict (switch_id, address_id) do nothing");
            statement.setInt(1, zone.getSwitch_id());
            statement.setInt(2, zone.getAddress_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Zone zone) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE zones SET switch_id=?, address_id=? WHERE id=?");
            statement.setInt(1, zone.getSwitch_id());
            statement.setInt(2, zone.getAddress_id());
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM zones WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
