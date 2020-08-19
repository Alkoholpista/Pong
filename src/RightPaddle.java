import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/** Represents the right PONG Paddle. */
public class RightPaddle implements Paddle {

    // Data Fields
    private int dy;
    private int startY = 300;
    private int x =1064;
    private int y = startY;
    private int w;
    private int h;
    // Dimensions
    // w: 36px
    // h: 174px
    private Image image; // Dimensions width: 36px, height: 174px

    // Constructor
    /** Create instance of RightPaddle. */
    public RightPaddle(){
        loadImage();
    }

    /** Creates and loads RightPaddle image. */
    public void loadImage(){
        ImageIcon ii = new ImageIcon("Paddle.png");
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    /** Move the Paddle up or down. */
    @Override
    public void move(){
        y += dy;
        paddleOut();
    }

    /** Checks to see whether the paddle hit the
     *  top or bottom of the screen. If so reset the
     *  y position so it is on the screen
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

    /** Sees which key was pressed and moves the
     *  paddle up or down accordingly
     *  @param e The key event
     */
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        // Key pressed is the up arrow key
        if(key == KeyEvent.VK_UP){
            dy = -6;
        }
        // Key pressed is the down arrow key
        if(key == KeyEvent.VK_DOWN){
            dy = 6;
        }
    }

    /** Sees which key was released and stops
     *  moving the paddle in that direction
     *  @param e The key event
     */
    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        // If key released is the arrow up or down key
        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
            dy = 0;
        }
    }

    /** Get image x value. */
    @Override
    public int getX(){ return x;}
    /** Get paddle y value. */
    @Override
    public int getY(){ return y;}
    /** Get paddle width. */
    @Override
    public int getWidth(){ return w;}
    /** Get paddle height. */
    @Override
    public int getHeight(){ return h;}
    /** Return the paddle image. */
    public Image getImage(){ return image;}
    /** Return the y-axis velocity of the paddle. */
    public int getYVelocity(){ return dy;}
}
