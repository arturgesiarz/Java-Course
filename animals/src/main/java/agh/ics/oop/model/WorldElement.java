package agh.ics.oop.model;

public interface WorldElement {
    /**
     * Get our position from in world
     *
     * @return Position given by Vector2d class.
     */
    Vector2d getPosition();
    /**
     * Convert our string to implemented class
     *
     * @return String
     */
    String toString();
}
