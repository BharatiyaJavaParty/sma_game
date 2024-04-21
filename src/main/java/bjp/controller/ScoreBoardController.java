package bjp.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import bjp.utility.Player;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ScoreBoardController {

    @FXML
    private TableView<ArrayList<String>> scoreboardTable;

    @FXML
    private TableColumn<ArrayList<String>, String> name;

    @FXML
    private TableColumn<ArrayList<String>, String> co2Budget;

    @FXML
    private TableColumn<ArrayList<String>, String> time;

    @FXML
    private TableColumn<ArrayList<String>, String> gems;

    public void initialize() throws FileNotFoundException {
        // Assuming you have data in an ArrayList of ArrayList of Strings called 'data'
        ArrayList<ArrayList<String>> data = Player.getResults();

        System.out.println(data);

        // Set cell value factories to populate table columns
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        co2Budget.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        time.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        gems.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().get(3)));

        // Add data to the table
        scoreboardTable.getItems().addAll(data);
    }
}