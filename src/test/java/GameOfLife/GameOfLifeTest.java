package GameOfLife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {

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

}