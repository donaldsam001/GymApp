package com.example.demopro1.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageMemController {

    public Button empManagement;
    public Button deviceManagement;
    public VBox vbox1;
    public Button managementMembership1;
    public VBox vbox;

    @FXML
    private Button logout;

    @FXML
    private Button managementMembership;

    @FXML
    private Button notification;

    @FXML
    private Button setting;

    @FXML
    private Pane topPane;

    @FXML
    private Pane mainContent;

    @FXML
    private void openLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demopro1/View/login-view.fxml"));
            Scene loginScene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to open login screen.");
        }
    }

    @FXML
    private void openMembershipPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demopro1/View/QL_HoiVien.fxml"));
            Pane membershipPane = loader.load();

            QLHV_Controller controller = loader.getController();
            controller.setMainContent(mainContent);

            mainContent.getChildren().setAll(membershipPane);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Không thể mở trang hội viên.");
        }
    }

    @FXML
    private void openThePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demopro1/View/the.fxml"));
            Pane thePane = loader.load();
            mainContent.getChildren().setAll(thePane);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Không thể mở trang thẻ.");
        }
    }

    @FXML
    private void openTimePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demopro1/View/Time.fxml"));
            Pane timePane = loader.load();
            mainContent.getChildren().setAll(timePane);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Không thể mở trang thời gian tập.");
        }
    }

    @FXML
    private void openPayPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demopro1/View/pay.fxml"));
            Pane payPane = loader.load();
            mainContent.getChildren().setAll(payPane);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Không thể mở trang thanh toán.");
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
