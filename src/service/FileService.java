package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;

/**
 * Created by CodeAcademy on 2017.07.12.
 */
public class FileService {
     ObservableList<String> listItems = FXCollections.observableArrayList("List files");
    public ObservableList<String> getFileNames() {
        File folder = new File("C:\\Users\\CodeAcademy\\IdeaProjects\\coGUI\\src\\resource");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(".csv")) {
                listItems.add(listOfFiles[i].getName());
            }
        }
        return listItems;
    }
}
