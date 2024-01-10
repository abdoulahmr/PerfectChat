/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package perfectchatserver;

/**
 *
 * @author 3ab9ar
 */
// PerfectChatClient.java
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class PerfectChatClient extends UnicastRemoteObject implements ChatInterface{
       private String username;

    protected PerfectChatClient() throws RemoteException {
    }
    @Override
    public void sendMessage(String sender, String message) throws RemoteException {
        System.out.println(message);
    }

    @Override
    public void registerClient(ChatInterface client, String username) throws RemoteException {
        this.username = username;
        System.out.println("You have joined the chat as '" + username + "'.");
    }

    @Override
    public void sendPrivateMessage(String sender, String receiver, String message) throws RemoteException {
        System.out.println(message);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            PerfectChatClient chatClient = new PerfectChatClient();
            ChatInterface server = (ChatInterface) Naming.lookup("rmi://localhost/ChatServer");
            server.registerClient(chatClient, username);

            System.out.println("Enter your messages (type '/dm [username] [message]' for private messages, type 'exit' to quit):");

            while (true) {
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Chat client is exiting.");
                    System.exit(0);
                } else if (input.startsWith("/dm")) {
                    String[] tokens = input.split(" ");
                    if (tokens.length >= 3) {
                        String receiver = tokens[1];
                        String message = input.substring(tokens[0].length() + tokens[1].length() + 2);
                        server.sendPrivateMessage(username, receiver, message);
                    } else {
                        System.out.println("Invalid /dm command. Usage: /dm [username] [message]");
                    }
                } else {
                    server.sendMessage(username, input);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receiveMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    @Override
    public String receiveMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
