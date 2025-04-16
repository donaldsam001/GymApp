package com.example.demopro1.DAO;

import com.example.demopro1.Models.MembershipPackage;
import com.example.demopro1.Utils.SQLiteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MembershipPackageDAO {

    private Connection connection;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public void getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:sqlite:service_app.db");
                logger.info("Connected to database");
                createTable();
            }
        } catch (SQLException e) {
            logger.warning(e.toString());
        }
    }

    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS Membership_package (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "price REAL NOT NULL, " +
                "description TEXT, " +
                "exp INTEGER NOT NULL, " +
                "status INTEGER NOT NULL)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.executeUpdate();
            logger.info("Table created or already exists.");
        } catch (SQLException e) {
            logger.warning(e.toString());
        }
    }

    private void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.warning(e.toString());
        }
    }

    public void insertMembershipPackage(MembershipPackage memPackage) {
        getConnection();
        String sql = "INSERT INTO Membership_package (name, price, description, exp, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, memPackage.getName());
            stmt.setFloat(2, memPackage.getPrice());
            stmt.setString(3, memPackage.getDescription());
            stmt.setInt(4, memPackage.getExp());
            stmt.setBoolean(5, memPackage.getStatus());
            stmt.executeUpdate();
            logger.info("Inserted MembershipPackage successfully.");
        } catch (SQLException e) {
            logger.warning(e.toString());
        } finally {
            closeConnection();
        }
    }

    public void updateMembershipPackage(MembershipPackage memPackage) {
        getConnection();
        String sql = "UPDATE Membership_package SET name = ?, price = ?, description = ?, exp = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, memPackage.getName());
            stmt.setFloat(2, memPackage.getPrice());
            stmt.setString(3, memPackage.getDescription());
            stmt.setInt(4, memPackage.getExp());
            stmt.setBoolean(5, memPackage.getStatus());
            stmt.setInt(6, memPackage.getId());

            stmt.executeUpdate();
            logger.info("Updated MembershipPackage successfully.");
        } catch (SQLException e) {
            logger.warning(e.toString());
        } finally {
            closeConnection();
        }
    }

    public void deleteMembershipPackage(int id) {
        getConnection();
        String sql = "DELETE FROM Membership_package WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("Deleted MembershipPackage with id: " + id);
        } catch (SQLException e) {
            logger.warning(e.toString());
        } finally {
            closeConnection();
        }
    }

//    public List<MembershipPackage> getAllMembershipPackages() {
//        getConnection();
//        List<MembershipPackage> list = new ArrayList<>();
//        String sql = "SELECT * FROM Membership_package";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                MembershipPackage pkg = new MembershipPackage(
//                        rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getFloat("price"),
//                        rs.getString("description"),
//                        rs.getInt("exp"),
//                        rs.getBoolean("status")
//                );
//                list.add(pkg);
//            }
//        } catch (SQLException e) {
//            logger.warning(e.toString());
//        } finally {
//            closeConnection();
//        }
//        return list;
//    }

    public List<MembershipPackage> getAllPackages() {
        List<MembershipPackage> packages = new ArrayList<>();
        String url = "jdbc:sqlite:service_app.db";
        String sql = "SELECT * FROM Membership_package";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                String descr=rs.getString("description");
                int exp=        rs.getInt("exp");
                boolean status=        rs.getBoolean("status");
                packages.add(new MembershipPackage(id, name, price, descr, exp, status));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return packages;
    }

}
