package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BrickGame extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Scene scene = new Scene(FXMLLoader.load(BrickGame.class.getResource("BrickGameForm.fxml")));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Brick Game");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
