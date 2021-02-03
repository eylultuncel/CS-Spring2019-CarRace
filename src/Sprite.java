import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Sprite
{
    private Image image;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private boolean passed;

    public Sprite()
    {
        setPositionX(0);
        setPositionY(0);
        setVelocityX(0);
        setVelocityY(0);
        setPassed(false);
    }

    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(double x, double y)
    {
        setPositionX(x);
        setPositionY(y);
    }

    public void setVelocity(double x, double y)
    {
        setVelocityX(x);
        setVelocityY(y);
    }

    public void addVelocity(double x, double y)
    {
        setVelocityX(getVelocityX() + x);
        setVelocityY(getVelocityY() + y);
    }

    public void update(double time)
    {
        if(getPositionX() + getVelocityX() * time<155 ){
            setPositionX(155);
        }
        else if(getPositionX() + getVelocityX() * time>387){
            setPositionX(387);
        }
        else {
            setPositionX(getPositionX() + getVelocityX() * time);
            setPositionY(getPositionY() + getVelocityY() * time);
        }
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, getPositionX(), getPositionY());
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(getPositionX(), getPositionY(),width-7,height-7);
    }

    public Rectangle2D getBoundaryEnemy()
    {
        return new Rectangle2D(getPositionX(), getPositionY(),width+100,height+200);
    }


    public boolean intersects(Sprite s,int num)
    {
        if(num==0){
            return s.getBoundaryEnemy().intersects(this.getBoundaryEnemy());
        }
        else{
            return s.getBoundary().intersects( this.getBoundary() );
        }
    }

    public String toString()
    {
        return " Position: [" + getPositionX() + "," + getPositionY() + "]"
                + " Velocity: [" + getVelocityX() + "," + getVelocityY() + "]";
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
