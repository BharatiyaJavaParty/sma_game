package bjp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import bjp.Main;
import bjp.utility.GameEngine;

public class ScoreBoardController {

    @FXML
    private Button homeButton;

    @FXML
    private VBox rootVBox;

    @FXML
    private TableView<ArrayList<String>> scoreboardTable;
    
    @FXML
    private TableColumn<ArrayList<String>, String> nameColumn, co2SpentColumn, timeColumn, gemsColumn, timeStampColumn;

    @FXML
    public void homeButtonClicked() {
       try {
          Main.setRoot("launch-view");
       } catch (Exception e) {
          e.printStackTrace();
       }
    }
    
    @FXML
    public void initialize() throws FileNotFoundException {

        scoreboardTable.widthProperty().addListener((obs, oldVal, newVal) -> {
            double tableWidth = newVal.doubleValue();
            nameColumn.setMinWidth(tableWidth / 5);
            co2SpentColumn.setMinWidth(tableWidth / 5);
            timeColumn.setMinWidth(tableWidth / 5);
            gemsColumn.setMinWidth(tableWidth / 5);
            timeStampColumn.setMinWidth(tableWidth / 5);
        });
    
        ArrayList<ArrayList<String>> data = getResults();
    
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        co2SpentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        gemsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        timeStampColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));
    
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
            ObjectNode jsonObject = mapper.createObjectNode();
            jsonObject.put("playerName", GameEngine.newPlayer.getPlayerName());
            jsonObject.put("playerCO2Spent", GameEngine.newPlayer.getPlayerCo2Spent());
            jsonObject.put("playerTime", GameEngine.newPlayer.getPlayerTime());
            jsonObject.put("gems", gems);
            jsonObject.put("TimeStamp", currentTime.getHour() + ":" + currentTime.getMinute());
            arrayNode.add(jsonObject);
    
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
            bw.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode));
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
            ArrayNode arrayNode;
            if (file.length() == 0) {
                arrayNode = mapper.createArrayNode();
            } else {
                arrayNode = (ArrayNode) mapper.readTree(file);
            }
            for (JsonNode rootNode : arrayNode) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(rootNode.get("playerName").asText());
                temp.add(String.valueOf(rootNode.get("playerCO2Spent").asInt()));
                temp.add(String.valueOf(rootNode.get("playerTime").asInt()));
                temp.add(String.valueOf(rootNode.get("gems").asInt()));
                temp.add(rootNode.get("TimeStamp").asText());
                res.add(temp);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the JSON file");
            e.printStackTrace();
            return res;
        }
        Collections.sort(res, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                int gemsResult = Integer.compare(Integer.parseInt(o2.get(3)), Integer.parseInt(o1.get(3)));
                if (gemsResult != 0) return gemsResult;

                int co2Result = Integer.compare(Integer.parseInt(o1.get(1)), Integer.parseInt(o2.get(1)));
                if (co2Result != 0) return co2Result;

                return Integer.compare(Integer.parseInt(o1.get(2)), Integer.parseInt(o2.get(2)));
            }
        });

        return res;
    }
}