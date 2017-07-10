package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;



/**
 * Created by CodeAcademy on 2017.07.10.
 */
public abstract class AnchorPaneBase extends AnchorPane {

    GridPane gridPane;

    TextArea outputTextArea;

    ColumnConstraints columnConstraints;
    ColumnConstraints columnConstraints0;
    ColumnConstraints columnConstraints1;
    ColumnConstraints columnConstraints2;

    RowConstraints rowConstraints;
    RowConstraints rowConstraints0;
    RowConstraints rowConstraints1;
    RowConstraints rowConstraints2;
    RowConstraints rowConstraints3;


    TextField textName;
    TextField textSurname;
    TextField textCity;
    TextField textPhone;

    Button insertBtn;
    Button changeBtn;

    public AnchorPaneBase() {
        gridPane = new GridPane();

        outputTextArea = new TextArea();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        columnConstraints2 = new ColumnConstraints();

        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        rowConstraints3 = new RowConstraints();

        textName = new TextField();
        textSurname = new TextField();
        textCity = new TextField();
        textPhone = new TextField();

        insertBtn = new Button();
        insertBtn.setOnAction(this::handleInsertAction);

        changeBtn = new Button();
        changeBtn.setOnAction(this::handleChangeAction);

        setPrefHeight(600);
        setPrefWidth(400);

        AnchorPane.setBottomAnchor(gridPane, 5.00);
        AnchorPane.setLeftAnchor(gridPane, 5.00);
        AnchorPane.setRightAnchor(gridPane, 5.00);
        AnchorPane.setTopAnchor(gridPane, 5.00);

        columnConstraints.setHgrow(Priority.SOMETIMES);
        columnConstraints.setMaxWidth(195);
        columnConstraints.setMinWidth(0);
        columnConstraints.setPrefWidth(21);

        columnConstraints0.setHgrow(Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(400);
        columnConstraints0.setMinWidth(10);
        columnConstraints0.setPrefWidth(227);

        columnConstraints1.setHgrow(Priority.SOMETIMES);
        columnConstraints1.setMaxWidth(361);
        columnConstraints1.setMinWidth(10);
        columnConstraints1.setPrefWidth(53);

        columnConstraints2.setHgrow(Priority.SOMETIMES);
        columnConstraints2.setMaxWidth(473);
        columnConstraints2.setMinWidth(10);
        columnConstraints2.setPrefWidth(457);


        rowConstraints.setVgrow(Priority.SOMETIMES);
        rowConstraints.setMinHeight(10);
        rowConstraints.setPrefHeight(30);

        rowConstraints0.setVgrow(Priority.SOMETIMES);
        rowConstraints0.setMinHeight(10);
        rowConstraints0.setPrefHeight(30);

        rowConstraints1.setVgrow(Priority.SOMETIMES);
        rowConstraints1.setMinHeight(10);
        rowConstraints1.setPrefHeight(30);

        rowConstraints2.setVgrow(Priority.SOMETIMES);
        rowConstraints2.setMinHeight(10);
        rowConstraints2.setPrefHeight(30);

        rowConstraints3.setVgrow(Priority.SOMETIMES);
        rowConstraints3.setMinHeight(10);
        rowConstraints3.setPrefHeight(30);


        GridPane.setColumnIndex(textName, 1);
        GridPane.setRowIndex(textName, 1);
        textName.setPromptText("Name");

        GridPane.setColumnIndex(textSurname, 1);
        GridPane.setRowIndex(textSurname, 2);
        textSurname.setPromptText("Surname");

        GridPane.setColumnIndex(textCity, 1);
        GridPane.setRowIndex(textCity, 3);
        textCity.setPromptText("City");

        GridPane.setColumnIndex(textPhone, 1);
        GridPane.setRowIndex(textPhone, 4);
        textPhone.setPromptText("Phone");


        GridPane.setColumnIndex(changeBtn, 2);
        GridPane.setRowIndex(changeBtn,4);

        GridPane.setColumnIndex(insertBtn, 2);
        GridPane.setRowIndex(insertBtn,3);


        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getColumnConstraints().add(columnConstraints2);

        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getRowConstraints().add(rowConstraints2);
        gridPane.getRowConstraints().add(rowConstraints3);

        gridPane.getChildren().add(textName);
        gridPane.getChildren().add(textSurname);
        gridPane.getChildren().add(textCity);
        gridPane.getChildren().add(textPhone);
        gridPane.getChildren().add(changeBtn);
        gridPane.getChildren().add(insertBtn);

        outputTextArea.setPrefHeight(200);
        outputTextArea.setPrefWidth(200);

        GridPane.setColumnIndex(outputTextArea, 3);
        GridPane.setRowIndex(outputTextArea, 3);

        gridPane.getChildren().add(outputTextArea);

        getChildren().add(gridPane);
    }

    abstract void handleInsertAction(ActionEvent actionEvent);

    abstract void handleChangeAction(ActionEvent actionEvent);
}
