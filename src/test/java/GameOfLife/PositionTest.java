package GameOfLife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void initializePositionWithOneOneExpectingANotNullObject() {
        Position position = new Position(1,1);
        assertNotNull(position);
    }

    @Test
    void initializePositionWithFiveAndFiveExpectingXAndYToReturnFive() {
        Position position = new Position(5,5);
        assertEquals(5,position.x);
        assertEquals(5,position.y);
    }

    @Test
    void compareTwoPositionsExpectingTrue() {
        Position pos1 = new Position(1,1);
        Position pos2 = new Position(1,1);
        assertEquals(pos1, pos2);
    }

    @Test
    void compareAnObjectWithItselfExpectingTrue() {
        Position position = new Position(1,1);
        assertEquals(position,position);
    }

    @Test
    void comparePositionWithAnObjetThatsNotAPositionExpectingFalse() {
        Position position = new Position(1,1);
        Object string = "Position";
        assertNotEquals(position,string);
    }

    @Test
    void compareTwoPositionObjectThatArentTheSameExpectingReturnFalse() {
        Position pos1 = new Position(1,1);
        Position pos2 = new Position(1,2);
        assertNotEquals(pos1, pos2);
    }

}