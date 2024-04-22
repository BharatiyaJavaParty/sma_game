package bjp.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import bjp.utility.GameEngine;
import bjp.utility.Player;

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
    @FXML
    private TableColumn<ArrayList<String>, String> TimeStamp;
    


    public void initialize() throws FileNotFoundException {
        // Assuming you have data in an ArrayList of ArrayList of Strings called 'data'

        ArrayList<ArrayList<String>> data = getResults();

        System.out.println(data);

        // Set cell value factories to populate table columns
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        co2Budget.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        time.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        gems.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        TimeStamp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));
        // Add data to the table
        scoreboardTable.getItems().addAll(data);
    }

    public static void saveResults(int gems) {
        try {
            File file = new File("Score.json");
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode arrayNode;

            if (file.exists() && file.length() != 0) {
                arrayNode = (ArrayNode) mapper.readTree(file);
            } else {
                file.createNewFile();
                arrayNode = mapper.createArrayNode();
            }

            LocalTime currentTime = LocalTime.now();
            int hour = currentTime.getHour();
            int minute = currentTime.getMinute();
            ObjectNode jsonObject = mapper.createObjectNode();
            jsonObject.put("playerName", GameEngine.newPlayer.getPlayerName());
            jsonObject.put("playerCO2Budget", GameEngine.newPlayer.getPlayerCo2Budget());
            jsonObject.put("playerTime", GameEngine.newPlayer.getPlayerTime());
            jsonObject.put("gems", gems);
            jsonObject.put("TimeStamp", String.format("%02d:%02d", hour, minute));
            arrayNode.add(jsonObject);

            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(jsonString);
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file");
            e.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<String>> getResults() {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        File file = new File("Score.json");
        if (!file.exists()) {
            System.out.println("Score.json file not found");
            return res;
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            ArrayNode arrayNode = (ArrayNode) mapper.readTree(file);
            for (JsonNode rootNode : arrayNode) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(rootNode.get("playerName").asText());
                temp.add(String.valueOf(rootNode.get("playerCO2Budget").asInt()));
                temp.add(String.valueOf(rootNode.get("playerTime").asInt()));
                temp.add(String.valueOf(rootNode.get("gems").asInt()));
                temp.add(rootNode.get("TimeStamp").asText());
                res.add(temp);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the JSON file");
            e.printStackTrace();
        }
        return res;
    }
}