package com.example.demopro1;

import com.example.demopro1.Utils.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class Main extends Application {

//    private Database db= new Database();
//    @Override
//    public void start(Stage primaryStage) {
//        db.createDatabase();

//        // Continue with JavaFX UI setup here...
//    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demopro1/View/manage-service.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/demopro1/Img/rau.jpg")));
        stage.setTitle("Main");
        stage.setScene(scene);
        stage.show();
    }





    public static void main(String[] args) {
        launch();
    }
}
