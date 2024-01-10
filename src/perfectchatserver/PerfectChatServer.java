/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package perfectchatserver;

/**
 *
 * @author 3ab9ar
 */
// PerfectChatServer.java
// PerfectChatServer.java
// PerfectChatServer.java
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class PerfectChatServer{

    /**
     * @param args the command line arguments
     */
public static void main(String[] args) {
        try {
            ChatServer chatServer = new ChatServer();
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            java.rmi.Naming.rebind("ChatServer", chatServer);
            System.out.println("ChatServer is running...");
        } catch (MalformedURLException | RemoteException e) {
        }
    }
}




