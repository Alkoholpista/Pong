import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/** Represents a PONG ball. */
public class Ball {

    // Data Fields
    private int dx;
    private int dy;
    private int x = 550;
    private int y;
    private int w;
    private int h;
    private Image image; // Dimensions width: 86px, height: 90px
    private Random random;

    // Constructor
    /** Create an instance of Ball. */
    public Ball(){
        loadImage();
        randomSpeed();
    }

    /** Creates and loads Ball image. */
    private void loadImage(){
        ImageIcon ii = new ImageIcon("Ball.png");
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    /** Move the Ball around the screen. */
    public void move(){
        x += dx;
        y += dy;

        hitTopOrBottom();
    }

    /** Detects if ball has hit the top or bottom
     *  of the screen and bounces it in the other
     *  direction.
     */
    private void hitTopOrBottom(){
        if(getY() <= 0){
            dy = Math.abs(dy);
        }
        else if(getY() >= 675){
            dy = 0 - dy;
        }
    }

    /** Hit one of the paddles, x direction of the
     *  ball needs to be flipped.
     *  @param paddle Which paddle the ball hit*/
    public void hitPaddle(String paddle){
        if(paddle.equals("l")){
            dx = Math.abs(dx);
        }
        else if(paddle.equals("r")){
            dx = 0 - dx;
        }
    }

    /** Resets the ball to the starting position
     *  means one side scored.
     */
    public void reset(){
        x = 550;
        y = 400;
        dy = 4;
        dx = 4;
    }

    /** Increases the speed that the ball is moving. */
    public void increaseSpeed(){
        dy += 2;
        dx += 2;
    }

    /** Randomly sets the speed and direction of the ball. */
    public void randomSpeed(){
        int[] speeds = {-2, 2, -3, 3, -4, 4, -5, 5};
        Random r = new Random();
        dy = speeds[r.nextInt(speeds.length)];
        dx = speeds[r.nextInt(speeds.length)];
        y = r.nextInt(600);
    }

    /** Get image x value. */
    public int getX(){ return x;}
    /** Get image y value. */
    public int getY(){ return y;}
    /** Get image width. */
    public int getWidth(){ return h;}
    /** Get image height. */
    public int getHeight(){ return h;}
    /** Return the image. */
    public Image getImage(){ return image;}
}
