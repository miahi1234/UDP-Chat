import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UDPServer {
    public static void main(String[] args) {
        final int PORT = 12345;
        DatagramSocket socket = null;
        List<Integer> clienti = new ArrayList<>();

        try {
            socket = new DatagramSocket(PORT);
            System.out.println("Serverul UDP este pornit. Așteaptă mesaje...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                boolean exista = false;
                for (Integer port : clienti)
                    if (clientPort == port)
                        exista = true;

                if(!exista)
                    clienti.add(clientPort);

                System.out.println("Mesaj primit de la " + clientAddress + ":" + clientPort + " - " + clientMessage);

                for (Integer port : clienti) {
                    if (port != clientPort) {
                        String responseMessage = clientMessage;
                        byte[] responseData = responseMessage.getBytes();
                        DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, port);
                        socket.send(responsePacket);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

