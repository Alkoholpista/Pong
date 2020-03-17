import java.awt.EventQueue;
import javax.swing.JFrame;

/** PONG the game. */
public class Pong extends JFrame {

    private Pong(){ initUI();}

    /** Initializes the user interface. */
    private void initUI(){
        add(new Screen());
        setSize(1100, 800);
        setResizable(false);
        setTitle("PONG");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /** The main methods, runs the game
     *  @param args Not used
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            Pong pong = new Pong();
            pong.setVisible(true);
        });
    }
}
