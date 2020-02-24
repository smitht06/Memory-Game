import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel{
    private String[] imagePaths;
    private int numberOfButtons;
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
        numberOfButtons = imagePaths.length*2;
        buttonsArray = new JButton[numberOfButtons];
        imageOnFront = new ImageIcon[numberOfButtons];

        for(int i = 0, j =0; i < imagePaths.length; i++){
            imageOnFront[j] = new ImageIcon(this.getClass().getResource(imagePaths[i]));


        }
        Random random = new Random();
        for(int i = 0; i < numberOfButtons; i++){
            int j = random.nextInt(numberOfButtons);
            temp = imageOnFront[i];
            imageOnFront[i] = imageOnFront[j];
            imageOnFront[j] = temp;
        }
        
    }
    private int makeButtons(int j){
        buttonsArray[j] = new JButton();

        buttonsArray[j].setIcon(imageOnBack);
        add(buttonsArray[j++]);
        return j;
    }

}
