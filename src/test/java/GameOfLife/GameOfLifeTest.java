package GameOfLife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static GameOfLife.State.ALIVE;
import static GameOfLife.State.DEAD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {

    GameOfLife gameOfLife;
    List<Position> positions;

    //TODO check all names

    @BeforeEach
    void initBeforeEach() {
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
    void initializeGameOfLifeExpectingNotNullObject() {
        assertNotNull(gameOfLife);
    }

    @Test
    void initializeFirstGenerationWithListParameterReturnsGridString() {
        gameOfLife.initializeFirstGeneration(positions);
        assertTrue(gameOfLife.gridAsString().length() > 0);
    }

    @Test
    void initializeFirstGenerationWithListParameterReturnsTrueOnIsAliveForCellsInList() {
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.getGrid();
        for (Position pos : positions) {
            assertEquals(ALIVE, grid[pos.y][pos.x].isAlive());
        }
    }

    @Test
    void killCellAtFourFourIsAliveReturnsFalse() {
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.getGrid();
        var cell = grid[4][4];
        cell.updateState(DEAD);
        assertEquals(DEAD, cell.isAlive());
    }

    @Test
    void resurrectCellAtTwoTwoIsAliveReturnsTrue() {
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.getGrid();
        var cell = grid[2][2];
        cell.updateState(ALIVE);
        assertEquals(ALIVE, cell.isAlive());
    }

    @Test
    void countNeighboursAtFourFiveReturnsTwo() {
        gameOfLife.initializeFirstGeneration(positions);
        assertEquals(2, gameOfLife.countNeighbours(gameOfLife.getGrid(), 4, 5));
    }

    @Test
    void countNeighboursAtZeroZeroReturnsZero() {
        gameOfLife.initializeFirstGeneration(positions);
        assertEquals(0, gameOfLife.countNeighbours(gameOfLife.getGrid(), 0, 0));
    }

    @Test
    void countNeighboursAtZeroZeroReturnsNoIndexOutOfBoundsException() {
        gameOfLife.initializeFirstGeneration(positions);
        assertDoesNotThrow(() -> gameOfLife.countNeighbours(gameOfLife.getGrid(),0,0));
    }

    @Test
    void countNeighboursOutsideOfGridWithTooHighCoordinatesThrowsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> gameOfLife.countNeighbours(gameOfLife.getGrid(), 10, 11));
    }

    @Test
    void countNeighboursOutsideGridWithTooLowCoordinatesThrowsIndexOutOfBoundException() {
        assertThrows(IndexOutOfBoundsException.class, () -> gameOfLife.countNeighbours(gameOfLife.getGrid(), -1, 1));
    }

    @Test
    void gridAsStringWithNoAliveCellsReturnsStringOfDeadGrid() {
        var deadGridRow = ".\t.\t.\t.\t.\t.\t.\t.\t.\t.\t" + "\n";
        var actualGrid = gameOfLife.gridAsString();
        assertEquals(deadGridRow.repeat(10), actualGrid);
    }

    @Test
    void gridAsStringWithLiveCellsReturnsExpectedGridWithSomeLiveCells() {
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
        assertEquals(expected, gameOfLife.gridAsString());
    }

    @Test
    void nextGenerationReturnsNewGridAsStringAfterUpdate() {
        gameOfLife.initializeFirstGeneration(positions);
        var firstGrid = gameOfLife.gridAsString();
        var nextGeneration = gameOfLife.nextGeneration(gameOfLife.getGrid());
        var newGrid = gameOfLife.gridAsString();
        assertNotEquals(firstGrid, newGrid);
        assertNotEquals("", nextGeneration);
    }

    @Test
    void nextGenerationCheckinThreeFiveAndFiveFiveIsAliveReturnsTrue() {
        gameOfLife.initializeFirstGeneration(positions);
        gameOfLife.nextGeneration(gameOfLife.getGrid());
        assertEquals(ALIVE, gameOfLife.getGrid()[5][5].isAlive());
        assertEquals(ALIVE, gameOfLife.getGrid()[5][3].isAlive());
    }

    @Test
    void nextGenerationWithWrongParamsThrowsNullPointerException() { //TODO name
        assertThrows(NullPointerException.class, () -> gameOfLife.nextGeneration(new Cell[1][1]));
    }

    @Test
    void countNeighboursAtFiveFiveReturnsArrayOfSurroundingPositions() {
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
        var actualNeighbours = gameOfLife.getNeighbours(5, 5);
        assertThat(actualNeighbours, contains(expectedNeighbours));

    }

    @Test
    void countNeighboursOnCellAfterNextGenerationReturnsZero() {
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.getGrid();

        gameOfLife.countNeighbours(gameOfLife.getGrid(), 1, 1);
        var oldCell = grid[1][1].countAliveNeighbours();

        gameOfLife.nextGeneration(grid);
//        gameOfLife.countNeighbours(gameOfLife.getGrid(), 1, 1);
        var newCell = grid[1][1].countAliveNeighbours();

        assertEquals(1, oldCell);
        assertEquals(0, newCell);
    }

    @Test
    void initializeFirstGenerationWithPositionOutsideGridSizeThrowsArrayIndexOutOfBoundsException() {
        positions.add(new Position(15, 15));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> gameOfLife.initializeFirstGeneration(positions));
    }

    @Test
    void nextGenerationUpdatesCellsAndReturnsNewGridAsString() {
        GameOfLife gameOfLife = new GameOfLife(3, 3);
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(1, 0));
        positions.add(new Position(1, 1));
        positions.add(new Position(1, 2));
        gameOfLife.initializeFirstGeneration(positions);
        final var oldGrid = gameOfLife.getGrid();
        gameOfLife.nextGeneration(oldGrid);
        assertEquals(
                ".\t.\t.\t\n" +
                        "x\tx\tx\t\n" +
                        ".\t.\t.\t\n",
                gameOfLife.gridAsString());
    }

    @Test
    void cellsWithMoreThanThreeNeighboursDieFromOverpopulation() {
        GameOfLife gameOfLife = new GameOfLife(3, 3);
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(0, 1));
        positions.add(new Position(1, 1));
        positions.add(new Position(2, 1));
        positions.add(new Position(1, 0));
        positions.add(new Position(1, 2));
        gameOfLife.initializeFirstGeneration(positions);
        final var oldGrid = gameOfLife.getGrid();
        gameOfLife.nextGeneration(oldGrid);
        assertEquals(
                "x\tx\tx\t\n" +
                        "x\t.\tx\t\n" +
                        "x\tx\tx\t\n",
                gameOfLife.gridAsString());
    }

}