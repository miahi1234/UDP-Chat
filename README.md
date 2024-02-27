# UDP-Chat
This application implements a chat system between a UDP server and multiple UDP clients. Here's an overview of how it works and the technologies involved:

1. **UDP Server**:
   - The UDP server listens on a specific port (in this case, port 12345) to receive messages from clients.
   - It uses a `DatagramSocket` to enable communication via the UDP protocol.
   - A list of client ports is maintained to keep track of clients connected to the server.
   - When it receives a message from a client, the server broadcasts it to all other clients except the original sender.

2. **UDP Client**:
   - Clients connect to the UDP server using the server's IP address and port.
   - After connection, the user can send messages to the server, which then distributes them to all connected clients.
   - Separate threads are used for both sending and receiving messages to allow for simultaneous sending and receiving.

Technologies Used:

- **Java (UDP Server)**:
  - It utilizes the `java.net.*` and `java.io.*` packages for data manipulation and socket handling.
  - `DatagramSocket` and `DatagramPacket` are used to receive and send messages via the UDP protocol.
  - A list is used to manage clients connected to the server.
- **Python (UDP Client)**:
  - The `socket` module is used to create and manage network connections.
  - The `threading` module is used to create and manage separate threads for simultaneous message sending and receiving.
  - To ensure communication, messages are encoded and decoded using UTF-8 encoding.

Together, these components enable the implementation of a simple chat system based on the UDP protocol, allowing users to send and receive messages in real-time.
