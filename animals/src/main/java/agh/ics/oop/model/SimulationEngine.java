package agh.ics.oop.model;
import agh.ics.oop.Simulation;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine{
    private final List<Simulation> simulationList;
    private final List<Thread> simulationTasks = new ArrayList<>();  //tworze liste ktora mi przechowa aktualne taski do wykonania ktore uruchomie rownolegle
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);  //tworzymy pule 4 watkow, ktora beda obslugiwane
    public SimulationEngine(List<Simulation> simulationList){
        this.simulationList = simulationList;
    }
    public synchronized void awaitSimulationsEnd(){

        simulationTasks.forEach(thread -> { //blokujemy wątek z, którego została wywołana aż do zakończenia wszystkich wątków
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown(); //konczymy dzialanie puli watkow
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS); //oczekujemy maksymlanie 10 sekund na jej zakonczenie
        } catch (InterruptedException e){
            e.printStackTrace();
            executorService.shutdownNow();
        }
    }
    public void runSync(){
        simulationList.forEach(Simulation::run);
    }
    public void runAsync(){
        simulationList.forEach(simulation -> simulationTasks.add(new Thread(simulation)));
        simulationTasks.forEach(Thread::start);
    }
    public void runAsyncInThreadPool(){
        simulationList.forEach(executorService::submit);
    }

}
