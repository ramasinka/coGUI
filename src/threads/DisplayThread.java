package threads;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.web.WebView;

/**
 * Created by CodeAcademy on 2017.07.14.
 */
public class DisplayThread implements Runnable {

    ObservableList urlList;
    ListView<String> listView;
    WebView webView;


    public DisplayThread(ObservableList<String> urlList, ListView<String> list, WebView webView) {
        this.urlList = urlList;
        this.listView = list;
        this.webView = webView;
    }

    public synchronized void run() {
        ObservableList<String> urlToDisplay = FXCollections.observableArrayList();
        for (int i = 0; i < urlList.size(); i++) {
            int finalI = i;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    String url = String.valueOf(urlList.get(finalI));
                    urlToDisplay.add(url);
                    listView.setItems(urlToDisplay);
                }
            });
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}