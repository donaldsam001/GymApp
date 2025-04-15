package com.example.demopro1.DAO;

import com.example.demopro1.Utils.SQLiteConnection;
import com.example.demopro1.Models.Equipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO {
    public static void insertEquipment(Equipment equipment) {
        String sql = "INSERT INTO Equipment (name, description, status) VALUES (?, ?, ?)";

        try (Connection conn = SQLiteConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, equipment.getName());
            stmt.setString(2, equipment.getDescription());
            stmt.setString(3, equipment.getStatus());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEquipment(int id) {
        String sql = "DELETE FROM Equipment WHERE id = ?";

        try (Connection conn = SQLiteConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<Equipment> getAllEquipment() {
        List<Equipment> list = new ArrayList<>();
        String sql = "SELECT * FROM Equipment";

        try (Connection conn = SQLiteConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Equipment eq = new Equipment(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("status")
                );
                list.add(eq);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


}
