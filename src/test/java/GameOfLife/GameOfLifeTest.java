package GameOfLife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {

    GameOfLife gameOfLife;
    List<Position> positions; //TODO set in beforeEach?

    @BeforeEach
    void initEach() {
        gameOfLife = new GameOfLife(10, 10);
        positions = new ArrayList<>();
        positions.add(new Position(0,0));
        positions.add(new Position(4, 4));
        positions.add(new Position(4, 5));
        positions.add(new Position(4, 6));
        positions.add(new Position(4, 1));
        positions.add(new Position(8, 8));
    }

    @Test
    void InitializeGameOfLifeObjectExpectingNotNull() {
        assertNotNull(gameOfLife);
    }

    @Test
    void InitializeFirstGenerationWithListParameterExpectingReturnTrueOnIsAlive() {
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.checkState();
        for (Position pos : positions) {
            assertTrue(grid[pos.y][pos.x].isAlive());
        }
    }

    @Test
    void KillCellAtFourFourExpectingIsAliveToReturnFalse() {
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.grid;
        var cell = grid[4][4];
        cell.updateState(false);
        assertFalse(cell.isAlive());
    }

    @Test
    void ResurrectCellAtTwoTwoExpectingIsAliveToReturnTrue() {
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.grid;
        var cell = grid[2][2];
        cell.updateState(true);
        assertTrue(cell.isAlive());
    }

    @Test
    void CheckAmountOfAliveNeighboursAtFourFiveExpectingTwo() {
        gameOfLife.initializeFirstGeneration(positions);
        assertEquals(2, gameOfLife.checkNeighbours(4,5));
    }

    @Test
    void CheckAmountOfAliveNeighboursAtZeroZeroExpectingZeroAndNoIndexOutOfBoundsException() {
        gameOfLife.initializeFirstGeneration(positions);
        assertEquals(0,gameOfLife.checkNeighbours(0,0));
    }

    @Test
    void CheckAmountOfAliveNeighboursOutSideOfGridExpectingIndexOutOfBoundException() {
        assertThrows(IndexOutOfBoundsException.class, () -> gameOfLife.checkNeighbours(10,11));
    }

    @Test
    void UpdateGridExpectingNewGridAfterUpdate() {
        gameOfLife.initializeFirstGeneration(positions);
        var firstGen = gameOfLife.grid;
        gameOfLife.nextGeneration();
        var nextGen = gameOfLife.grid;
        assertArrayEquals(firstGen,nextGen);
        //TODO assertArrayNotEquals
    }

}