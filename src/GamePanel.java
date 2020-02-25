import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;

public class GamePanel extends JPanel{
    private static String[] imagePaths = {"images/bulbasaur.png","images/butterfree.png","images/dragonite.png","images/Eevee.png",
            "images/Meowth.png","images/nidoran.png","images/pikachu.png","images/wartortle.png"} ;
    private int numberOfButtons;
    private JButton[] buttonsArray;
    private ImageIcon imageOnBack;
    private ImageIcon[] imageOnFront;
    private ImageIcon temp;
    private int score = 0;
    private int index = 0;
    private int oddClick = 0;
    private int numberOfClicks = 0;
    private Timer timer;
    private int numFlippedCards;
    private boolean gameOver = false;

    //construct the gameboard that will hold the buttons and pictures
    public GamePanel(){
        setLayout(new GridLayout(0,4,0,0));
        imageOnBack = new ImageIcon(this.getClass().getResource("images/back.jpg"));
        imageOnBack = (ImageIcon) reSizeIcon(imageOnBack,200,200);
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
            imageOnFront[j] = (ImageIcon) reSizeIcon(imageOnFront[j],200,200);
            j = makeButtons(j);
            imageOnFront[j] = imageOnFront[j-1];
            j = makeButtons(j);
        }
        Random random = new Random();
        for(int i = 0; i < numberOfButtons; i++){
            int j = random.nextInt(numberOfButtons);
            temp = imageOnFront[i];
            imageOnFront[i] = imageOnFront[j];
            imageOnFront[j] = temp;
        }
        timer = new Timer(1000, new TimerAction());
    }
    private int makeButtons(int j){
        buttonsArray[j] = new JButton();
        buttonsArray[j].addActionListener(new GamePanel.ButtonListener());
        buttonsArray[j].setIcon(imageOnBack);
        add(buttonsArray[j++]);
        return j;
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(timer.isRunning()){
                return;
            }numFlippedCards++;
            for(int i = 0; i < numberOfButtons; i++){
                if(e.getSource()==buttonsArray[i]){
                    buttonsArray[i].setIcon(imageOnFront[i]);
                    index = i;
                }
            }
            if(numFlippedCards % 2 == 0){
                if(index == oddClick){
                    numberOfClicks--;
                    return;
                }
                if(imageOnFront[index] != imageOnFront[oddClick]){
                    timer.start();
                }else{
                    buttonsArray[index].setEnabled(false);
                    buttonsArray[oddClick].setEnabled(false);
                    score++;
                    System.out.println("Match found!" + score);
                    if(score == 8){
                        JOptionPane.showMessageDialog(null,"You win!");
                        setGameOver(true);
                        setVisible(false);
                    }
                }
            }else{
                oddClick = index;
            }
        }
    }

    private class TimerAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonsArray[index].setIcon(imageOnBack);
            buttonsArray[oddClick].setIcon(imageOnBack);
            timer.stop();
        }
    }

    private static Icon reSizeIcon(ImageIcon picture, int width, int height){
        Image imgHolder = picture.getImage();
        Image resizedPicture = imgHolder.getScaledInstance(width,height,Image.SCALE_DEFAULT);
        return new ImageIcon(resizedPicture);
    }


    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public String[] getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(String[] imagePaths) {
        GamePanel.imagePaths = imagePaths;
    }

    public int getNumberOfButtons() {
        return numberOfButtons;
    }

    public void setNumberOfButtons(int numberOfButtons) {
        this.numberOfButtons = numberOfButtons;
    }

    public JButton[] getButtonsArray() {
        return buttonsArray;
    }

    public void setButtonsArray(JButton[] buttonsArray) {
        this.buttonsArray = buttonsArray;
    }

    public ImageIcon getImageOnBack() {
        return imageOnBack;
    }

    public void setImageOnBack(ImageIcon imageOnBack) {
        this.imageOnBack = imageOnBack;
    }

    public ImageIcon[] getImageOnFront() {
        return imageOnFront;
    }

    public void setImageOnFront(ImageIcon[] imageOnFront) {
        this.imageOnFront = imageOnFront;
    }

    public ImageIcon getTemp() {
        return temp;
    }

    public void setTemp(ImageIcon temp) {
        this.temp = temp;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getOddClick() {
        return oddClick;
    }

    public void setOddClick(int oddClick) {
        this.oddClick = oddClick;
    }

    public int getNumberOfClicks() {
        return numberOfClicks;
    }

    public void setNumberOfClicks(int numberOfClicks) {
        this.numberOfClicks = numberOfClicks;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getNumFlippedCards() {
        return numFlippedCards;
    }

    public void setNumFlippedCards(int numFlippedCards) {
        this.numFlippedCards = numFlippedCards;
    }
}
