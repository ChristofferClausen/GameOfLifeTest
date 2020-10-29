package GameOfLife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void initializePositionExpectingANotNullObject() {
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
    void compareTwoSamePositionsReturnsTrue() {
        Position pos1 = new Position(1,1);
        Position pos2 = new Position(1,1);
        assertEquals(pos1, pos2);
    }

    @Test
    void compareAnObjectWithItselfReturnsTrue() {
        Position position = new Position(1,1);
        assertEquals(position,position);
    }

    @Test
    void comparePositionWithAnObjectThatsNotAPositionReturnsFalse() {
        Position position = new Position(1,1);
        Object string = "Position";
        assertNotEquals(position,string);
    }

    @Test
    void compareTwoPositionObjectThatArentTheSameReturnsFalse() {
        Position pos1 = new Position(1,1);
        Position pos2 = new Position(1,2);
        assertNotEquals(pos1, pos2);
    }

}