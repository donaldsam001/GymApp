module com.example.demopro1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;
    requires java.sql;

    opens com.example.demopro1 to javafx.fxml;
    exports com.example.demopro1;
    exports com.example.demopro1.Controller;
    opens com.example.demopro1.Controller to javafx.fxml;
    opens com.example.demopro1.View to javafx.fxml;
}