package com.example.demopro1.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Button registerButton;

    @FXML
    private Button closeButton;

    @FXML
    void handleRegister(ActionEvent event) {
        String fName = firstName.getText();
        String lName = lastName.getText();
        String uName = userName.getText();
        String pass = password.getText();
        String confirmPass = confirmPassword.getText();

        if (fName.isEmpty() || lName.isEmpty() || uName.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
            showAlert("Registration Failed", "Please fill all the fields.");
            return;
        }

        if (!pass.equals(confirmPass)) {
            showAlert("Registration Failed", "Passwords do not match.");
            return;
        }

        // Giả lập lưu dữ liệu (thực tế có thể lưu vào database)
        System.out.println("User Registered: " + uName);

        showAlert("Success", "Registration Successful!");

        // Chuyển sang màn hình đăng nhập
        openLoginScreen(event);
    }

    @FXML
    void handleClose(ActionEvent event) {
        // Đóng cửa sổ đăng ký
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void openLoginScreen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demopro1/View/login-view.fxml"));
            Scene loginScene = new Scene(loader.load());

            // Lấy stage hiện tại
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to open login screen.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
