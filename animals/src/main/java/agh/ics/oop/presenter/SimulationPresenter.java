package agh.ics.oop.presenter;

import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    private TextField argumentsField; //pobrana lista argumentow wpisana przez naszego uzytkownika
    @FXML
    private Label actualMove; //aktualny ruch
    @FXML
    private Label infoLabel;
    private WorldMap map;
    public void setWorldMap(WorldMap map){
        this.map = map;
    }
    private void drawMap(String message){
        actualMove.setText(message);
    }
    @Override
    public void mapChanged(WorldMap worldMap, String message){
        drawMap(message);

    }
    @FXML
    public void startSimulation(ActionEvent actionEvent) {
    }
    @FXML
    public void stopSimulation(ActionEvent actionEvent) {
    }
}
