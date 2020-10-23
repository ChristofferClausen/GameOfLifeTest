package GameOfLife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void InitializeCellObjectExpectingNotNull() {
        Cell cell = new Cell(5,5);
        assertNotNull(cell);
    }

    @Test
    void InitializeCellWithFiveAndFourExpectingSameBackWhenAskingForPosition() {
        Cell cell = new Cell(5,4);
        assertEquals(5,cell.x);
        assertEquals(4,cell.y);
    }

    @Test
    void CheckCellToSeeIfItIsAliveExpectingFalse() {
        Cell cell = new Cell(5,5);
        assertFalse(cell.isAlive());
    }

    @Test
    void CheckCellToSeeIfItIsAliveExpectingTrue() {
        Cell cell = new Cell(5,5, true);
        assertTrue(cell.isAlive());
    }

    @Test
    void InitializeCellWithNegativesExpectingIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,() -> new Cell(-1,-5));
    }

    @Test
    void InitializeCellWithNegativesAndPassingAliveBooleanExpectingIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,() -> new Cell(-1,-5,true));
    }

}