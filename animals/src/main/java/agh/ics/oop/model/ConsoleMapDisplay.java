package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener{
    private int updateCounter = 0;
    @Override
    synchronized public void mapChanged(WorldMap worldMap, String message) { //synchronized bo dla kazdej symulacji, chce aby po dodaniu/przesunieciu elementu wyswietlil sie ciag informacji nie przerywnie przez inne watki
        System.out.println("ID: " + worldMap.getId());
        System.out.println(message);
        System.out.println(worldMap);
        updateCounter++;
    }
    public int getUpdateCounter() {
        return updateCounter;
    }
}
