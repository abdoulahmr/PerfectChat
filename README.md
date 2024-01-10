# PerfectChat
# Java RMI Chat Application

This is a simple chat application implemented in Java using RMI (Remote Method Invocation). It consists of a server (`ChatServer`) and a client (`ChatClientImpl`). The server manages the chat and communicates with the clients using RMI.

## Features

- **Server-Side:**
  - Handles client registration and unregistration.
  - Supports broadcasting messages to all clients.
  - Allows sending private messages between clients.

- **Client-Side (`ChatClientImpl`):**
  - Provides a GUI for sending and receiving messages.
  - Differentiates messages with colors based on the sender.
  - Supports private messaging with a "/dm" command.
  - Allows leaving the chat with a "/leave" command.

## How to Run

1. **Server:**
   - Run `ChatPat` class located in the `chatpat` package.

2. **Client:**
   - Run `ChatClientImpl` class located in the `chatpat` package.
   - Enter your desired username when prompted.

## Dependencies

- Java RMI (part of the Java Standard Edition library).

## Usage

- Start the server and multiple clients.
- Clients can send messages that are broadcasted to all connected clients.
- Clients can use "/dm user message" to send a private message to a specific user.
- Clients can use "/leave" to leave the chat.

## Contributors

- LAHMAR Abdessalem

## License

This project is licensed under the [License Name] - see the [LICENSE.md](LICENSE.md) file for details.
