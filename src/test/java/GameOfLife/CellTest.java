package GameOfLife;


import org.junit.jupiter.api.Test;

import static GameOfLife.State.ALIVE;
import static GameOfLife.State.DEAD;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void initializeCellObjectExpectingNotNull() {
        Cell cell = new Cell();
        assertNotNull(cell);
    }

    @Test
    void isAliveReturnsFalse() {
        Cell cell = new Cell();
        assertEquals(DEAD, cell.isAlive());
    }

    @Test
    void isAliveWithParamReturnsTrue() {
        Cell cell = new Cell(ALIVE);
        assertEquals(ALIVE, cell.isAlive());
    }

    @Test
    void isAliveWithParamReturnsFalse() {
        Cell cell = new Cell(DEAD);
        assertEquals(DEAD, cell.isAlive());
    }

}