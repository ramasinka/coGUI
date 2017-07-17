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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeAcademy on 2017.07.17.
 */
public class ServerApp extends Application {
    static List<Thread> threadsList;

    @Override
    public void start(Stage primaryStage) throws Exception {
        threadsList = new ArrayList<>();
        primaryStage.setTitle("Server");
        primaryStage.setScene(makePortGUI(primaryStage));
        primaryStage.show();
    }

    private Scene makePortGUI(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.CENTER);

        Text text = new Text("PORT");
        TextField portTextField = new TextField();
        Button portButton = new Button();

        portButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Server server = new Server(Integer.parseInt(portTextField.getText()));
                Thread serverThread = new Thread(server);
                serverThread.setDaemon(true);
                serverThread.start();
                threadsList.add(serverThread);

                primaryStage.setScene(makeChatGUI(server));
            }
        });

        gridPane.add(portTextField, 0, 0);
        gridPane.add(text, 1, 0);
        gridPane.add(portButton, 1, 1);

        return new Scene(gridPane);
    }

    private Scene makeChatGUI(Server server) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Label informationLabel = new Label("server chat");

        ListView<String> chatListView = new ListView();
        chatListView.setItems(server.logList);

        ListView<String> clientListView = new ListView<>();
        clientListView.setItems(server.clientsNames);

        gridPane.add(informationLabel, 0, 0);
        gridPane.add(chatListView, 0,1 );
        gridPane.add(clientListView, 0, 2);

        return new Scene(gridPane);
    }
}
