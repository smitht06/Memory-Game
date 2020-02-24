import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    private String[] imagPaths;
    private int numButtons;
    private JButton[] buttonsArray;
    private ImageIcon imageOnBack;
    private ImageIcon[] imageOnFront;
    private ImageIcon temp;
    private int score = 0;
    private int index = 0;
    private int oddClick = 0;
    private Timer timer;
    private int numFlippedCards;

    //construct the gameboard that will hold the buttons and pictures
    public GamePanel(){
        setLayout(new GridLayout(0,4,0,0));
        imageOnBack = new ImageIcon(this.getClass().getResource("images/back-of-card.png"));
        setBackground(Color.BLUE);
        setVisible(true);
        addButtons();
    }

    public void addButtons(){

    }

}
