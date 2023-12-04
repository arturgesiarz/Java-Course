package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener{
    private static int updateCounter = 0;
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println(message);
        System.out.println(worldMap);
        updateCounter++;
    }
    public static int getUpdateCounter() {
        return updateCounter;
    }
}
