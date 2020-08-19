import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/** Represents the left PONG Paddle*/
public class LeftPaddle implements Paddle {

    // Data Fields
    private int dy;
    private int startY = 300;
    private int x = 0;
    private int y = startY
            ;
    private int w;
    private int h;
    private Image image; // Dimensions width: 36px, height: 174px

    // Constructor
    /** Create an instance of LeftPaddle. */
    public LeftPaddle(){ loadImage();}

    /** Creates and loads LeftPaddle image. */
    private void loadImage(){
        ImageIcon ii = new ImageIcon("Paddle.png");
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    /** Move the paddle up or down. */
    @Override
    public void move(){
        y += dy;
        paddleOut();
    }

    /** Checks to see whether the paddle hit the
     *  top or bottom of the screen. If so reset the
     *  Y position so it is on the screen
     */
    private void paddleOut(){
        if(getY() <= 0){
            y = 0;
        }
        else if(getY() >= 600){
            y = 600;
        }
    }

    /** Sets the paddle back to the center
     *  vertically
     */
    public void reset() {
        y = startY;
    }

  /**  /** Sees which key was pressed and moves
     *  the player accordingly.
     *  @param e The key event
     */
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        // Key pressed is left arrow key
        // which moves the left paddle up
        if(key == KeyEvent.VK_LEFT){
            dy = -6;
        }
        if(key == KeyEvent.VK_RIGHT){
            dy = 6;
        }
    }

    /** Sees which key was released and stops moving the
     *  paddle in that direction.
     *  @param e The key event
     */
    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT){
            dy = 0;
        }
    }

    /** Get image x value. */
    @Override
    public int getX(){ return x;}
    /** Get image y value. */
    @Override
    public int getY(){ return y;}
    /** Get image width. */
    @Override
    public int getWidth(){ return w;}
    /** Get image height. */
    @Override
    public int getHeight(){ return h;}
    /** Get Paddle image. */
    public Image getImage(){ return image;}
    /** Return the y-axis velocity of the paddle. */
    public int getYVelocity(){ return dy;}
}
