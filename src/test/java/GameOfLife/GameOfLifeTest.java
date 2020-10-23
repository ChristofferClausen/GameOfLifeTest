package GameOfLife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GameOfLifeTest {

    @Test
    void InitializeCellObjectExpectingNotNull() {
        Cell cell = new Cell();
        assertNotNull(cell);
    }

}