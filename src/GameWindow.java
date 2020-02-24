import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public GameWindow(){
        setTitle("Memory Game");
        setBounds(5,5,900,700);
        setVisible(true);
        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,7));

    }
}
