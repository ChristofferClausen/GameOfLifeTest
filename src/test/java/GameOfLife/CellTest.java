package GameOfLife;


import org.junit.jupiter.api.Test;

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
        assertFalse(cell.isAlive());
    }

    @Test
    void CheckCellToSeeIfItIsAliveWithParamExpectingTrue() {
        Cell cell = new Cell(true);
        assertTrue(cell.isAlive());
    }

    @Test
    void CheckCellToSeeIfAliveWithParamExpectingFalse() {
        Cell cell = new Cell(false);
        assertFalse(cell.isAlive());
    }

}