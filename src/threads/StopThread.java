package threads;

import javafx.application.Platform;

/**
 * Created by CodeAcademy on 2017.07.14.
 */
public class StopThread implements Runnable {

    Thread displayThread;
    Thread openBrowserThread;

    public StopThread(Thread displayThread, Thread openBrowserThread) {
        this.displayThread = displayThread;
        this.openBrowserThread = openBrowserThread;
    }

    @Override
    public synchronized void run() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                displayThread.stop();
                openBrowserThread.stop();
            }
        });
    }
}
