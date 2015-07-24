/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceserver;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jonathan
 */
class DayOfTheWeekService implements Service{

    JLabel outputLabel;
    JComboBox month;
    JTextField day;
    JTextField year;
    Font bigFont = new Font("Comic Sans", Font.BOLD, 30);
    
    public JPanel getGuiPanel(){
        JPanel panel = new JPanel();
        panel.setFont(bigFont);
        JButton button = new JButton("Do it!");
        button.setFont(bigFont);
        button.addActionListener(new DoItListener());
        outputLabel = new JLabel("data appears here");
        outputLabel.setFont(bigFont);
        DateFormatSymbols dateStuff = new DateFormatSymbols();
        month = new JComboBox(dateStuff.getMonths());
        month.setFont(bigFont);
        day = new JTextField(8);
        day.setFont(bigFont);
        year = new JTextField(8);
        year.setFont(bigFont);
        
        JPanel inputPanel = new JPanel(new GridLayout(3,2));
        JLabel mLabel = new JLabel("Month");
        mLabel.setFont(bigFont);
        inputPanel.add(mLabel);
        inputPanel.add(month);
        JLabel dLabel = new JLabel("Day");
        dLabel.setFont(bigFont);
        inputPanel.add(dLabel);
        inputPanel.add(day);
        JLabel yLabel = new JLabel("Year");
        yLabel.setFont(bigFont);
        inputPanel.add(yLabel);
        inputPanel.add(year);
        
        panel.add(inputPanel);
        panel.add(button);
        panel.add(outputLabel);
        return panel;
        
    }
    
    public class DoItListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            int monthNum = month.getSelectedIndex();
            int dayNum = Integer.parseInt(day.getText());
            int yearNum = Integer.parseInt(year.getText());
            Calendar c = Calendar.getInstance();
            c.set(Calendar.MONTH, monthNum);
            c.set(Calendar.DAY_OF_MONTH, dayNum);
            c.set(Calendar.YEAR, yearNum);
            Date date = c.getTime();
            String dayOfWeek = (new SimpleDateFormat("EEEE").format(date));
            outputLabel.setText(dayOfWeek);
        }
    }
}
