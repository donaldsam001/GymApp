package com.example.demopro1;

import com.example.demopro1.Models.MembershipPackage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.*;

public class Test extends Application {

    private TableView<MembershipPackage> table = new TableView<>();

    @Override
    public void start(Stage stage) {

        TableColumn<MembershipPackage, Integer> idCol = new TableColumn<>("ID");
        TableColumn<MembershipPackage, String> nameCol = new TableColumn<>("Name");
        TableColumn<MembershipPackage, Float> priceCol = new TableColumn<>("Price");
        TableColumn<MembershipPackage, String> descCol = new TableColumn<>("Description");
        TableColumn<MembershipPackage, Integer> expCol = new TableColumn<>("Exp");
        TableColumn<MembershipPackage, Boolean> statusCol = new TableColumn<>("Status");

        // Kết nối cột với thuộc tính trong model
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        expCol.setCellValueFactory(new PropertyValueFactory<>("exp"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().addAll(idCol, nameCol, priceCol, descCol, expCol, statusCol);

        // Load data from SQLite
        ObservableList<MembershipPackage> data = loadFromDatabase();
        table.setItems(data);

        StackPane root = new StackPane();
        root.setPadding(new Insets(10));
        root.getChildren().add(table);

        Scene scene = new Scene(root, 800, 400);
        stage.setScene(scene);
        stage.setTitle("Membership Packages - From SQLite");
        stage.show();
    }

    private ObservableList<MembershipPackage> loadFromDatabase() {
        ObservableList<MembershipPackage> list = FXCollections.observableArrayList();
        String url = "jdbc:sqlite:service_app.db";

        String sql = "SELECT * FROM Membership_package";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                String desc = rs.getString("description");
                int exp = rs.getInt("exp");
                // Nếu status lưu dưới dạng TEXT như "true"/"false"
                boolean status = rs.getString("status").equalsIgnoreCase("true");

                list.add(new MembershipPackage(id, name, price, desc, exp, status));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
