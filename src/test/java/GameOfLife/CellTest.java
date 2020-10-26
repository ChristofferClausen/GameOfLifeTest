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
    void CheckCellToSeeIfItIsAliveExpectingTrue() {
        Cell cell = new Cell(true);
        assertTrue(cell.isAlive());
    }

}