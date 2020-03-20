package sample;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Sprite {
    private Image image;
    private double posX;
    private double posY;
    private double velX;
    private double velY;
    private double accX;
    private double accY;
    private double width;
    private double height;
    private double lastRelTime;
    public Sprite(){
        posX = 0;
        posY = 0;
        velX=0;
        velY=0;
        accX=0;
        accY=0;
    }
    public Sprite(String filename){
        posX = 0;
        posY = 0;
        velX=0;
        velY=0;
        accX=0;
        accY=0;
        Image i = new Image(getClass().getResourceAsStream(filename));
        setImage(i);
    }

    public void setImage(Image i){
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }
    public void setImage(String filename){
        Image i = new Image(getClass().getResourceAsStream(filename));
        setImage(i);
    }
    public void setPos(double x, double y){
        posX = x;
        posY = y;
    }
    public void setVel(double x, double y){
        velX = x;
        velY = y;
    }
    public void addVel(double x, double y)
    {
        velX += x;
        velY += y;
    }

    public void setAcc(double accX, double accY){
        this.accX = accX;
        this.accY = accY;
    }

    public void update(double t)
    {
        double time = t-lastRelTime;
        velX+= accX*time;
        velY+= accY*time;
        posX += velX * time;
        posY += velY * time;
        lastRelTime=t;
    }
    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, posX, posY );
    }
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(posX,posY,width,height);
    }
    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public String toString()
    {
        return " Position: [" + posX + "," + posY + "]"
                + " Velocity: [" + velX + "," + velY + "]";
    }
}
