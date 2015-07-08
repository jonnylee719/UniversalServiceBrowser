/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Jonathan
 */
public interface ServiceServer extends Remote{
    Object[] getServiceList() throws RemoteException;
    Service getService(Object serviceKey) throws RemoteException;
    
    
}
