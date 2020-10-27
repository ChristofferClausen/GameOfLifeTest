package GameOfLife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static GameOfLife.State.ALIVE;
import static GameOfLife.State.DEAD;
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
    void initializeGameOfLifeObjectExpectingNotNull() {
        assertNotNull(gameOfLife);
    }

    @Test
    void initializeFirstGenerationWithListParameterExpectingStringGridAsReturn() {
        gameOfLife.initializeFirstGeneration(positions);
        assertTrue(gameOfLife.gridString().length() > 0);
    }

    @Test
    void initializeFirstGenerationWithListParameterExpectingReturnTrueOnIsAlive() {
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.getGrid();
        for (Position pos : positions) {
            assertEquals(ALIVE,grid[pos.y][pos.x].isAlive());
        }
    }

    @Test
    void killCellAtFourFourExpectingIsAliveToReturnFalse() {
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.getGrid();
        var cell = grid[4][4];
        cell.updateState(DEAD);
        assertEquals(DEAD,cell.isAlive());
    }

    @Test
    void resurrectCellAtTwoTwoExpectingIsAliveToReturnTrue() {
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.getGrid();
        var cell = grid[2][2];
        cell.updateState(ALIVE);
        assertEquals(ALIVE,cell.isAlive());
    }

    @Test
    void checkAmountOfAliveNeighboursAtFourFiveExpectingTwo() {
        gameOfLife.initializeFirstGeneration(positions);
        assertEquals(2, gameOfLife.countNeighbours(gameOfLife.getGrid(), 4, 5));
    }

    @Test
    void checkAmountOfAliveNeighboursAtZeroZeroExpectingZeroAndNoIndexOutOfBoundsException() {
        gameOfLife.initializeFirstGeneration(positions);
        assertEquals(0, gameOfLife.countNeighbours(gameOfLife.getGrid(), 0, 0));
    }

    @Test
    void checkAmountOfAliveNeighboursOutSideOfGridExpectingIndexOutOfBoundException() {
        assertThrows(IndexOutOfBoundsException.class, () -> gameOfLife.countNeighbours(gameOfLife.getGrid(), 10, 11));
    }

    @Test
    void checkAmountOfAliveNeighboursWithTooLowCoordinatesExpectingIndexOutOfBoundException() {
        assertThrows(IndexOutOfBoundsException.class, () -> gameOfLife.countNeighbours(gameOfLife.getGrid(), -1, 1));
    }

    @Test
    void checkPrintGridMethodWithNoAliveCellsExpectingDeadGrid() { //TODO rename and make it look better
        var deadGridRow = ".\t.\t.\t.\t.\t.\t.\t.\t.\t.\t" + "\n";
        var actualGrid = gameOfLife.gridString();
        assertEquals(deadGridRow.repeat(10), actualGrid);
    }

    @Test
    void checkPrintGridMethodWithAliveCells() {
        positions = new ArrayList<>();
        positions.add(new Position(2, 1));
        positions.add(new Position(2, 2));
        positions.add(new Position(2, 3));
        gameOfLife = new GameOfLife(5, 5);
        gameOfLife.initializeFirstGeneration(positions);
        var expected = ".\t.\t.\t.\t.\t" + "\n" +
                ".\t.\tx\t.\t.\t" + "\n" +
                ".\t.\tx\t.\t.\t" + "\n" +
                ".\t.\tx\t.\t.\t" + "\n" +
                ".\t.\t.\t.\t.\t" + "\n";
        assertEquals(expected, gameOfLife.gridString());
    }

    @Test
    void updateGridExpectingNewGridAfterUpdateASDASD() {
        gameOfLife.initializeFirstGeneration(positions);
        var firstGrid = gameOfLife.gridString();
        var nextGeneration = gameOfLife.nextGeneration(gameOfLife.getGrid());
        var newGrid = gameOfLife.gridString();
        assertNotEquals(firstGrid, newGrid);
        assertNotEquals("", nextGeneration);
    }

    @Test
    void updateGridExpectingThreeFiveAndFiveFiveToBeAlive() {
        gameOfLife.initializeFirstGeneration(positions);
        gameOfLife.nextGeneration(gameOfLife.getGrid());
        assertEquals(ALIVE,gameOfLife.getGrid()[5][5].isAlive());
        assertEquals(ALIVE,gameOfLife.getGrid()[5][3].isAlive());
    }

    @Test
    void updateGridWithWrongParamsExpectingNullPointerException() { //TODO name
        assertThrows(NullPointerException.class, () -> gameOfLife.nextGeneration(new Cell[1][1]));
    }

    @Test
    void checkNeighboursAtFiveFiveExpectingArrayOfSurroundingPositions() {
        var expectedNeighbours = new Position[]{
                new Position(4, 4),
                new Position(4, 5),
                new Position(4, 6),

                new Position(5, 4),
                new Position(5, 6),

                new Position(6, 4),
                new Position(6, 5),
                new Position(6, 6)
        };
        var actualNeighbours = gameOfLife.neighboursArray(5, 5);
        assertTrue(Arrays.equals(expectedNeighbours, actualNeighbours));
    }

    @Test
    void testThatNeighboursGetResetAtOneOneAfterCellUpdatesExpectingNeighboursToBeZero() {
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.getGrid();
        gameOfLife.countNeighbours(gameOfLife.getGrid(), 1, 1);
        var oldCell = grid[1][1].countNeighbours();
        gameOfLife.nextGeneration(grid);
        gameOfLife.countNeighbours(gameOfLife.getGrid(), 1, 1);
        var newCell = grid[1][1].countNeighbours();

        assertEquals(1, oldCell);
        assertEquals(0, newCell);
    }

    @Test
    void tryingToInitializeWithPositionOutsideGridSizeExpectingArrayIndexOutOfBoundsException() {
        positions.add(new Position(15,15));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> gameOfLife.initializeFirstGeneration(positions));
    }

}