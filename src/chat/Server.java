package chat;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeAcademy on 2017.07.17.
 */
public class Server implements Runnable {
    int port;
    ObservableList<String> logList;
    ObservableList<String> clientsNames;
    List<Socket> clients;
    List<ClientThreads> clientThreads;
    ServerSocket socket;

    public Server(int port) {
        this.port = port;
        logList = FXCollections.observableArrayList();
        clientsNames = FXCollections.observableArrayList();
        clients = new ArrayList<>();

        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientThreads = new ArrayList<>();

    }

    @Override
    public void run() {
        ClientThreads currentClient = null;
        while (true) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    logList.add("Server start");
                }
            });
            try {
                Socket clientSocket = socket.accept();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        logList.add("Client: " + clientSocket.getRemoteSocketAddress());
                    }
                });

                currentClient = new ClientThreads(clientSocket, this);
                Thread currentClientThread = new Thread(currentClient);
                clientThreads.add(currentClient);
                currentClientThread.setDaemon(true);
                currentClientThread.start();
                ServerApp.threadsList.add(currentClientThread);
            } catch (IOException e) {
                e.printStackTrace();
                closeConnections(currentClient);
            }
        }
    }

    public void writeToClients(String inputToServer) {
        for (ClientThreads clientThreads : clientThreads) {
            clientThreads.writeToServer(inputToServer);
        }
    }

    public void closeConnections(ClientThreads client) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("client exit: " + client.clientSocket.getRemoteSocketAddress());
                clients.remove(clientThreads.indexOf(client));
                clientsNames.remove(clientThreads.indexOf(client));
                clientThreads.remove(clientThreads.indexOf(client));
            }
        });
    }
}
