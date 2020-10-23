package GameOfLife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {

    @Test
    void InitializeGameOfLifeObjectExpectingNotNull() {
        GameOfLife gameOfLife = new GameOfLife();
        assertNotNull(gameOfLife);
    }

}