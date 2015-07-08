/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceserver;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import javax.swing.JFrame;

/**
 *
 * @author Jonathan
 */
public class ServiceServerImpl extends UnicastRemoteObject implements ServiceServer{
    
    HashMap serviceList;
    Service diceService;
    Service dayService;
    Service musicService;
    
    
    public ServiceServerImpl() throws RemoteException{
        setUpServices();
    }
    
    
    private void setUpServices(){
        serviceList = new HashMap();
        
        serviceList.put("Dice Rolling Service", new DiceService());
        serviceList.put("Day of the Week Service", new DayOfTheWeekService());
        serviceList.put("Visual Music Service", new MiniMusicService());
        
    }
    
    public Object[] getServiceList() {
        System.out.println("in remote");
        return serviceList.keySet().toArray();
    }

    public Service getService(Object serviceKey) throws RemoteException {
        System.out.println("reached getService()");
        Service theService = (Service) serviceList.get(serviceKey);
        return theService;
    }
    
    public static void main(String[] args){
        try{
            ServiceServer server = new ServiceServerImpl();
            Naming.rebind("ServiceServer", server);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        System.out.println("Remote service is running");
    }

}
