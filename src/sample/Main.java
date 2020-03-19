package sample;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
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
        primaryStage.setTitle("Bootleg Flappy Bird");
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(500,512);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image background = new Image(getClass().getResourceAsStream("resources/images/background.png"));
        Image downpipe = new Image(getClass().getResourceAsStream("resources/images/down_pipe.png"));
        Image uppipe = new Image(getClass().getResourceAsStream("resources/images/up_pipe.png"));
        final long startNanoTime = System.nanoTime();
        new AnimationTimer(){
            public void handle(long currentNanoTime){
                double t = (currentNanoTime - startNanoTime)/1000000000.0;
                gc.drawImage(background, 0, 0);
                gc.drawImage(background, 288, 0);
                gc.drawImage(downpipe, 250 + 250*Math.sin(t), -600);
                gc.drawImage(uppipe, 250 + 250*Math.sin(t), 400);
            }
        }.start();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
