package GameOfLife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {

    GameOfLife gameOfLife;
    List<Position> positions; //TODO set in beforeEach?

    //TODO Change to camelCase?

    @BeforeEach
    void initEach() {
        gameOfLife = new GameOfLife(10, 10);
        positions = new ArrayList<>();
        positions.add(new Position(0, 0));
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
    void InitializeFirstGenerationWithListParameterExpectingStringGridAsReturn() {
        var grid = gameOfLife.initializeFirstGeneration(positions);
        assertTrue(grid.length() > 0);
    }

    @Test
    void InitializeFirstGenerationWithListParameterExpectingReturnTrueOnIsAlive() {
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.getGrid();
        for (Position pos : positions) {
            assertTrue(grid[pos.y][pos.x].isAlive());
        }
    }

    @Test
    void KillCellAtFourFourExpectingIsAliveToReturnFalse() {
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.getGrid();
        var cell = grid[4][4];
        cell.updateState(false);
        assertFalse(cell.isAlive());
    }

    @Test
    void ResurrectCellAtTwoTwoExpectingIsAliveToReturnTrue() {
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.getGrid();
        var cell = grid[2][2];
        cell.updateState(true);
        assertTrue(cell.isAlive());
    }

    @Test
    void CheckAmountOfAliveNeighboursAtFourFiveExpectingTwo() {
        gameOfLife.initializeFirstGeneration(positions);
        assertEquals(2, gameOfLife.checkNeighbours(4, 5));
    }

    @Test
    void CheckAmountOfAliveNeighboursAtZeroZeroExpectingZeroAndNoIndexOutOfBoundsException() {
        gameOfLife.initializeFirstGeneration(positions);
        assertEquals(0, gameOfLife.checkNeighbours(0, 0));
    }

    @Test
    void CheckAmountOfAliveNeighboursOutSideOfGridExpectingIndexOutOfBoundException() {
        assertThrows(IndexOutOfBoundsException.class, () -> gameOfLife.checkNeighbours(10, 11));
    }

    @Test
    void CheckAmountOfAliveNeighboursWithTooLowCoordinatesExpectingIndexOutOfBoundException() {
        assertThrows(IndexOutOfBoundsException.class, () -> gameOfLife.checkNeighbours(-1, 1));
    }

    @Test
    void CheckPrintGridMethodWithNoAliveCellsExpectingDeadGrid() { //TODO rename and make it look better
        var deadGridRow = ".\t.\t.\t.\t.\t.\t.\t.\t.\t.\t" + "\n";
        var actualGrid = gameOfLife.printGrid();
        assertEquals(deadGridRow.repeat(10), actualGrid);
    }

    @Test
    void CheckPrintGridMethodWithAliveCells() {
        positions = new ArrayList<>();
        positions.add(new Position(2, 1));
        positions.add(new Position(2, 2));
        positions.add(new Position(2, 3));
        gameOfLife = new GameOfLife(5,5);
        gameOfLife.initializeFirstGeneration(positions);
        var expected = ".\t.\t.\t.\t.\t" + "\n" +
                ".\t.\tx\t.\t.\t" + "\n" +
                ".\t.\tx\t.\t.\t" + "\n" +
                ".\t.\tx\t.\t.\t" + "\n" +
                ".\t.\t.\t.\t.\t" + "\n";
        assertEquals(expected, gameOfLife.printGrid());
    }

    @Test
    void UpdateGridExpectingNewGridAfterUpdateASDASD() {
        gameOfLife.initializeFirstGeneration(positions);
        var firstGrid = gameOfLife.printGrid();
        var nextGeneration = gameOfLife.nextGeneration(gameOfLife.getGrid());
        var newGrid = gameOfLife.printGrid();
        assertNotEquals(firstGrid, newGrid);
        assertNotEquals("",nextGeneration);
    }

    @Test
    void UpdateGridExpectingThreeFiveAndFiveFiveToBeAlive() {
        gameOfLife.initializeFirstGeneration(positions);
        gameOfLife.nextGeneration(gameOfLife.getGrid());
        assertTrue(gameOfLife.getGrid()[5][5].isAlive());
        assertTrue(gameOfLife.getGrid()[5][3].isAlive());
    }

    @Test
    void UpdateGridWithWrongParamsExpectingNullPointerException() { //TODO name
        assertThrows(NullPointerException.class, () -> gameOfLife.nextGeneration(new Cell[1][1]));
    }

    @Test
    void UpdateGridWithWrongParamsExpectingNullPointerException2() { //TODO name
        Cell[][] grid = new Cell[2][2];
        grid[0][0] = new Cell();
        grid[0][1] = new Cell();
        grid[1][0] = new Cell();
        grid[1][1] = new Cell();
        assertThrows(NullPointerException.class, () -> gameOfLife.nextGeneration(grid));
    }

    @Test
    void CheckNeighboursAtFiveFiveExpectingArrayOfSurroundingPositions() {
        var expectedNeighbours = new Position[]{
                new Position(4,4),
                new Position(4,5),
                new Position(4,6),

                new Position(5,4),
                new Position(5,6),

                new Position(6,4),
                new Position(6,5),
                new Position(6,6)
        };
        var actualNeighbours = gameOfLife.getNeighbours(5,5);
        assertTrue(Arrays.equals(expectedNeighbours,actualNeighbours));
    }

    @Test
    void TestThatNeighboursGetResetAtOneOneAfterCellUpdatesExpectingNeighboursToBeZero() {
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.getGrid();
        gameOfLife.checkNeighbours(1,1);
        var oldCell = grid[1][1].getNeighbours();
        gameOfLife.nextGeneration(grid);
        gameOfLife.checkNeighbours(1,1);
        var newCell = grid[1][1].getNeighbours();

        assertEquals(1,oldCell);
        assertEquals(0, newCell);
    }

}