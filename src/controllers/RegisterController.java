package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Created by CodeAcademy on 2017.07.10.
 */
public class RegisterController {

    @FXML
    TextField textName;

    @FXML
    TextField textSurname;

    @FXML
    TextField textCity;

    @FXML
    TextField textPhone;

    @FXML
    Button insertBtn;

    @FXML
    Button changeBtn;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private void handleInsertAction() {
        insertBtn.setOnAction((event) -> {
            outputTextArea.clear();
            outputTextArea.appendText("Name: " + textName.getText() + "\nSurname: "+ textSurname.getText() + "\nCity: " + textCity.getText() + "\nPhone: " + textPhone.getText());
        });
    }
    @FXML
    private void handleChangeAction() {
        changeBtn.setOnAction((event) -> {
            outputTextArea.appendText("change Action\n");
        });
    }

}
