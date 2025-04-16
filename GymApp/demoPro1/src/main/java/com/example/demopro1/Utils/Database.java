package com.example.demopro1.Utils;
import com.example.demopro1.Models.Equipment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String DB_URL = "jdbc:sqlite:service_app.db";

    public static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();

                // Employee table
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Employee (
                        id TEXT PRIMARY KEY,
                        name TEXT NOT NULL,
                        password TEXT NOT NULL,
                        phone TEXT NOT NULL,
                        role TEXT NOT NULL
                    );
                """);

                // Membership package table
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Membership_package (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        price INTEGER NOT NULL,
                        description TEXT,
                        exp INTEGER NOT NULL,
                        status TEXT NOT NULL
                    );
                """);

                // Membership table
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Membership (
                        id TEXT PRIMARY KEY,
                        name TEXT NOT NULL,
                        gender TEXT,
                        phone TEXT NOT NULL
                    );
                """);

                // Equipment table
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Equipment (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        description TEXT,
                        status TEXT NOT NULL,
                        date_repair TEXT
                    );
                """);

                // Membership card table
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Membership_card (
                        id_mem TEXT NOT NULL,
                        id_package INTEGER NOT NULL,
                        status TEXT NOT NULL,
                        start_date TEXT NOT NULL,
                        end_date TEXT NOT NULL,
                        PRIMARY KEY (id_mem, id_package),
                        FOREIGN KEY (id_mem) REFERENCES Membership(id),
                        FOREIGN KEY (id_package) REFERENCES Membership_package(id)
                    );
                """);

                //History table
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS History (
                        date_id INTEGER PRIMARY KEY AUTOINCREMENT,
                        date TEXT NOT NULL,
                        time_in TEXT NOT NULL,
                        time_out TEXT NOT NULL,
                        note TEXT
                    );
                """);

                System.out.println("Database and all tables created successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static final String DB_PATH = "service_app.db"; // Tên file DB, có thể là đường dẫn đầy đủ

    public static void deleteDatabase() {
        File dbFile = new File(DB_PATH);
        if (dbFile.exists()) {
            if (dbFile.delete()) {
                System.out.println("Database deleted successfully.");
            } else {
                System.out.println("Failed to delete the database.");
            }
        } else {
            System.out.println("Database file does not exist.");
        }
    }


}

