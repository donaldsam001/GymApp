package com.example.demopro1.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ManageServiceController {

    @FXML
    private Button handelCreate;

    @FXML
    private TextField inputCode;

    @FXML
    private TextField inputDescription;

    @FXML
    private TextField inputExpDate;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputPrice;

    @FXML
    private TextField inputSearch;

    @FXML
    public void initialize() {
        handelCreate.setOnAction(e -> createMembershipPackage());
    }

    private void createMembershipPackage() {
        String code = inputCode.getText();
        String name = inputName.getText();
        String description = inputDescription.getText();
        String expDate = inputExpDate.getText();
        String price = inputPrice.getText();

        // Kiểm tra dữ liệu đầu vào
        if (code.isEmpty() || name.isEmpty() || description.isEmpty() || expDate.isEmpty() || price.isEmpty()) {
            showAlert("Lỗi", "Vui lòng điền đầy đủ thông tin.", Alert.AlertType.ERROR);
            return;
        }

        try {
            double priceValue = Double.parseDouble(price);
            // TODO: Thêm logic lưu vào database hoặc danh sách
            System.out.println("Tạo gói hội viên: " + code + ", " + name + ", " + description + ", " + expDate + ", " + priceValue);
            showAlert("Thành công", "Đã tạo gói hội viên thành công!", Alert.AlertType.INFORMATION);
            clearInputs();
        } catch (NumberFormatException ex) {
            showAlert("Lỗi", "Giá không hợp lệ.", Alert.AlertType.ERROR);
        }
    }

    private void clearInputs() {
        inputCode.clear();
        inputName.clear();
        inputDescription.clear();
        inputExpDate.clear();
        inputPrice.clear();
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
