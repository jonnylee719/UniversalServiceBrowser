/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceBrowser;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import serviceserver.Service;
import serviceserver.Service;
import serviceserver.ServiceServer;
import serviceserver.ServiceServer;
import serviceserver.ServiceServer;

/**
 *
 * @author Jonathan
 */
public class ServiceBrowser {

    JPanel mainPanel;
    JComboBox serviceList;
    ServiceServer server;
    Font bigFont = new Font("Comic Sans", Font.BOLD, 30);

    
    public void buildGUI(){
        JFrame frame = new JFrame("RMI Browser");
        mainPanel = new JPanel();
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Object[] services = getServicesList();  
        
        
        serviceList = new JComboBox(services);
        serviceList.setFont(bigFont);
        
        frame.getContentPane().add(BorderLayout.NORTH, serviceList);
        serviceList.addActionListener(new MyListListener());
        

        frame.setSize(1000, 500);
        frame.setVisible(true);
    }
    
    void loadService(Object serviceSelection){
        try{
            Service svc = server.getService(serviceSelection);
            
            mainPanel.removeAll();
            mainPanel.add(svc.getGuiPanel());
            mainPanel.validate();
            mainPanel.repaint();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    Object[] getServicesList(){
        Object[] services = null;
        Object obj = null;
        
        try {
            server = (ServiceServer) Naming.lookup("rmi://127.0.0.1/ServiceServer");
            services = server.getServiceList();
        
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return services;
        }
  
    class MyListListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Object selection = serviceList.getSelectedItem();
            System.out.println(selection);
            loadService(selection);
        }
    }
    
    public static void main(String[] args) {
        new ServiceBrowser().buildGUI();
    }
    
    
    
}
