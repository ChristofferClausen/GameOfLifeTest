package GameOfLife;


import org.junit.jupiter.api.Test;

import static GameOfLife.State.ALIVE;
import static GameOfLife.State.DEAD;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void InitializeCellObjectExpectingNotNull() {
        Cell cell = new Cell();
        assertNotNull(cell);
    }

    @Test
    void CheckCellToSeeIfItIsAliveExpectingFalse() {
        Cell cell = new Cell();
        assertEquals(DEAD,cell.isAlive());
    }

    @Test
    void CheckCellToSeeIfItIsAliveWithParamExpectingTrue() {
        Cell cell = new Cell(ALIVE);
        assertEquals(ALIVE,cell.isAlive());
    }

    @Test
    void CheckCellToSeeIfAliveWithParamExpectingFalse() {
        Cell cell = new Cell(DEAD);
        assertEquals(DEAD,cell.isAlive());
    }

}