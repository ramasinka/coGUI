package chat;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by CodeAcademy on 2017.07.17.
 */
public class Client implements Runnable {

    PrintWriter clientToServer;
    BufferedReader serverToClientReader;
    Socket clientSocket;
    ObservableList<String> chatLog;
    String name;

    public Client(String host, int port, String name) {
        try {
            this.name = name;
            clientSocket = new Socket(host, port);
            serverToClientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            chatLog = FXCollections.observableArrayList();
            clientToServer.println("Name: " + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String inputFromServer = serverToClientReader.readLine();

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        chatLog.add(inputFromServer);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ObservableList<String> getChatLog() {
        return chatLog;
    }

    public void responseToServer(String text) {
        clientToServer.println(text);
    }
}
