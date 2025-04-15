package com.example.demopro1.Controller;

import com.example.demopro1.Models.TrainingTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TimeController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<TrainingTime> timeTableView;

    @FXML
    private TableColumn<TrainingTime, String> colCustomerID;

    @FXML
    private TableColumn<TrainingTime, String> colName;

    @FXML
    private TableColumn<TrainingTime, String> colPhone;

    @FXML
    private TableColumn<TrainingTime, String> colStartDay;

    @FXML
    private TableColumn<TrainingTime, String> colEndDay;

    @FXML
    private TableColumn<TrainingTime, String> colStatus;

    private ObservableList<TrainingTime> trainingTimeList;

    @FXML
    public void initialize() {
        // Cấu hình các cột trong bảng
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colStartDay.setCellValueFactory(new PropertyValueFactory<>("startDay"));
        colEndDay.setCellValueFactory(new PropertyValueFactory<>("endDay"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Tạo danh sách thời gian tập mẫu
        trainingTimeList = FXCollections.observableArrayList(
                new TrainingTime("C001", "John Doe", "123456789", "2023-01-01", "2023-12-31", "Active"),
                new TrainingTime("C002", "Jane Smith", "987654321", "2023-02-01", "2023-11-30", "Inactive")
        );

        // Gán danh sách thời gian tập cho bảng
        timeTableView.setItems(trainingTimeList);
    }

    @FXML
    private void handleSearch() {
        String searchText = searchField.getText().toLowerCase();
        if (searchText.isEmpty()) {
            timeTableView.setItems(trainingTimeList);
            return;
        }

        ObservableList<TrainingTime> filteredList = FXCollections.observableArrayList();
        for (TrainingTime time : trainingTimeList) {
            if (time.getCustomerID().toLowerCase().contains(searchText) ||
                    time.getName().toLowerCase().contains(searchText) ||
                    time.getPhone().toLowerCase().contains(searchText)) {
                filteredList.add(time);
            }
        }

        timeTableView.setItems(filteredList);
    }
}