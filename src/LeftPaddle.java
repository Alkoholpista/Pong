import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/** Represents the left PONG Paddle*/
public class LeftPaddle implements Paddle {

    // Data Fields
    private int dy;
    private int x = 0;
    private int y = 346;
    private int w;
    private int h;
    private Image image; // Dimensions width: 36px, height: 174px
    private int score = 0;

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
    public void move(){
        y += dy;
    }

    /** Sees which key was pressed and moves
     *  the player accordingly.
     *  @param e The key event
     */
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
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT){
            dy = 0;
        }
    }

    /** Get image x value. */
    public int getX(){ return x;}
    /** Get image y value. */
    public int getY(){ return y;}
    /** Get image width. */
    public int getWidth(){ return w;}
    /** Get image height. */
    public int getHeight(){ return h;}
    /** Get Paddle image. */
    public Image getImage(){ return image;}
    /** This paddle scored, increment score. */
    public void scored(){ score++;}
    /** Get the score for the left paddle. */
    public String getScore(){ return Integer.toString(score);}
}
