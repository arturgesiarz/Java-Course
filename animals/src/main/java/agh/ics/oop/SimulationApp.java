package agh.ics.oop;
import agh.ics.oop.model.*;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class SimulationApp extends Application {
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml")); //tu nastepuje pobieranie informacji z fxml
        BorderPane viewRoot = loader.load(); //tworze tutaj swoj widok
        SimulationPresenter presenter = loader.getController(); //tworze prezentera
        configureStage(primaryStage, viewRoot); //konfiguruje moj glowny widok w tym miejscu

        RectangularMap map = new RectangularMap(15,15); //tworze przyklada mape
        List<Vector2d> positions = List.of(new Vector2d(0,2), new Vector2d(5,5),new Vector2d(10,10), new Vector2d(3,7)); //tworze przykladowe miejsce moich zwierzat
        List<MoveDirection> directions = List.of(
                MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.BACKWARD,MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD,MoveDirection.FORWARD,
                MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.BACKWARD,MoveDirection.LEFT);

        map.addObserver(presenter); //dodaje obserwatora
        Simulation simulation = new Simulation(positions, directions,map); //tworze przyklada symulacje

        simulation.run();
        primaryStage.show();
    }
    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        Scene scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
