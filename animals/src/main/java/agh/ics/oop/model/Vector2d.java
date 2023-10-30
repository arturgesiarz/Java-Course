package agh.ics.oop.model;

import java.util.Objects;

public class Vector2d {
    private final int x;
    private final int y;
    public Vector2d(int x,int y){
        this.x=x;
        this.y=y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String toString(){
        return "("+x+","+y+")";
    }
    boolean precedes(Vector2d other){
        return other.x>=this.x && other.y>=this.y;
    }
    boolean follows(Vector2d other){
        return other.x<=this.x && other.y<=this.y;
    }
    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x,this.y+other.y);
    }
    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x-other.x,this.y-other.y);
    }
    public Vector2d upperRight(Vector2d other){
        if(precedes(other)) return other;
        if(follows(other)) return this;
        return new Vector2d(Math.max(other.x, this.x),Math.max(other.y, this.y));
    }
    public Vector2d lowerLeft(Vector2d other){
        if(precedes(other)) return this;
        if(follows(other)) return other;
        return new Vector2d(Math.min(other.x, this.x),Math.min(other.y, this.y));
    }
    public Vector2d opposite(){
        return new Vector2d(-this.x,-this.y);
    }
    @Override
    public boolean equals(Object other){
        if(this == other) return true; //rownosc referencji -> porownouje te same objekty w pamieci
        if(other == null || this.getClass() != other.getClass()) return false; //nie mamy doczynienia z ta samo klasa
        Vector2d vectorOther = (Vector2d) other;
        return Objects.equals(this.x,vectorOther.x) && Objects.equals(this.y,vectorOther.y);
    }

}
