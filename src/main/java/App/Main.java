/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package App;


import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main Class for Daily Autistic Journal Mood Application
 * @author Nafila
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("src/main/java/View/MoodView.fxml").toURI().toURL();

        Scene scene = new Scene(FXMLLoader.load(url));
        
        stage.setTitle("Daily Autistic Journal Mood");
        stage.setScene(scene);
        stage.show();
    }
}

