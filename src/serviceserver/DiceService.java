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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jonathan
 */
class DiceService implements Service{
    
    JLabel label;
    JComboBox numOfDice;
    Font bigFont = new Font("Comic Sans", Font.BOLD, 30);

    
    public JPanel getGuiPanel(){
        JPanel panel = new JPanel();
        panel.setFont(bigFont);
        JButton button = new JButton("Roll 'em!");
        String[] choices = {"1", "2", "3", "4", "5"};
        numOfDice = new JComboBox(choices);
        label = new JLabel("dice values here");
        button.addActionListener(new RollEmListener());
        panel.add(numOfDice);
        panel.add(button);
        panel.add(label);
        return panel;
    }
    
    public class RollEmListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String diceOutput = "";
            String selection = (String) numOfDice.getSelectedItem();
            int numOfDiceToRoll = Integer.parseInt(selection);
            for(int i = 0; i< numOfDiceToRoll; i++){
                int r = (int) ((Math.random()*6)+1);
                diceOutput += (" " + r);
            }
            label.setText(diceOutput);
        }
    }
    
}
