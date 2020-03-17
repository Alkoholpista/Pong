import java.awt.event.KeyEvent;

/** Interface Paddle. */
public interface Paddle {

    /** Move the paddle up and down. */
    void move();

    /** Sees which key is pressed and move paddle
     *  accordingly.
     *  @param e The KeyEvent
     */
    void keyPressed(KeyEvent e);

    /** Sees which key is released and stops moving
     *  Paddle in that direction.
     *  @param e The KeyEvent
     */
    void keyReleased(KeyEvent e);

    /** Get x value. */
    int getX();
    /** Get y value. */
    int getY();
    /** Get width. */
    int getWidth();
    /** Get height. */
    int getHeight();
}
