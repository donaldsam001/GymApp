package com.example.demopro1.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DeviceManagementController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button handelAdd;

    @FXML
    private TextField inputCode;

    @FXML
    private TextField inputDescription;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputSearch;

    @FXML
    private TextField inputSearchDel;

    @FXML
    private TextField inputSearchMaintance;

    @FXML
    void initialize() {
        assert handelAdd != null : "fx:id=\"handelAdd\" was not injected: check your FXML file 'device-management.fxml'.";
        assert inputCode != null : "fx:id=\"inputCode\" was not injected: check your FXML file 'device-management.fxml'.";
        assert inputDescription != null : "fx:id=\"inputDescription\" was not injected: check your FXML file 'device-management.fxml'.";
        assert inputName != null : "fx:id=\"inputName\" was not injected: check your FXML file 'device-management.fxml'.";
        assert inputSearch != null : "fx:id=\"inputSearch\" was not injected: check your FXML file 'device-management.fxml'.";
        assert inputSearchDel != null : "fx:id=\"inputSearchDel\" was not injected: check your FXML file 'device-management.fxml'.";
        assert inputSearchMaintance != null : "fx:id=\"inputSearchMaintance\" was not injected: check your FXML file 'device-management.fxml'.";

    }

}
