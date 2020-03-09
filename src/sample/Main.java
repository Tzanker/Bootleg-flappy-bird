package sample;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Translate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = FXMLLoader.load(getClass().getResource("background.fxml"));
        root.getStylesheets().add(getClass().getResource("./resources/Main.css").toExternalForm());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 512));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
