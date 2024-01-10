/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package perfectchatserver;

/**
 *
 * @author 3ab9ar
 */
// ChatInterface.java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatInterface extends Remote {
    void sendMessage(String username, String message) throws RemoteException;
    void registerClient(ChatInterface client, String username) throws RemoteException;
    void sendPrivateMessage(String sender, String receiver, String message) throws RemoteException;
    public String receiveMessage();
}
