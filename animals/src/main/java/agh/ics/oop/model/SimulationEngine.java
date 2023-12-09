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
}
