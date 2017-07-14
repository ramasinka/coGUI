package service;

import data.UrlData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by CodeAcademy on 2017.07.14.
 */
public class UrlService {

    public static final String PATH = "C:\\Users\\CodeAcademy\\IdeaProjects\\coGUI\\src\\resource\\";
    ObservableList<String> listItems = FXCollections.observableArrayList();

    public ObservableList<String> getUrlList(String fileName) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH + fileName));
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                UrlData urlData = new UrlData();
                urlData.setId(Integer.parseInt(split[0]));
                urlData.setUrl(split[1]);
                listItems.add(urlData.getUrl());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listItems;
    }

    public ObservableList<String> getListItems() {
        return listItems;
    }

    public void setListItems(ObservableList<String> listItems) {
        this.listItems = listItems;
    }

    @Override
    public String toString() {
        return "UrlService{" +
                "listItems=" + listItems +
                '}';
    }
}
