package com.example.demopro1.Controller;

import com.example.demopro1.DAO.MembershipPackageDAO;
import com.example.demopro1.Models.MembershipPackage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ManageServiceController {


    @FXML
    private TableColumn<MembershipPackage, Integer> colId;

    @FXML
    private TableColumn<MembershipPackage, String> colName;

    @FXML
    private TableColumn<MembershipPackage, String> colDescription;

    @FXML
    private TableColumn<MembershipPackage, Integer> colExpDate;

    @FXML
    private TableColumn<MembershipPackage, Float> colPrice;

    @FXML
    private TableColumn<MembershipPackage, Integer> colId1;

    @FXML
    private TableColumn<MembershipPackage, String> colName1;

    @FXML
    private TableColumn<MembershipPackage, String> colDescription1;

    @FXML
    private TableColumn<MembershipPackage, Integer> colExpDate1;

    @FXML
    private TableColumn<MembershipPackage, Float> colPrice1;

    @FXML
    private TableColumn<MembershipPackage, Integer> colId2;

    @FXML
    private TableColumn<MembershipPackage, String> colName2;

    @FXML
    private TableColumn<MembershipPackage, String> colDescription2;

    @FXML
    private TableColumn<MembershipPackage, Integer> colExpDate2;

    @FXML
    private TableColumn<MembershipPackage, Float> colPrice2;


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
    private TableView<MembershipPackage> listPackage;


    @FXML
    private TableView<MembershipPackage> listPackage1;

    @FXML
    private TableView<MembershipPackage> listPackage2;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabViewPackages;

    @FXML
    public void initialize() {
        // setup cell value factory
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory< >("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colExpDate.setCellValueFactory(new PropertyValueFactory<> ("exp"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Load dữ liệu
        loadMembershipPackages();

        handelCreate.setOnAction(e -> {
            createMembershipPackage();
            loadMembershipPackages(); // reload lại sau khi thêm
        });

        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == tabViewPackages) {
                loadMembershipPackages();
            }
        });
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
            int id = Integer.parseInt(code);
            float priceValue = Float.parseFloat(price);
            int exp = Integer.parseInt(expDate);
            boolean status = true; // hoặc kiểm tra từ UI nếu có lựa chọn trạng thái
            MembershipPackage membershipPackage = new MembershipPackage(id, name, priceValue, description, exp, status);

            MembershipPackageDAO dao = new MembershipPackageDAO();
            dao.insertMembershipPackage(membershipPackage);


            System.out.println("Tạo gói hội viên: " + id + ", " + name + ", " + description + ", " + expDate + ", " + priceValue);
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

//    private void loadMembershipPackages() {
//        MembershipPackageDAO dao = new MembershipPackageDAO();
//        java.util.List<MembershipPackage> list = dao.getAllMembershipPackages();
//        javafx.collections.ObservableList<MembershipPackage> data = javafx.collections.FXCollections.observableArrayList(list);
//        listPackage.setItems(data);
//    }

//    private void loadMembershipPackages() {
//        MembershipPackageDAO dao = new MembershipPackageDAO();
//        List<MembershipPackage> list = dao.getAllMembershipPackages();
//        ObservableList<MembershipPackage> data = FXCollections.observableArrayList(list);
//        membershipTable.setItems(data);
//    }

//    private void loadMembershipPackages() {
//        MembershipPackageDAO dao = new MembershipPackageDAO();
//        System.out.println("Đang tải dữ liệu từ database...");
//        List<MembershipPackage> packages = dao.getAllPackages();
//        System.out.println("Số lượng gói lấy được: " + packages.size());
//        for (MembershipPackage p : packages) {
//            System.out.println(p.getId() + " - " + p.getName());
//        }
//
//        if (packages != null && !packages.isEmpty()) {
//            ObservableList<MembershipPackage> data = FXCollections.observableArrayList(packages);
//            listPackage.setItems(data);
//        } else {
//            System.out.println("Không có dữ liệu gói hội viên.");
//        }
//    }

//    private void loadMembershipPackages() {
//            MembershipPackageDAO dao = new MembershipPackageDAO();
//            System.out.println("Đang tải dữ liệu từ database...");
//            List<MembershipPackage> packages = dao.getData();
//            System.out.println("Số lượng gói lấy được: " + packages.size());
//            for (MembershipPackage p : packages) {
//                System.out.println(p.getId() + " - " + p.getName());
//            }
//
//            if (packages != null && !packages.isEmpty()) {
//                ObservableList<MembershipPackage> data = FXCollections.observableArrayList(packages);
//                listPackage.setItems(data);
//            } else {
//                System.out.println("Không có dữ liệu gói hội viên.");
//            }
//    }

    private void loadMembershipPackages() {
        MembershipPackageDAO dao = new MembershipPackageDAO();
        System.out.println("Đang tải dữ liệu từ database...");
        List<MembershipPackage> packages = dao.getData();

        if (packages != null && !packages.isEmpty()) {
            ObservableList<MembershipPackage> data = FXCollections.observableArrayList(packages);
            listPackage.setItems(data);
            System.out.println("Số lượng gói lấy được: " + packages.size());
            for (MembershipPackage p : packages) {
                System.out.println(p.getId() + " - " + p.getName());
            }
        } else {
            System.out.println("Không có dữ liệu gói hội viên.");
        }
    }

}
