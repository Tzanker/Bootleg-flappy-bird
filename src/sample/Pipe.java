package sample;

import javafx.scene.canvas.GraphicsContext;

public class Pipe {
    private Sprite up;
    private Sprite down;
    private double spacing;
    private double posY;
    private double vel;
    private double posX;
    private int PIPEHEIGHT = 793;

//    public Pipe(double spacemult, double bottomHeight, double velX, int widthmult, double time) {
//        up = new Sprite("resources/images/up_pipe.png", time);
//        down = new Sprite("resources/images/down_pipe.png", time);
//        spacing = 64 * spacemult;
//        posY = bottomHeight;
//        vel = velX;
//        posX = 288*widthmult;
//        down.setPos(288*widthmult, posY-spacing-PIPEHEIGHT);
//        up.setPos(288*widthmult, posY);
//        down.setVel(vel, 0);
//        up.setVel(vel, 0);
//    }
    public Pipe(double velX, int widthmult, double time) {
        up = new Sprite("resources/images/up_pipe.png", time);
        down = new Sprite("resources/images/down_pipe.png", time);
        spacing = 64*2  + 64*4*Math.random();
        posY = 200 + 200*Math.random();
        vel = velX;
        posX = 288*widthmult;
        down.setPos(288*widthmult, posY-spacing-PIPEHEIGHT);
        up.setPos(288*widthmult, posY);
        down.setVel(vel, 0);
        up.setVel(vel, 0);
    }


    public void update( double t){
        down.update(t);
        up.update(t);
        posX=down.getPosX();
    }
    public void render(GraphicsContext gc){
        down.render(gc);
        up.render(gc);
    }
    public double getPosX(){
        posX=down.getPosX();
        return posX;
    }
    public boolean intersects(Sprite bird){
        return down.intersects(bird) || up.intersects(bird);
    }
    public void setVel(double velX){
        vel = velX;
    }

}
