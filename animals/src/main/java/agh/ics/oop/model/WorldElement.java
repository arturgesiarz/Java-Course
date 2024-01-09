package agh.ics.oop.model;

public interface WorldElement {
    /**
     * Get our position from the world
     *
     * @return Position given by Vector2d class.
     */
    Vector2d getPosition();
    /**
     * Convert our string
     *
     * @return String
     */
    String toString();
    String getFileName();
}
