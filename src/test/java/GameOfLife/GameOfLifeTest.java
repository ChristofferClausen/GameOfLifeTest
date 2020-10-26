package GameOfLife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {

    GameOfLife gameOfLife;
    private final Cell[] aliveCells = {
            new Cell(4,4),
            new Cell(4,5),
            new Cell(4,6),
            new Cell(1,1),
            new Cell(8,7)
    };

    @BeforeEach
    void initEach() {
        gameOfLife = new GameOfLife(10,10, aliveCells);
    }

    @Test
    void InitializeGameOfLifeObjectExpectingNotNull() {
        GameOfLife gameOfLife = new GameOfLife(10,10, aliveCells);
        assertNotNull(gameOfLife);
    }

    @Test
    void CheckHeightAndWidthOfGridExpectingTenAndTen() {
        assertEquals(10, gameOfLife.height());
        assertEquals(10, gameOfLife.width());
    }
}