import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/** Class to represent the screen on which everything takes place. */
public class Screen extends JPanel implements ActionListener {

    // Data Fields
    private Timer timer;
    private LeftPaddle leftPaddle;
    private RightPaddle rightPaddle;
    private int leftPaddleScore = 0;
    private int rightPaddleScore = 0;
    private Ball ball;
    private final int DELAY = 10;
    private int paddleHits = 0;

    // Constructor
    public Screen(){ initScreen();}

    /** Initialize the screen. */
    private void initScreen(){
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);

        leftPaddle = new LeftPaddle();
        rightPaddle = new RightPaddle();
        ball = new Ball();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    /** Paint the images on the screen.
     *  @param g The graphics
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    /** Draw the images on the screen.
     *  @param g The graphics
     */
    private void doDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(leftPaddle.getImage(), leftPaddle.getX(), leftPaddle.getY(), this);
        g2d.drawImage(rightPaddle.getImage(), rightPaddle.getX(), rightPaddle.getY(), this);
        g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(), this);

        // Draw the "net"
        Rectangle rect = new Rectangle(500, 0, 50, 800);
        g2d.drawRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());

        // Draw the scores
        Font f = new Font("TimesRoman", Font.PLAIN, 48);
        g.setFont(f);
        g.setColor(Color.WHITE);
        g2d.drawString(Integer.toString(leftPaddleScore), 450, 50);
        g2d.drawString(Integer.toString(rightPaddleScore), 570, 50);
    }

    /** Perform the action which is a step.
     *  @param e The action event
     */
    @Override
    public void actionPerformed(ActionEvent e){
        step();
    }

    /** Move the paddles and ball and repaints
     *  the screen to show new positions.
     *  Also calls the methods to see
     *  whether the ball has hit the paddle
     *  or scored.
     */
    private void step(){
        ball.move();
        leftPaddle.move();
        rightPaddle.move();
        hitPaddle();
        scored();
        repaint();
    }

    /** Method to check and see if the ball
     *  has hit either of the paddles.
     */
    private void hitPaddle(){
        if((ball.getX() >= rightPaddle.getX() - 85) && ((ball.getY() < rightPaddle.getY() + 169 && ball.getY() > rightPaddle.getY())
        || (ball.getY() + 85 > rightPaddle.getY() && ball.getY() + 85 < rightPaddle.getY() + 169))){
            ball.hitPaddle("r", rightPaddle.getYVelocity());
            paddleHits++;
        }
        else if((ball.getX() <= leftPaddle.getX() + 36) && ((ball.getY() < leftPaddle.getY() + 169 && ball.getY() > leftPaddle.getY())
        || (ball.getY() + 85 > leftPaddle.getY() && ball.getY() + 85 < leftPaddle.getY() + 169))){
            ball.hitPaddle("l", leftPaddle.getYVelocity());
            paddleHits++;
        }

        if(paddleHits == 2){
            ball.increaseSpeed();
            paddleHits = 0;
        }
    }

    /** Method to see if either side has scored. */
    private void scored(){
        if(ball.getX() < 0){
            // Increment the score of the right side/paddle
            // by one
            rightPaddleScore++;
            // Set the ball back to the starting position
            ball.reset();
            // Set the paddles back to the center vertically
            rightPaddle.reset();
            leftPaddle.reset();
            // reset the speed and direction of the ball
            // to a random speed and direction
            ball.randomSpeed();
            // Set paddle hits back to 0
            paddleHits = 0;

            // play the score sound
            scoreSound();
        }
        else if(ball.getX() > 1100){
            // Increment the score of the left side/paddle
            // by one
            leftPaddleScore++;
            // Set the ball back to the starting position
            ball.reset();
            // Set the paddles back to the center vertically
            rightPaddle.reset();
            leftPaddle.reset();
            // Reset the speed and direction of the ball
            // to a random speed and direction
            ball.randomSpeed();
            // set paddle hits back to 0
            paddleHits = 0;

            // play the score sound
            scoreSound();
        }
    }

    /** Plays the score sound. */
    private void scoreSound() {
        InputStream music;
        try{
            music = new FileInputStream(new File("nooo.wav"));
            AudioStream audio = new AudioStream(music);
            AudioPlayer.player.start(audio);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /** Class TAdapter delegates the processing to the
     *  methods of the LeftPaddle and RightPaddle classes.
     */
    private class TAdapter extends KeyAdapter {

        /** Sees which key was pressed and acts accordingly
         *  @param e The key event
         */
        @Override
        public void keyPressed(KeyEvent e){
            leftPaddle.keyPressed(e);
            rightPaddle.keyPressed(e);
        }

        /** Sees which key was released and acts accordingly
         *  @param e The key event
         */
        @Override
        public void keyReleased(KeyEvent e){
            leftPaddle.keyReleased(e);
            rightPaddle.keyReleased(e);
        }
    }
}
