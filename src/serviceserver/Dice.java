/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceserver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

/**
 *
 * @author Jonathan
 */
public class Dice extends JComponent implements Serializable{
    private int diceValue;
    private int diceFaces;
    private static final int SPOT_DIAM = 18;
    private Font bigFont = new Font("Comic Sans", Font.BOLD, 24);
    
    public Dice(){
        setDiceFaces(6);
        setPreferredSize(new Dimension(130, 130));
        roll();
    }
    
    public void setDiceFaces(int faces){
        diceFaces = faces;
    }
    
    public int getValue(){
        return diceValue;
    }
    
    public void setValue(int val){
        diceValue = val;
        repaint();
    }
    
    public int roll(){
        int val = (int)(Math.random()* diceFaces + 1);
        setValue(val);
        return val;
    }
    
    //this method returns the graphical representation of the dices
    public void paintComponent(Graphics g){
        int w = getWidth();
        int h = getHeight();
        
        Graphics2D g2 = (Graphics2D)g;
        
        g2.setColor(Color.white);
        g2.fillRect(0, 0, w, h);
        g2.setColor(Color.black);
        
        g2.drawRect(0, 0, w-1, h-1);
        
        switch(diceValue){
            case 1: 
                drawSpot(g2, w/2, h/2);
                break;
            case 3:
                drawSpot(g2, w/2, h/2);
            case 2:
                drawSpot(g2, w/4, h/4);
                drawSpot(g2, 3*w/4, 3*h/4);
                break;
            case 5:
                drawSpot(g2, w/2, h/2);
            case 4:
                drawSpot(g2, w/4, h/4);
                drawSpot(g2, 3*w/4, 3*h/4);
                drawSpot(g2, 3*w/4, h/4);
                drawSpot(g2, w/4, 3*h/4);
                break;
            case 6:
                drawSpot(g2, w/4, h/4);
                drawSpot(g2, 3*w/4, 3*h/4);
                drawSpot(g2, 3*w/4, h/4);
                drawSpot(g2, w/4, 3*h/4);
                drawSpot(g2, w/4, h/2);
                drawSpot(g2, 3*w/4, h/2);
                break;
        }
    }
    
    private void drawSpot(Graphics2D g2, int x, int y){
        g2.fillOval(x-SPOT_DIAM/2, y-SPOT_DIAM/2, SPOT_DIAM, SPOT_DIAM);
    }
    
    Dice dice1;
    Dice dice2;
    Dice dice3;
    Dice dice4;
    Dice dice5;
    Dice dice6;
    public void setGui(){
        JFrame theFrame = new JFrame("Tester dice panel");
        theFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        theFrame.setSize(1000, 1500);
        JPanel panel = new JPanel();
        dice1 = new Dice();
        dice2 = new Dice();
        dice3 = new Dice();
        dice4 = new Dice();
        dice5 = new Dice();
        dice6 = new Dice();
        
        panel.add(dice1);
        panel.add(dice2);
        panel.add(dice3);
        panel.add(dice4);
        panel.add(dice5);
        panel.add(dice6);
        
        JButton rollButton = new JButton("Roll");
        rollButton.setFont(bigFont);
        rollButton.addActionListener(new rollListener());
        panel.add(rollButton);
        
        theFrame.getContentPane().add(panel);
        theFrame.setVisible(true);
    }
    
    public class rollListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            dice1.roll();
            dice2.roll();
            dice3.roll();
            dice4.roll();
            dice5.roll();
            dice6.roll();
            
        }
    }
    
    public static void main(String[] args){
        new Dice().setGui();
    }

}
