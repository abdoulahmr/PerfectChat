/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perfectchatserver;

/**
 *
 * @author abdou
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ChatServer extends UnicastRemoteObject implements ChatInterface {
    private final Map<ChatInterface, String> clients;

    protected ChatServer() throws RemoteException {
        clients = new HashMap<>();
    }

    @Override
    public void registerClient(ChatInterface client, String username) throws RemoteException {
        clients.put(client, username);
        broadcastMessage(username + " has joined the chat.");
    }

    @Override
    public void sendMessage(String username, String message) throws RemoteException {
        String formattedMessage = username + ": " + message;
        System.out.println("Received message: " + formattedMessage);
        broadcastMessage(formattedMessage);
    }

    @Override
    public void sendPrivateMessage(String sender, String receiver, String message) throws RemoteException {
        String formattedMessage = "(Private from " + sender + "): " + message;
        for (Map.Entry<ChatInterface, String> entry : clients.entrySet()) {
            if (entry.getValue().equals(receiver)) {
                entry.getKey().sendMessage(sender, formattedMessage);
                break;
            }
        }
    }
    private void broadcastMessage(String message) {
        for (Map.Entry<ChatInterface, String> entry : clients.entrySet()) {
            try {
                entry.getKey().sendMessage(entry.getValue(), message);
            } catch (RemoteException e) {
                // Handle RemoteException (e.g., remove the disconnected client)

            }
        }
    }

    @Override
    public String receiveMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
