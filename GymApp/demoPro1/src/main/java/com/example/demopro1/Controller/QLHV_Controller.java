package com.example.demopro1.Controller;

import com.example.demopro1.Models.Member;
import com.example.demopro1.DAO.MemberDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class QLHV_Controller implements Initializable {

    @FXML private TextField customerID_hv, name_hv, phone_hv, age_hv;
    @FXML private ComboBox<String> gender_hv, schedule_hv;
    @FXML private DatePicker Start_date, End_date;
    @FXML private TableView<Member> table_hv;
    @FXML private TableColumn<Member, String> customer_table_hv, name_table_view, phone_table_view, gender_table_view, schedule_table_view;
    @FXML private TableColumn<Member, Integer> age_table_view;
    @FXML private TableColumn<Member, LocalDate> start_table_view, end_table_view;

    private ObservableList<Member> memberList = FXCollections.observableArrayList();
    private Pane mainContent;

    public void setMainContent(Pane mainContent) {
        this.mainContent = mainContent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComboBoxes();
        initTable();
        loadMembers();
        table_hv.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> showMemberDetails(newSelection));
    }

    private void initComboBoxes() {
        gender_hv.setItems(FXCollections.observableArrayList("Male", "Female", "Other"));
        schedule_hv.setItems(FXCollections.observableArrayList("Morning", "Afternoon", "Evening"));
    }

    private void initTable() {
        customer_table_hv.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        name_table_view.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone_table_view.setCellValueFactory(new PropertyValueFactory<>("phone"));
        gender_table_view.setCellValueFactory(new PropertyValueFactory<>("gender"));
        schedule_table_view.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        start_table_view.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        end_table_view.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        age_table_view.setCellValueFactory(new PropertyValueFactory<>("age"));
    }

    private void loadMembers() {
        memberList = MemberDAO.getAllMembers();
        table_hv.setItems(memberList);
    }

    private void showMemberDetails(Member member) {
        if (member != null) {
            customerID_hv.setText(member.getCustomerID());
            name_hv.setText(member.getName());
            phone_hv.setText(member.getPhone());
            gender_hv.setValue(member.getGender());
            schedule_hv.setValue(member.getSchedule());
            Start_date.setValue(member.getStartDate());
            End_date.setValue(member.getEndDate());
            age_hv.setText(String.valueOf(member.getAge()));
        }
    }

    @FXML
    private void handleAddMember() {
        Member newMember = getMemberFromForm();
        if (newMember != null) {
            MemberDAO.insertMember(newMember);
            loadMembers();
            clearForm();
            showAlert("Thành công", "Đã thêm hội viên mới.");
        }
    }

    @FXML
    private void handleUpdateMember() {
        Member updatedMember = getMemberFromForm();
        if (updatedMember != null) {
            MemberDAO.updateMember(updatedMember);
            loadMembers();
            clearForm();
            showAlert("Thành công", "Đã cập nhật thông tin hội viên.");
        }
    }

    @FXML
    private void handleDeleteMember() {
        Member selected = table_hv.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Xác nhận");
            confirm.setHeaderText("Bạn có chắc muốn xóa hội viên này?");
            confirm.setContentText("ID: " + selected.getCustomerID());

            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                MemberDAO.deleteMember(selected.getCustomerID());
                loadMembers();
                clearForm();
                showAlert("Thành công", "Đã xóa hội viên.");
            }
        }
    }

    @FXML
    private void clearForm() {
        customerID_hv.clear();
        name_hv.clear();
        phone_hv.clear();
        age_hv.clear();
        gender_hv.getSelectionModel().clearSelection();
        schedule_hv.getSelectionModel().clearSelection();
        Start_date.setValue(null);
        End_date.setValue(null);
        table_hv.getSelectionModel().clearSelection();
    }

    private Member getMemberFromForm() {
        try {
            return new Member(
                    customerID_hv.getText(),
                    name_hv.getText(),
                    phone_hv.getText(),
                    gender_hv.getValue(),
                    schedule_hv.getValue(),
                    Start_date.getValue(),
                    End_date.getValue(),
                    Integer.parseInt(age_hv.getText())
            );
        } catch (Exception e) {
            showAlert("Dữ liệu không hợp lệ", "Vui lòng điền đầy đủ và đúng định dạng.");
            return null;
        }
    }

    @FXML
    private void goToThePage() {
        switchCenterContent("/com/example/demopro1/View/the.fxml", "Không thể chuyển sang trang Thẻ");
    }

    @FXML
    private void goToPayPage() {
        switchCenterContent("/com/example/demopro1/View/pay.fxml", "Không thể chuyển sang trang Thanh Toán");
    }

    @FXML
    private void goToTimePage() {
        switchCenterContent("/com/example/demopro1/View/Time.fxml", "Không thể chuyển sang trang Thời Gian");
    }

    private void switchCenterContent(String fxmlPath, String errorMessage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Pane newContent = loader.load();
            if (mainContent != null) {
                mainContent.getChildren().setAll(newContent);
            } else {
                showAlert("Lỗi", "mainContent chưa được truyền từ HomeController.");
            }
        } catch (IOException e) {
            showAlert("Lỗi", errorMessage);
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
