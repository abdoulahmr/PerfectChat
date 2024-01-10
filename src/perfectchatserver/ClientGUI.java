package perfectchatserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientGUI extends JFrame {

    private ChatInterface server;
    private String username;
    private JTextArea chatTextArea;
    private JTextField messageTextField;

    public ClientGUI() {
        initComponents();
    }

    private void initComponents() {
        // Initial screen for entering the username
        JPanel usernamePanel = new JPanel();
        JTextField usernameField = new JTextField(15);
        JButton joinButton = new JButton("Join");

        usernamePanel.add(new JLabel("Enter your username: "));
        usernamePanel.add(usernameField);
        usernamePanel.add(joinButton);

        joinButton.addActionListener((ActionEvent e) -> {
            String enteredUsername = usernameField.getText().trim();
            username = enteredUsername.isEmpty() ? "anonymous" : enteredUsername;
            showChatScreen();
        });

        // Second screen for sending/receiving messages
        JPanel chatPanel = new JPanel();
        chatTextArea = new JTextArea(15, 40);
        JScrollPane scrollPane = new JScrollPane(chatTextArea);
        messageTextField = new JTextField(30);
        JButton sendButton = new JButton("Send");

        chatPanel.add(scrollPane);
        chatPanel.add(messageTextField);
        chatPanel.add(sendButton);

        sendButton.addActionListener((ActionEvent e) -> {
            String message = messageTextField.getText().trim();
            sendMessage(message);
            messageTextField.setText("");  // Clear the input field
            updateChat();  // Update the chat area after sending a message
        });

        // Add the initial screen to the frame
        setContentPane(usernamePanel);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showChatScreen() {
        try {
            server = (ChatInterface) Naming.lookup("rmi://localhost/ChatServer");
            server.registerClient(new PerfectChatClient(), username);

            // Switch to the chat screen
            setContentPane(getChatPanel());
            pack();
            setLocationRelativeTo(null);

            // Update the chat area initially
            updateChat();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel getChatPanel() {
        JPanel chatPanel = new JPanel();
        chatTextArea = new JTextArea(15, 40);
        JScrollPane scrollPane = new JScrollPane(chatTextArea);
        messageTextField = new JTextField(30);
        JButton sendButton = new JButton("Send");

        chatPanel.add(scrollPane);
        chatPanel.add(messageTextField);
        chatPanel.add(sendButton);

        sendButton.addActionListener((ActionEvent e) -> {
            String message = messageTextField.getText().trim();
            sendMessage(message);
            messageTextField.setText("");  // Clear the input field
            updateChat();  // Update the chat area after sending a message
        });

        return chatPanel;
    }

    private void sendMessage(String message) {
        try {
            server.sendMessage(username, message);
        } catch (RemoteException e) {
        }
    }

    private void updateChat() {
        SwingUtilities.invokeLater(() -> {
            // Directly append the received message to the chat area
            chatTextArea.append(server.receiveMessage() + "\n");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientGUI::new);
    }
}

