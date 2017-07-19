package chat;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
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
    boolean running;
    private String inpText;
    private String textToChat;

    public Client(String host, int port, String name) {
        try {
            this.running = false;
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
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(new FileReader("C:\\Users\\CodeAcademy\\IdeaProjects\\coGUI\\src\\resource\\DATAJSON.json"));


                JSONObject jsonObject = (JSONObject) obj;
                JSONArray jsonArray = (JSONArray) jsonObject.get("animals");

                if (inpText != null) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject object = (JSONObject) jsonArray.get(i);
                        String name = (String) object.get("first_name");
                        if (name.equals(inpText)) {
                            textToChat = (((JSONObject) jsonArray.get(i)).toJSONString());
                        }
                    }
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        chatLog.add(textToChat);
                    }
                });
                running = false;
                while (!this.running) {
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();

            }

        }
    }

    public void responseToServer(String text) {
        clientToServer.println(text);
    }

    public void setInpText(String inpText) {
        this.inpText = inpText;
    }

    public void setBolValue(boolean bolValue) {
        this.running = bolValue;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
