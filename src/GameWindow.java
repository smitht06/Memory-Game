import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    //JFrame constructor
    public GameWindow(){
        //set title, visable, size exit on close and layout
        setTitle("Memory Game");
        setVisible(true);
        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,7));
        setContentPane(new GamePanel());
    }
}
