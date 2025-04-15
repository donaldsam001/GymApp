package com.example.demopro1.Controller;

import com.example.demopro1.Models.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class QLHV_Controller implements Initializable {

    @FXML
    private DatePicker End_date;

    @FXML
    private DatePicker Start_date;

    @FXML
    private TextField customerID_hv;

    @FXML
    private TableColumn<Member, String> customer_table_hv;

    @FXML
    private TableColumn<Member, LocalDate> end_table_view;

    @FXML
    private ComboBox<String> gender_hv;

    @FXML
    private TableColumn<Member, String> gender_table_view;

    @FXML
    private TextField name_hv;

    @FXML
    private TableColumn<Member, String> name_table_view;

    @FXML
    private TextField phone_hv;

    @FXML
    private TableColumn<Member, String> phone_table_view;

    @FXML
    private ComboBox<String> schedule_hv;

    @FXML
    private TableColumn<Member, String> schedule_table_view;

    @FXML
    private TableColumn<Member, LocalDate> start_table_view;

    @FXML
    private ComboBox<String> status_hv;

    @FXML
    private TableColumn<Member, String> status_table_view;

    @FXML
    private TableView<Member> table_hv;

    private ObservableList<Member> memberList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Khởi tạo danh sách dữ liệu
        memberList = FXCollections.observableArrayList();

        // Cấu hình các cột trong bảng
        customer_table_hv.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        name_table_view.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone_table_view.setCellValueFactory(new PropertyValueFactory<>("phone"));
        gender_table_view.setCellValueFactory(new PropertyValueFactory<>("gender"));
        schedule_table_view.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        start_table_view.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        end_table_view.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        status_table_view.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Gán danh sách dữ liệu cho bảng
        table_hv.setItems(memberList);

        // Khởi tạo giá trị cho ComboBox
        gender_hv.setItems(FXCollections.observableArrayList("Male", "Female", "Other"));
        schedule_hv.setItems(FXCollections.observableArrayList("Morning", "Afternoon", "Evening"));
        status_hv.setItems(FXCollections.observableArrayList("Active", "Inactive"));
    }

    // Các phương thức xử lý thêm, sửa, xóa, và xóa dữ liệu nhập liệu có thể được thêm vào đây
}