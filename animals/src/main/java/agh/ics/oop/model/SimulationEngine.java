package agh.ics.oop.model;
import agh.ics.oop.Simulation;
import java.util.*;

public class SimulationEngine{
    private final List<Simulation> simulationList;
    public SimulationEngine(List<Simulation> simulationList){
        this.simulationList = simulationList;
    }
    public void runSync(){
        simulationList.forEach(Simulation::run);
    }
    public void runAsync(){
        List<Thread> simulationTasks = new ArrayList<>(); //tworze liste ktora mi przechowa aktualne taski do wykonania ktore uruchomie rownolegle
        simulationList.forEach(simulation -> simulationTasks.add(new Thread(simulation)));
        simulationTasks.forEach(Thread::start);
        simulationTasks.forEach(thread -> { //aby moc uzyc tutaj lambdy zasotsowalem funkcje anonimowa aby zlapac wyjatek
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
