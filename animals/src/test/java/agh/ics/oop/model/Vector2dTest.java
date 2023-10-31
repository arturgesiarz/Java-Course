package agh.ics.oop.model;
import org.junit.jupiter.api.Test;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void toStringTest() {
        Vector2d vector1 = new Vector2d(1,2); //both positive
        Vector2d vector2 = new Vector2d(-12,9); //x negative, y positive
        Vector2d vector3 = new Vector2d(98,-2); //x positive, y negative
        Vector2d vector4 = new Vector2d(-98, -2130); //both negative

        assertEquals("(1,2)",vector1.toString());
        assertEquals("(-12,9)",vector2.toString());
        assertEquals("(98,-2)",vector3.toString());
        assertEquals("(-98,-2130)",vector4.toString());
    }

    @Test
    void precedesTest() {
        Vector2d vectorMain = new Vector2d(1,2);
        Vector2d vector1 = new Vector2d(1, 2); //equals
        Vector2d vector2 = new Vector2d(10,15); //both greater
        Vector2d vector3 = new Vector2d(0,5); //x smaller, y greater
        Vector2d vector4 = new Vector2d(2, 0); //x grater, y smaller
        Vector2d vector5 = new Vector2d(-98, -2130);//both smaller

        assertTrue(vectorMain.precedes(vector1));
        assertTrue(vectorMain.precedes(vector2));
        assertFalse(vectorMain.precedes(vector3));
        assertFalse(vectorMain.precedes(vector4));
        assertFalse(vectorMain.precedes(vector5));
    }

    @Test
    void followsTest() {
        Vector2d vectorMain = new Vector2d(12,89);
        Vector2d vector1 = new Vector2d(12,89); //equals
        Vector2d vector2 = new Vector2d(0,-873); //both smaller
        Vector2d vector3 = new Vector2d(10, 90); //x smaller, y greater
        Vector2d vector4 = new Vector2d(13, -9383); //x grater, y smaller
        Vector2d vector5 = new Vector2d(1000, 1000); //both grater

        assertTrue(vectorMain.follows(vector1));
        assertTrue(vectorMain.follows(vector2));
        assertFalse(vectorMain.follows(vector3));
        assertFalse(vectorMain.follows(vector4));
        assertFalse(vectorMain.follows(vector5));
    }

    @Test
    void equalsTest() {
        Vector2d vectorMain = new Vector2d(87,102);
        Vector2d vector1 = new Vector2d(87,102); //equals
        Vector2d vector2 = new Vector2d(87,5); //not equals
        Vector2d vector3 = new Vector2d(123,102); //not equals
        Vector2d vector4 = new Vector2d(1000,1000); //not equals
        Vector2d vector5 = new Vector2d(-1,-1); //not equals

        assertTrue(Objects.equals(vectorMain,vector1));
        assertFalse(Objects.equals(vectorMain,vector2));
        assertFalse(Objects.equals(vectorMain,vector3));
        assertFalse(Objects.equals(vectorMain,vector4));
        assertFalse(Objects.equals(vectorMain,vector5));
    }

    @Test
    void addTest() {
        Vector2d vectorMain = new Vector2d(0,90);
        Vector2d vector1 = new Vector2d(1,2); //both positive
        Vector2d vector2 = new Vector2d(-12,9); //x negative, y positive
        Vector2d vector3 = new Vector2d(98,-2); //x positive, y negative
        Vector2d vector4 = new Vector2d(-98, -2130); //both negative

        Vector2d solution1 = new Vector2d(1,92); //vectorMain + vector1
        Vector2d solution2 = new Vector2d(-12,99); //vectorMain + vector2
        Vector2d solution3 = new Vector2d(98,88); //vectorMain + vector3
        Vector2d solution4 = new Vector2d(-98,-2040); //vectorMain + vector4

        assertEquals(solution1, vectorMain.add(vector1));
        assertEquals(solution2, vectorMain.add(vector2));
        assertEquals(solution3, vectorMain.add(vector3));
        assertEquals(solution4, vectorMain.add(vector4));
    }

    @Test
    void subtractTest() {
        Vector2d vectorMain = new Vector2d(0,90);
        Vector2d vector1 = new Vector2d(1,2); //both positive
        Vector2d vector2 = new Vector2d(-12,9); //x negative, y positive
        Vector2d vector3 = new Vector2d(98,-2); //x positive, y negative
        Vector2d vector4 = new Vector2d(-98, -2130); //both negative

        Vector2d solution1 = new Vector2d(-1,88); //vectorMain - vector1
        Vector2d solution2 = new Vector2d(12,81); //vectorMain - vector2
        Vector2d solution3 = new Vector2d(-98,92); //vectorMain - vector3
        Vector2d solution4 = new Vector2d(98,2220); //vectorMain - vector4

        assertEquals(solution1, vectorMain.subtract(vector1));
        assertEquals(solution2, vectorMain.subtract(vector2));
        assertEquals(solution3, vectorMain.subtract(vector3));
        assertEquals(solution4, vectorMain.subtract(vector4));
    }

    @Test
    void upperRightTest() {
        Vector2d vectorUpper1 = new Vector2d(2,5);
        Vector2d vectorBottom1 = new Vector2d(10,2);
        Vector2d vectorSolution1 = new Vector2d(10, 5);

        Vector2d vectorUpper2 = new Vector2d(-5,8);
        Vector2d vectorBottom2 = new Vector2d(10,1);
        Vector2d vectorSolution2 = new Vector2d(10, 8);

        Vector2d vectorUpper3 = new Vector2d(-2000,-8);
        Vector2d vectorBottom3 = new Vector2d(2,-99);
        Vector2d vectorSolution3 = new Vector2d(2, -8);

        assertEquals(vectorSolution1, vectorUpper1.upperRight(vectorBottom1));
        assertEquals(vectorSolution2, vectorUpper2.upperRight(vectorBottom2));
        assertEquals(vectorSolution3, vectorUpper3.upperRight(vectorBottom3));
    }

    @Test
    void lowerLeftTest() {
        Vector2d vectorUpper1 = new Vector2d(2,5);
        Vector2d vectorBottom1 = new Vector2d(10,2);
        Vector2d vectorSolution1 = new Vector2d(2, 2);

        Vector2d vectorUpper2 = new Vector2d(-5,8);
        Vector2d vectorBottom2 = new Vector2d(10,1);
        Vector2d vectorSolution2 = new Vector2d(-5, 1);

        Vector2d vectorUpper3 = new Vector2d(-2000,-8);
        Vector2d vectorBottom3 = new Vector2d(2,-99);
        Vector2d vectorSolution3 = new Vector2d(-2000, -99);

        assertEquals(vectorSolution1, vectorUpper1.lowerLeft(vectorBottom1));
        assertEquals(vectorSolution2, vectorUpper2.lowerLeft(vectorBottom2));
        assertEquals(vectorSolution3, vectorUpper3.lowerLeft(vectorBottom3));
    }

    @Test
    void oppositeTest() {
        Vector2d vector1 = new Vector2d(2,5);
        Vector2d vectorSol1 = new Vector2d(-2,-5);

        Vector2d vector2 = new Vector2d(-2,5);
        Vector2d vectorSol2 = new Vector2d(2,-5);

        Vector2d vector3 = new Vector2d(2,-5);
        Vector2d vectorSol3 = new Vector2d(-2,5);

        Vector2d vector4 = new Vector2d(-2137,-666);
        Vector2d vectorSol4 = new Vector2d(2137,666);

        assertEquals(vectorSol1, vector1.opposite());
        assertEquals(vectorSol2, vector2.opposite());
        assertEquals(vectorSol3, vector3.opposite());
        assertEquals(vectorSol4, vector4.opposite());

    }

}