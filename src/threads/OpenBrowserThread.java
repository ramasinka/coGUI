package threads;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.web.WebView;

import static java.lang.Thread.sleep;

/**
 * Created by CodeAcademy on 2017.07.14.
 */
public class OpenBrowserThread implements Runnable {
    ObservableList<String> list;
    WebView webView;

    public OpenBrowserThread(ObservableList<String> urlList, WebView webView) {
        this.webView = webView;
        this.list = urlList;
    }

    public synchronized void run() {
        for (int i = 0; i < list.size(); i++) {
            int finalI = i;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    webView.getEngine().load(list.get(finalI));
                }
            });
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
