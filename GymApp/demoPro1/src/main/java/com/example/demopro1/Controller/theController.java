package com.example.demopro1.Controller;

import com.example.demopro1.Models.MemberCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class theController {

    @FXML
    private TextField customerIDField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private ComboBox<String> scheduleComboBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private TableView<MemberCard> cardTableView;

    @FXML
    private TableColumn<MemberCard, String> colCustomerID;

    @FXML
    private TableColumn<MemberCard, String> colName;

    @FXML
    private TableColumn<MemberCard, String> colPhone;

    @FXML
    private TableColumn<MemberCard, String> colGender;

    @FXML
    private TableColumn<MemberCard, String> colSchedule;

    @FXML
    private TableColumn<MemberCard, String> colStartDate;

    @FXML
    private TableColumn<MemberCard, String> colEndDate;

    @FXML
    private TableColumn<MemberCard, String> colStatus;

    private ObservableList<MemberCard> cardList;

    @FXML
    public void initialize() {
        // Cấu hình các cột trong bảng
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colSchedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Tạo danh sách thẻ mẫu
        cardList = FXCollections.observableArrayList(
                new MemberCard("C001", "John Doe", "123456789", "Male", "Morning", "2023-01-01", "2023-12-31", "Active"),
                new MemberCard("C002", "Jane Smith", "987654321", "Female", "Evening", "2023-02-01", "2023-11-30", "Inactive")
        );

        // Gán danh sách thẻ cho bảng
        cardTableView.setItems(cardList);

        // Khởi tạo giá trị cho ComboBox
        genderComboBox.setItems(FXCollections.observableArrayList("Male", "Female", "Other"));
        scheduleComboBox.setItems(FXCollections.observableArrayList("Morning", "Afternoon", "Evening"));
        statusComboBox.setItems(FXCollections.observableArrayList("Active", "Inactive"));
    }

    @FXML
    private void handleAddCard() {
        String customerID = customerIDField.getText();
        String name = nameField.getText();
        String phone = phoneField.getText();
        String gender = genderComboBox.getValue();
        String schedule = scheduleComboBox.getValue();
        String startDate = startDatePicker.getValue() != null ? startDatePicker.getValue().toString() : "";
        String endDate = endDatePicker.getValue() != null ? endDatePicker.getValue().toString() : "";
        String status = statusComboBox.getValue();

        if (customerID.isEmpty() || name.isEmpty() || phone.isEmpty() || gender == null || schedule == null || startDate.isEmpty() || endDate.isEmpty() || status == null) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        MemberCard newCard = new MemberCard(customerID, name, phone, gender, schedule, startDate, endDate, status);
        cardList.add(newCard);
        clearFields();
    }

    @FXML
    private void handleDeleteCard() {
        MemberCard selectedCard = cardTableView.getSelectionModel().getSelectedItem();
        if (selectedCard == null) {
            showAlert("Error", "Please select a card to delete.");
            return;
        }
        cardList.remove(selectedCard);
    }

    @FXML
    private void handleRenewCard() {
        MemberCard selectedCard = cardTableView.getSelectionModel().getSelectedItem();
        if (selectedCard == null) {
            showAlert("Error", "Please select a card to renew.");
            return;
        }
        selectedCard.setEndDate("2024-12-31"); // Gia hạn đến ngày cố định (có thể thay đổi theo logic)
        cardTableView.refresh();
    }

    @FXML
    private void handleClearFields() {
        clearFields();
    }

    private void clearFields() {
        customerIDField.clear();
        nameField.clear();
        phoneField.clear();
        genderComboBox.setValue(null);
        scheduleComboBox.setValue(null);
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        statusComboBox.setValue(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}