/**
 * Sample Skeleton for 'management-employee.fxml' Controller Class
 */

package com.example.demopro1.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ManagementEmployeeController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="handelCreate"
    private Button handelCreate; // Value injected by FXMLLoader

    @FXML // fx:id="inputCode"
    private TextField inputCode; // Value injected by FXMLLoader

    @FXML // fx:id="inputDOB"
    private TextField inputDOB; // Value injected by FXMLLoader

    @FXML // fx:id="inputEmail"
    private TextField inputEmail; // Value injected by FXMLLoader

    @FXML // fx:id="inputName"
    private TextField inputName; // Value injected by FXMLLoader

    @FXML // fx:id="inputPhoneNumber"
    private TextField inputPhoneNumber; // Value injected by FXMLLoader

    @FXML // fx:id="inputSearch"
    private TextField inputSearch; // Value injected by FXMLLoader

    @FXML // fx:id="inputSearchX"
    private TextField inputSearchX; // Value injected by FXMLLoader

    @FXML // fx:id="inputSearchX1"
    private TextField inputSearchX1; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert handelCreate != null : "fx:id=\"handelCreate\" was not injected: check your FXML file 'management-employee.fxml'.";
        assert inputCode != null : "fx:id=\"inputCode\" was not injected: check your FXML file 'management-employee.fxml'.";
        assert inputDOB != null : "fx:id=\"inputDOB\" was not injected: check your FXML file 'management-employee.fxml'.";
        assert inputEmail != null : "fx:id=\"inputEmail\" was not injected: check your FXML file 'management-employee.fxml'.";
        assert inputName != null : "fx:id=\"inputName\" was not injected: check your FXML file 'management-employee.fxml'.";
        assert inputPhoneNumber != null : "fx:id=\"inputPhoneNumber\" was not injected: check your FXML file 'management-employee.fxml'.";
        assert inputSearch != null : "fx:id=\"inputSearch\" was not injected: check your FXML file 'management-employee.fxml'.";
        assert inputSearchX != null : "fx:id=\"inputSearchX\" was not injected: check your FXML file 'management-employee.fxml'.";
        assert inputSearchX1 != null : "fx:id=\"inputSearchX1\" was not injected: check your FXML file 'management-employee.fxml'.";

    }

}
