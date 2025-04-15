package com.example.demopro1.Utils;

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
                        email TEXT,
                        phone TEXT NOT NULL,
                        age INTEGER
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
                        email TEXT,
                        phone TEXT NOT NULL,
                        age INTEGER
                    );
                """);

                // Equipment table
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Equipment (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        description TEXT,
                        status TEXT NOT NULL
                    );
                """);

                // Membership card table
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Membership_card (
                        id_mem TEXT NOT NULL,
                        id_package INTEGER NOT NULL,
                        name TEXT NOT NULL,
                        status TEXT NOT NULL,
                        PRIMARY KEY (id_mem, id_package),
                        FOREIGN KEY (id_mem) REFERENCES Membership(id),
                        FOREIGN KEY (id_package) REFERENCES Membership_package(id)
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
