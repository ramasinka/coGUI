package chat;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by CodeAcademy on 2017.07.17.
 */
public class ClientThreads implements Runnable {

    Socket clientSocket;
    Server server;
    BufferedReader incomingMessageReader;
    PrintWriter outMessageWriter;
    String clientName;


    public ClientThreads(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
        try {
            incomingMessageReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outMessageWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                server.clientsNames.add("Client name - " + clientSocket.getRemoteSocketAddress());
            }
        });
        String inputToServer;
        while (true) {
            try {
                inputToServer = incomingMessageReader.readLine();
                server.writeToClients(inputToServer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToServer(String inputToServer) {
        outMessageWriter.println(inputToServer);
    }

    public String getClientName() {
        String name = null;
        try {
            name = incomingMessageReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }
}
