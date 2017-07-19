package chat;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by CodeAcademy on 2017.07.17.
 */
public class ClientApp extends Application {
    ArrayList<Thread> listThreads;

    @Override
    public void start(Stage primaryStage) throws Exception {
        listThreads = new ArrayList<>();
        primaryStage.setTitle("client register windows");
        primaryStage.setScene(getGridPane(primaryStage));
        primaryStage.show();
    }

    private Scene getGridPane(Stage primaryStage) {
        GridPane gridPaneRoot = new GridPane();
        gridPaneRoot.setPadding(new Insets(10));
        gridPaneRoot.setVgap(10);
        gridPaneRoot.setHgap(10);
        gridPaneRoot.setAlignment(Pos.CENTER);

        TextField nameField = new TextField("Test");
        TextField hostField = new TextField("localhost");
        TextField portField = new TextField("1000");
        ListView listView = new ListView();

        Label errorLabel = new Label();

        Button btnSubmit = new Button("submit");

        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Client client = new Client(hostField.getText(), Integer.parseInt(portField.getText()), nameField.getText());
                Thread clientThread = new Thread(client);
                primaryStage.setScene(getChatUI(client));
                clientThread.setDaemon(true);
                clientThread.start();
                listThreads.add(clientThread);
            }
        });

        gridPaneRoot.add(nameField, 1, 1);
        gridPaneRoot.add(hostField, 1, 2);
        gridPaneRoot.add(portField, 1, 3);
        gridPaneRoot.add(btnSubmit, 1, 4);
        gridPaneRoot.add(errorLabel, 2, 4);
        gridPaneRoot.add(listView, 3, 4);
        Scene scene = new Scene(gridPaneRoot);
        return scene;
    }

    public Scene getChatUI(Client client) {
        GridPane gridPaneRoot2 = new GridPane();
        gridPaneRoot2.setPadding(new Insets(20));
        gridPaneRoot2.setAlignment(Pos.CENTER);
        gridPaneRoot2.setVgap(10);
        gridPaneRoot2.setHgap(10);

        ListView<String> chatListView = new ListView<>();
        chatListView.setItems(client.chatLog);

        TextField chatText = new TextField();
        chatText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                client.responseToServer(chatText.getText());
                client.setInpText(chatText.getText());
                if(!client.isRunning()){
                    client.setRunning(true);
                }
            }
        });
        gridPaneRoot2.add(chatListView, 1, 1);
        gridPaneRoot2.add(chatText, 1, 2);
        return new Scene(gridPaneRoot2);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
