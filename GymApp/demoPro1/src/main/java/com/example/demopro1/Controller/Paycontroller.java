package com.example.demopro1.Controller;

import com.example.demopro1.Models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Paycontroller {

    @FXML
    private ComboBox<String> payment_CustomerID;

    @FXML
    private TextField payment_amout;

    @FXML
    private Label pay_total;

    @FXML
    private Label pay_change;

    @FXML
    private Button payment_payBtn;

    @FXML
    private TableView<Customer> pay_tableview;

    @FXML
    private TableColumn<Customer, String> pay_col_customer;

    @FXML
    private TableColumn<Customer, String> pay_name;

    @FXML
    private TableColumn<Customer, String> pay_phone;

    @FXML
    private TableColumn<Customer, String> pay_col_start;

    @FXML
    private TableColumn<Customer, String> pay_col_end;

    @FXML
    private TableColumn<Customer, String> pay_col_status;

    private ObservableList<Customer> customerList;

    @FXML
    public void initialize() {
        // Cấu hình các cột trong bảng
        pay_col_customer.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        pay_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        pay_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        pay_col_start.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        pay_col_end.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        pay_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Tạo danh sách khách hàng mẫu
        customerList = FXCollections.observableArrayList(
                new Customer("C001", "John Doe", "123456789", "2023-01-01", "2023-12-31", "Active"),
                new Customer("C002", "Jane Smith", "987654321", "2023-02-01", "2023-11-30", "Inactive")
        );

        // Gán danh sách khách hàng cho bảng
        pay_tableview.setItems(customerList);

        // Gán danh sách khách hàng cho ComboBox
        ObservableList<String> customerIDs = FXCollections.observableArrayList();
        for (Customer customer : customerList) {
            customerIDs.add(customer.getCustomerID());
        }
        payment_CustomerID.setItems(customerIDs);
    }

    @FXML
    private void handlePay() {
        String selectedCustomerID = payment_CustomerID.getValue();
        String amountText = payment_amout.getText();

        if (selectedCustomerID == null || amountText.isEmpty()) {
            showAlert("Error", "Please select a customer and enter the payment amount.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            double total = 100.0; // Giả lập tổng số tiền cần thanh toán
            double change = amount - total;

            if (change < 0) {
                showAlert("Error", "Insufficient amount. Please enter a valid amount.");
                return;
            }

            // Cập nhật giao diện
            pay_total.setText("$" + total);
            pay_change.setText("$" + change);

            showAlert("Success", "Payment successful!");
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid amount. Please enter a valid number.");
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
