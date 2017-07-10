package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Tab tab1;

    @FXML
    private AnchorPane anch1;

    @FXML
    private Tab tab2;

    @FXML
    private AnchorPane anch2;

    @FXML
    private Tab tab3;

    @FXML
    private AnchorPane anch3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tab1.setContent(null);

        AnchorPaneBase anchorPaneBase = new AnchorPaneBase() {
            @Override
            void handleInsertAction(ActionEvent actionEvent) {
                outputTextArea.clear();
                outputTextArea.appendText("Test");
            }

            @Override
            void handleChangeAction(ActionEvent actionEvent) {

            }
        };
        tab1.setContent(anchorPaneBase);


    }
}
