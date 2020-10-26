package GameOfLife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {

    GameOfLife gameOfLife;

    @BeforeEach
    void initEach() {
        gameOfLife = new GameOfLife(10,10);
    }

    @Test
    void InitializeGameOfLifeObjectExpectingNotNull() {
        assertNotNull(gameOfLife);
    }

    @Test
    void CheckHeightAndWidthOfGridExpectingTenAndTen() {
        assertEquals(10, gameOfLife.height());
        assertEquals(10, gameOfLife.width());
    }

//    @Test
//    void TestingKillCellExpectingIsAliveOnCellToReturnFalse() {
//    }


}