package sample;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
        final int WIDTHMULT = 4;
        primaryStage.setTitle("Bootleg Flappy Bird");
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(288*WIDTHMULT,512);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image background = new Image(getClass().getResourceAsStream("resources/images/background.png"));
        Sprite bird = new Sprite("resources/images/bird1.png",0);
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
        ArrayList<Pipe> pipeList = new ArrayList();
        Pipe pipe = new Pipe(-200, 4, 0);
        pipeList.add(pipe);

        intVal framecount = new intVal(0);
        final long startNanoTime = System.nanoTime();
        new AnimationTimer(){
            public void handle(long currentNanoTime){
                framecount.val = ((framecount.val ==60)? 0:framecount.val+1);
                double t = (currentNanoTime - startNanoTime)/1000000000.0;
                for(int i=0; i<=WIDTHMULT; i++){
                    gc.drawImage(background, 288*i, 0);
                }
                if(input.get(0)==1){
                    input.set(0,0);
                    bird.setVel(0,-400);
                }
                if(framecount.val % 60 ==0){
                    Pipe newPipe = new Pipe(-200, 4, t);
                    pipeList.add(newPipe);
                }
                for (int j =0; j<pipeList.size(); j++){
                    pipeList.get(j);

                    pipeList.get(j).update(t);
                    if(pipeList.get(j).intersects(bird)){
                        Image gameOver = new Image(getClass().getResourceAsStream("resources/images/game_over.png"));
                        gc.drawImage(gameOver, 250, 256);
                        bird.setVel(0,500);
                    }
                    pipeList.get(j).render(gc);
                    System.out.println(pipeList.size());
                    if(pipeList.get(j).getPosX()<=-138){
                        pipeList.remove(pipeList.get(j));
                    }

                }
                bird.update(t);
                bird.render(gc);
                System.out.println(framecount.val);
            }
        }.start();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
