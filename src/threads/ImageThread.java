package threads;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by CodeAcademy on 2017.07.14.
 */
public class ImageThread implements Runnable {

    ObservableList<String> imageList;
    ImageView imageView;

    public ImageThread(ObservableList<String> imageList, ImageView imageView) {
        this.imageList = imageList;
        this.imageView = imageView;
    }

    @Override
    public void run() {
        for (int i = 0; i < imageList.size(); i++) {
            int finalI = i;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //TODO
            imageView.setImage(new Image(imageList.get(finalI)));
            //imageView.
        }
    }
}
