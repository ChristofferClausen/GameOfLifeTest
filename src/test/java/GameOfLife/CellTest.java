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
    void checkCellToSeeIfItIsAliveExpectingFalse() {
        Cell cell = new Cell();
        assertEquals(DEAD, cell.isAlive());
    }

    @Test
    void checkCellToSeeIfItIsAliveWithParamExpectingTrue() {
        Cell cell = new Cell(ALIVE);
        assertEquals(ALIVE, cell.isAlive());
    }

    @Test
    void checkCellToSeeIfAliveWithParamExpectingFalse() {
        Cell cell = new Cell(DEAD);
        assertEquals(DEAD, cell.isAlive());
    }

}