/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceserver;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jonathan
 */
class DiceService implements Service{
    
    JLabel diceValue;
    JLabel totalLabel;
    JPanel dicePanel;
    JComboBox numOfDice;
    Font bigFont = new Font("Comic Sans", Font.BOLD, 30);
    
    int diceNum;
    
    //creating all the dices
    ArrayList <Dice> diceList = new ArrayList() ;
    
    public JPanel getGuiPanel(){
        JPanel panel = new JPanel();
        JPanel componentPanel = new JPanel(); // for the total value of all the dices
        JPanel mainPanel = new JPanel(); //for the entire 3 panels
        
        JButton button = new JButton("Roll 'em!");
        String[] choices = {"1", "2", "3", "4", "5"};
        numOfDice = new JComboBox(choices);
        numOfDice.addActionListener(new DiceNumListener());
        diceValue = new JLabel("dice values here");
        button.addActionListener(new RollEmListener());
        
        totalLabel = new JLabel("|Total dice value: |");
        totalLabel.setFont(bigFont);
        
        button.setFont(bigFont);
        numOfDice.setFont(bigFont);
        diceValue.setFont(bigFont);
        
        dicePanel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        componentPanel.add(numOfDice);
        componentPanel.add(button);
        componentPanel.add(diceValue);
        componentPanel.add(totalLabel);
        
        panel.add(componentPanel);
        panel.add(dicePanel);
        
        for(int i = 0; i < 6; i++){
            diceList.add(new Dice());
        }
        
        return panel;
    }
    
    public class DiceNumListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            diceNum = numOfDice.getSelectedIndex()+1;
            System.out.println(diceNum);
            
            
            //dicePanel delete all items first
            dicePanel.removeAll();
            
            switch(diceNum){
                case 1:
                    dicePanel.add(diceList.get(0));
                    break;
                case 2:
                    dicePanel.add(diceList.get(0));
                    dicePanel.add(diceList.get(1));
                    break;
                case 3:
                    dicePanel.add(diceList.get(0));
                    dicePanel.add(diceList.get(1));
                    dicePanel.add(diceList.get(2));
                    break;
                case 4:
                    dicePanel.add(diceList.get(0));
                    dicePanel.add(diceList.get(1));
                    dicePanel.add(diceList.get(2));
                    dicePanel.add(diceList.get(3));
                    break;
                case 5:
                    dicePanel.add(diceList.get(0));
                    dicePanel.add(diceList.get(1));
                    dicePanel.add(diceList.get(2));
                    dicePanel.add(diceList.get(3));
                    dicePanel.add(diceList.get(4));
                    break;
                case 6:
                    dicePanel.add(diceList.get(0));
                    dicePanel.add(diceList.get(1));
                    dicePanel.add(diceList.get(2));
                    dicePanel.add(diceList.get(3));
                    dicePanel.add(diceList.get(4));
                    dicePanel.add(diceList.get(5));
                    break;
            }
        }
    }
    
    public class RollEmListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String diceOutput = "";
            int result = 0;
            System.out.println(diceNum);
            
            
            for (int i = 0; i < diceNum; i++){
                Dice die = diceList.get(i);
                int dieResult = die.roll();
                diceOutput += ("[" + dieResult + "] ");
                result = result + dieResult;
                
            }
            diceValue.setText(diceOutput);
            totalLabel.setText("|Total dice value: " + result + " | ");
        }
    }
    
}
