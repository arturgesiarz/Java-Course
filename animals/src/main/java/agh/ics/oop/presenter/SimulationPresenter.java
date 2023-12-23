package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    private TextField argumentsField; //pobrana lista argumentow wpisana przez naszego uzytkownika
    @FXML
    private Label actualMove; //aktualny ruch
    @FXML
    private Label infoLabel; // wyswietlanie tytlow
    private WorldMap map;
    public void setWorldMap(WorldMap map){
        this.map = map;
    }
    private void drawMap(String message){
        actualMove.setText(message);
    }
    @Override
    public void mapChanged(WorldMap worldMap, String message){
        Platform.runLater(() -> drawMap(message)); //rysowanie mapy moze byc obslugiwane tylko przez jeden watek
    }
    @FXML
    public void onSimulationStartClicked() { //zaczyna sie nasza symulacja
        String args = argumentsField.getText(); //pozyskuje argumenty wpisane
        String[] argsTab = args.split(" "); //dziele mojego stringa na tablice stringow po spacji
        List<Simulation> simulationList = new ArrayList<>();

        List<MoveDirection> directions = OptionsParser.giveDirections(argsTab); //tlumacze moje wpisane argumenty na prawdziwe wartosci
        List<Vector2d> positions = List.of(new Vector2d(0,2), new Vector2d(5,5),new Vector2d(10,10), new Vector2d(3,7)); //tworze przykladowe miejsce moich zwierzat
        this.setWorldMap(new RectangularMap(15,15)); //tworze mape

        this.map.addObserver(this); //dodaje obserwatora
        Simulation simulation = new Simulation(positions, directions,this.map); //tworze przyklada symulacje
        simulationList.add(simulation);

        SimulationEngine simulationEngine = new SimulationEngine(simulationList);
        simulationEngine.runAsync(); //urucham asynchronicznie moja symulacje - nie dodaje join -> bo zawisi to watek graficzny i nic z tego nie dostane!
    }
    @FXML
    public void onSimulationStopClicked() {
    }
}
