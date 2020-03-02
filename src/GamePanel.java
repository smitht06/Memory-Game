/*
 * File: GamePanel.java
 * Author: Anthony Smith
 * Date: 3/1/2020
 * Course: COP 5007
 * Purpose: This class is an extension of JPanel. It creates the game panel and contains all game and button logic for the memory game
 * */

//imports
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;

public class GamePanel extends JPanel{
    //declare all global variables including two string with the image file paths
    private static final String[] IMAGEPATHS = {"images/bulbasaur.png","images/butterfree.png","images/dragonite.png","images/Eevee.png",
            "images/Meowth.png","images/nidoran.png","images/pikachu.png","images/wartortle.png"} ;
    private static final String BACKIMAGEPATH = "images/back.jpg";
    private int numberOfButtons;
    private JButton[] buttonsArray;
    private ImageIcon imageOnBack;
    private ImageIcon[] imageOnFront;
    private int index = 0;
    private int oddClick = 0;
    private Timer timer;
    private int numFlippedCards;
    private int playerOnesScore = 0;
    private int playerTwosScore = 0;
    private boolean turnKeeper;

    //construct the gameboard that will hold the buttons and pictures
    public GamePanel(){
        setLayout(new GridLayout(0,4,0,0));
        imageOnBack = new ImageIcon(this.getClass().getResource(BACKIMAGEPATH));
        imageOnBack = (ImageIcon) reSizeIcon(imageOnBack,200,200);
        setBackground(Color.BLUE);
        setVisible(true);
        addButtons();
    }

    //method to add buttons to the Jframe
    public void addButtons(){
        //set number of buttons to the number of pictures times 2
        //populate arrays with picture and button objects
        numberOfButtons = IMAGEPATHS.length*2;
        buttonsArray = new JButton[numberOfButtons];
        imageOnFront = new ImageIcon[numberOfButtons];

        //for loop assigns images to array and calls the make buttons method
        for(int i = 0, j = 0; i < IMAGEPATHS.length; i++){
            imageOnFront[j] = new ImageIcon(this.getClass().getResource(IMAGEPATHS[i]));
            imageOnFront[j] = (ImageIcon) reSizeIcon(imageOnFront[j],200,200);
            j = makeButtons(j);
            imageOnFront[j] = imageOnFront[j-1];
            j = makeButtons(j);
        }
        //randomizer randomizes image placements
        Random random = new Random();
        for(int i = 0; i < numberOfButtons; i++){
            int j = random.nextInt(numberOfButtons);
            ImageIcon temp = imageOnFront[i];
            imageOnFront[i] = imageOnFront[j];
            imageOnFront[j] = temp;
        }
        timer = new Timer(1000, new TimerAction());
    }
    //method to make buttons, also adds action listener to button
    //sets image on the back of the card
    private int makeButtons(int j){
        buttonsArray[j] = new JButton();
        buttonsArray[j].addActionListener(new GamePanel.ButtonListener());
        buttonsArray[j].setIcon(imageOnBack);
        add(buttonsArray[j++]);
        return j;
    }

    //listener for buttons
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(timer.isRunning()){
                return;
            }
            numFlippedCards++;
            for(int i = 0; i < numberOfButtons; i++){
                if(e.getSource()==buttonsArray[i]){
                    buttonsArray[i].setIcon(imageOnFront[i]);
                    index = i;
                }
            }

            //dont allow same card to be clicked twice
            if(numFlippedCards % 2 == 0){
                if(index == oddClick){
                    numFlippedCards--;
                    return;
                }
                if(imageOnFront[index] != imageOnFront[oddClick]){
                    //two player game, keeps track of turn
                    if(turnKeeper == true){
                        System.out.println("Player 2's turn");
                    }else{
                        System.out.println("Player 1's turn");
                    }
                    turnKeeper = !turnKeeper;
                    timer.start();
                    //player 1 score keeper and tracks winner
                }else if(!turnKeeper){
                    disableButton();
                    playerTwosScore++;
                    turnKeeper = true;
                    System.out.println("Player 2's score: "+playerTwosScore);
                    if(playerTwosScore == 8){
                        JOptionPane.showMessageDialog(null,"You win!");
                        setGameOver(true);
                        setVisible(false);
                    }
                    //player 1 score keeper and tracks winner
                }else if(turnKeeper){
                    disableButton();
                    playerOnesScore++;

                    System.out.println("Player 1's score: "+playerOnesScore);
                    turnKeeper = false;
                    if(playerOnesScore == 8){
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

    //method disables buttons that a player has found matches for
    private void disableButton() {
        buttonsArray[index].setEnabled(false);
        buttonsArray[oddClick].setEnabled(false);
    }

    //timer lister to wait a few seconds befor ecards flip
    private class TimerAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonsArray[index].setIcon(imageOnBack);
            buttonsArray[oddClick].setIcon(imageOnBack);
            timer.stop();
        }
    }

    //method to resize photos
    private static Icon reSizeIcon(ImageIcon picture, int width, int height){
        Image imgHolder = picture.getImage();
        Image resizedPicture = imgHolder.getScaledInstance(width,height,Image.SCALE_DEFAULT);
        return new ImageIcon(resizedPicture);
    }

    //setter method to tell when the game is over
    public void setGameOver(boolean gameOver) {
    }


}
