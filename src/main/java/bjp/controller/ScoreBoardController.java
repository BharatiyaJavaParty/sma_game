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
    private TableColumn<ArrayList<String>, String> nameColumn, co2BudgetColumn, timeColumn, gemsColumn, timeStampColumn;

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
            nameColumn.setPrefWidth(tableWidth / 5);
            co2BudgetColumn.setPrefWidth(tableWidth / 5);
            timeColumn.setPrefWidth(tableWidth / 5);
            gemsColumn.setPrefWidth(tableWidth / 5);
            timeStampColumn.setPrefWidth(tableWidth / 5);
        });
    
        ArrayList<ArrayList<String>> data = getResults();
    
        System.out.println(data);
    
        // Set cell value factories to populate table columns
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        co2BudgetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        gemsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        timeStampColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));
    
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
            jsonObject.put("playerCO2Spent", GameEngine.newPlayer.getPlayerCo2Spent());
            jsonObject.put("playerTime", GameEngine.newPlayer.getPlayerTime());
            jsonObject.put("gems", gems);
            jsonObject.put("TimeStamp", String.format("%02d:%02d", hour, minute));
            arrayNode.add(jsonObject);

            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sortResults(arrayNode));
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
        ArrayNode arrayNode;
        File file = new File("Score.json");
        if (!file.exists()) {
            System.out.println("Score.json file not found");
            return res;
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            if(file.length() == 0)
            {
                arrayNode = mapper.createArrayNode();
            }
            else
            {
                arrayNode = (ArrayNode) mapper.readTree(file);
            }
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

    private static ArrayNode sortResults(ArrayNode arrayNode){
        for(int i=0; i<arrayNode.size();i++){
            //sorting according to time
            for(int j = i; j<arrayNode.size(); j++){
                if(arrayNode.get(i).get("playerCO2Budget").asInt()> arrayNode.get(j).get("playerCO2Budget").asInt()){
                    JsonNode temp;
                    temp = arrayNode.get(i);
                    arrayNode.set(i, arrayNode.get(j));
                    arrayNode.set(j, temp);
                }
                //if times are same then sorting by playerTime
                else if(arrayNode.get(i).get("playerCO2Budget").asInt()== arrayNode.get(j).get("playerCO2Budget").asInt()){
                    if(arrayNode.get(i).get("playerTime").asInt() > 
                        arrayNode.get(j).get("playerTime").asInt()){
                            JsonNode temp;
                            temp = arrayNode.get(i);
                            arrayNode.set(i, arrayNode.get(j));
                            arrayNode.set(j, temp);
                    }
                }
            }
        }
        System.out.println(arrayNode);
        return arrayNode;
    }
}