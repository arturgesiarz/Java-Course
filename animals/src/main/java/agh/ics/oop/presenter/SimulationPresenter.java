package agh.ics.oop.presenter;
import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimulationPresenter implements MapChangeListener {
    private static final double CELL_WIDTH = 30; //stala sluzaca do okreslenia szerokosci okienka
    private static final double CELL_HEIGHT = 30; //stala sluzaca do okreslenia wysokosci okienka
    @FXML
    private GridPane mapGrid;
    @FXML
    private TextField argumentsField; //pobrana lista argumentow wpisana przez naszego uzytkownika
    @FXML
    private Label actualMove; //aktualny ruch
    @FXML
    private Label infoLabel; // wyswietlanie tytlow -> oraz pozwala na zmianie tego
    private WorldMap map;
    public void setWorldMap(WorldMap map){
        this.map = map;
    }
    private void clearGrid() { //metoda czyszczaca aktualna mape
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }
    private void createRawGrid(int noRows, int noCols){

        for(int i = 0; i < noRows; i++){
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT)); //ustawianie wysokosci komorki
        }
        for(int i = 0; i < noCols; i++){
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH)); //ustawianie szeroskosci komorki
        }
    }

    private void addAxesGrid(int noRows, int noCols, Boundary currentBounds){ //metoda dodaje do grida naglowki osi czyli x/y 0 1 2.. itd
        Label labelToAdd;

        labelToAdd = new Label("y\\x");
        GridPane.setHalignment(labelToAdd, HPos.CENTER);
        mapGrid.add(labelToAdd,0,0);

        for(int i = 1; i < noCols; i ++){ //dodaje naglowki dla kolumn
            labelToAdd = new Label("" + (currentBounds.lowerLeftBoundary().getX() + i - 1));
            GridPane.setHalignment(labelToAdd, HPos.CENTER);
            mapGrid.add(labelToAdd,i,0);
        }

        for(int i = 1; i < noRows; i ++){ //dodaje naglowki dla wierszy
            labelToAdd = new Label("" + (currentBounds.lowerLeftBoundary().getY() + i - 1));
            GridPane.setHalignment(labelToAdd, HPos.CENTER);
            mapGrid.add(labelToAdd,0,i);
        }
    }
    private void complementsRestGrid(int noRows, int noCols, Boundary currentBounds){ //metoda uzupelnia reszte calego grida -> aktualizuje przemieszczanie sie
        Label labelToAdd;
        for(int i = 1; i < noRows; i ++){
            for(int j = 1; j < noCols; j ++){
                Vector2d actVector = new Vector2d(
                        currentBounds.lowerLeftBoundary().getX() + i - 1,
                        currentBounds.lowerLeftBoundary().getY() + j - 1
                );
                if(Objects.nonNull(map.objectAt(actVector))){
                    labelToAdd = new Label("" + map.objectAt(actVector));
                }
                else{
                    labelToAdd = new Label("");
                }
                GridPane.setHalignment(labelToAdd, HPos.CENTER);
                mapGrid.add(labelToAdd,j,i);
            }
        }
    }
    private void drawMap(String message){
        clearGrid(); //czyszcze aktualna siatke

        Boundary currentBounds = this.map.getCurrentBounds(); //pozyskuje granice mojej mapy

        int noRows = Math.abs(currentBounds.upperRightBoundary().getY() - currentBounds.lowerLeftBoundary().getY()) + 2;
        int noCols = Math.abs(currentBounds.upperRightBoundary().getX() - currentBounds.lowerLeftBoundary().getX()) + 2;

        createRawGrid(noRows,noCols);
        addAxesGrid(noRows,noCols,currentBounds);
        complementsRestGrid(noRows,noCols,currentBounds);

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
        this.setWorldMap(new GrassField(6));

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
