/*
 * File: GamePanel.java
 * Author: Anthony Smith
 * Date: 3/1/2020
 * Course: COP 5007
 * Purpose: This class is an extension of JPanel. It creats the game panel and contains all game and button logic for the memory game
 * */

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;

public class GamePanel extends JPanel{
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

    public void addButtons(){
        numberOfButtons = IMAGEPATHS.length*2;
        buttonsArray = new JButton[numberOfButtons];
        imageOnFront = new ImageIcon[numberOfButtons];

        for(int i = 0, j = 0; i < IMAGEPATHS.length; i++){
            imageOnFront[j] = new ImageIcon(this.getClass().getResource(IMAGEPATHS[i]));
            imageOnFront[j] = (ImageIcon) reSizeIcon(imageOnFront[j],200,200);
            j = makeButtons(j);
            imageOnFront[j] = imageOnFront[j-1];
            j = makeButtons(j);
        }
        Random random = new Random();
        for(int i = 0; i < numberOfButtons; i++){
            int j = random.nextInt(numberOfButtons);
            ImageIcon temp = imageOnFront[i];
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
            }
            numFlippedCards++;
            for(int i = 0; i < numberOfButtons; i++){
                if(e.getSource()==buttonsArray[i]){
                    buttonsArray[i].setIcon(imageOnFront[i]);
                    index = i;
                }
            }


            if(numFlippedCards % 2 == 0){
                if(index == oddClick){
                    numFlippedCards--;
                    return;
                }
                if(imageOnFront[index] != imageOnFront[oddClick]){
                    if(turnKeeper == true){
                        System.out.println("Player 2's turn");
                    }else{
                        System.out.println("Player 1's turn");
                    }
                    turnKeeper = !turnKeeper;
                    timer.start();
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

    private void disableButton() {
        buttonsArray[index].setEnabled(false);
        buttonsArray[oddClick].setEnabled(false);
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


    public void setGameOver(boolean gameOver) {
    }


}
