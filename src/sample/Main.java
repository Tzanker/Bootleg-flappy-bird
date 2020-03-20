package sample;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
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
import java.util.ArrayList;

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
        Sprite downpipe = new Sprite("resources/images/down_pipe.png");
        Sprite uppipe = new Sprite("resources/images/up_pipe.png");
        Sprite bird = new Sprite("resources/images/bird1.png");
        bird.setPos(250, 256);
        bird.setAcc(0,981);
        ArrayList<Integer> input = new ArrayList<Integer>();
        input.add(0);
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (code == "SPACE") {
                            input.set(0,1);
                        }
                    }
                }
        );

        final long startNanoTime = System.nanoTime();
        new AnimationTimer(){
            public void handle(long currentNanoTime){
                double t = (currentNanoTime - startNanoTime)/1000000000.0;
                gc.drawImage(background, 0, 0);
                gc.drawImage(background, 288, 0);
                if(input.get(0)==1){
                    input.set(0,0);
                    bird.setVel(0,-400);
                }
                bird.update(t);
                downpipe.setPos(250 + 250*Math.sin(t), -600);
                uppipe.setPos(250 + 250*Math.sin(t), 400);
                if(bird.intersects(uppipe) || bird.intersects(downpipe)){
                    Image gameOver = new Image(getClass().getResourceAsStream("resources/images/game_over.png"));
                    gc.drawImage(gameOver, 250, 256);
                    bird.setVel(0,500);
                }
                uppipe.render(gc);
                downpipe.render(gc);
                bird.render(gc);

                System.out.println(t);
            }
        }.start();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
