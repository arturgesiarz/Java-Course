package agh.ics.oop.presenter;

import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Objects;

public class SimulationPresenter implements MapChangeListener { //bede uzywal tej klasy jako obserwatora
    @FXML
    private Label infoLabel; //narazie nie potrzebne
    private WorldMap map;
    public void setWorldMap(WorldMap map){
        this.map = map;
    }
    private void drawMap(String message){
        infoLabel.setText(message);
    }
    @Override
    public void mapChanged(WorldMap worldMap, String message){
        drawMap(message);

    }
}
