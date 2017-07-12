package controllers;

import data.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import service.FileService;
import service.PersonService;

import java.net.URL;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by CodeAcademy on 2017.07.12.
 */
public class InformationController implements Initializable {


    @FXML
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person, String> id;

    @FXML
    private TableColumn<Person, String> first_name;

    @FXML
    private TableColumn<Person, String> last_name;

    @FXML
    private TableColumn<Person, String> email;

    @FXML
    private TableColumn<Person, String> gender;

    @FXML
    private TableColumn<Person, String> ip_address;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button insertBtn;

    @FXML
    private VBox vBox;

    @FXML
    private ListView<String> list_view;

    @FXML
    private TextField searchValue;

    @FXML
    private Button searchBtn;

    @FXML
    private TextArea displayText;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputLastName;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputGender;

    @FXML
    private TextField inputIpAddress;

    ObservableList<Person> personObservableList = FXCollections.observableArrayList();
    PersonService personService = new PersonService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileService fileService = new FileService();
        ObservableList<String> fileNames = fileService.getFileNames();
        list_view.setItems(fileNames);
    }

    @FXML
    public void handleMouseClick(MouseEvent event) {
        personObservableList = getPersonData();
        tableView.setItems(personObservableList);
        addPersonDataToTableColumn();

    }

    private ObservableList<Person> getPersonData() {
        List<Person> personData = personService.getPersonData(list_view.getSelectionModel().getSelectedItem());
        return FXCollections.observableArrayList(personData);
    }

    private void addPersonDataToTableColumn() {
        id.setCellValueFactory(new PropertyValueFactory<Person, String>("id"));
        first_name.setCellValueFactory(new PropertyValueFactory<Person, String>("first_name"));
        last_name.setCellValueFactory(new PropertyValueFactory<Person, String>("last_name"));
        email.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        gender.setCellValueFactory(new PropertyValueFactory<Person, String>("gender"));
        ip_address.setCellValueFactory(new PropertyValueFactory<Person, String>("ip_address"));
    }

    @FXML
    public void handleDeleteAction(ActionEvent event) {
        int selectedPersonIndex = tableView.getSelectionModel().getSelectedIndex();
        personObservableList.remove(selectedPersonIndex);
        personService.deleteDataFromFile(list_view.getSelectionModel().getSelectedItem(), selectedPersonIndex);
        //tableView.getItems().remove(selectedPersonIndex);
    }

    @FXML
    public void handleInsertAction(ActionEvent event) {
        Person person = new Person();
        int id = 1;

        Iterator it = personObservableList.iterator();
        while (it.hasNext()) {
            Person p = (Person) it.next();
            if (p.getId() == id) {
                id++;
            }
        }
        person.setId(id);
        person.setFirst_name(inputName.getText());
        person.setLast_name(inputLastName.getText());
        person.setEmail(inputEmail.getText());
        person.setGender(inputGender.getText());
        person.setIpAddress(inputIpAddress.getText());
        personObservableList.add(person);
        personService.writeToFile(person, list_view.getSelectionModel().getSelectedItem());
//        tableView.getItems().add(person);
    }

    @FXML
    public void handleSearchAction(ActionEvent event) {

    }

    Comparator<Person> comparator = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getId() - p2.getId();
        }
    };

}
