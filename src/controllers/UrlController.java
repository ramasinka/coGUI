package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import service.UrlService;
import threads.DisplayThread;
import threads.OpenBrowserThread;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by CodeAcademy on 2017.07.14.
 */
public class UrlController implements Initializable {

    @FXML
    private ListView<String> urlList;

    @FXML
    private Button startBtn;

    @FXML
    private Button stopBtn;

    @FXML
    private WebView webView;

    @FXML
    private TextArea textAreaDisplay;

    UrlService urlService = new UrlService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     /*   ObservableList<String> urlData = urlService.getUrlList("URL.csv");
        ObservableList<String> urls = FXCollections.observableArrayList();
        for (int i = 0; i < urlData.size(); i++) {
            urls.add(urlData.get(i));
            ReadThread readThread = new ReadThread();
            readThread.start();
        }
        urlList.setItems(urls);*/
    }

    @FXML
    void handleStartAction(ActionEvent event) {
        ObservableList<String> urlList = urlService.getUrlList("URL.csv");
        Thread displayThread = new Thread(new DisplayThread(urlList, this.urlList, webView));
        displayThread.setDaemon(true);
        displayThread.start();


        Thread openBrowserThread = new Thread(new OpenBrowserThread(urlList, webView));
        openBrowserThread.setDaemon(true);
        openBrowserThread.start();

        textAreaDisplay.clear();
        stopBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                displayThread.stop();
                openBrowserThread.stop();
                textAreaDisplay.setText("Threads display");
            }
        });
    }

    @FXML
    void handleStopAction(ActionEvent event) {

    }

    @FXML
    void redirectToUrl(MouseEvent event) {
    }
}
