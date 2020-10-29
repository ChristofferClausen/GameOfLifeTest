package GameOfLife;

import java.util.Arrays;
import java.util.List;

import static GameOfLife.State.ALIVE;
import static GameOfLife.State.DEAD;

public class GameOfLife {

    private static final int MINUS_ONE = -1;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int MIN_NEIGHBOURS = 2;
    private static final int MAX_NEIGHBOURS = 3;
    private final int HEIGHT;
    private final int WIDTH;
    Cell[][] grid;

    GameOfLife(int height, int width) {
        this.HEIGHT = height;
        this.WIDTH = width;
        grid = new Cell[height][width];
        initializeDeadGrid();
    }

    private void initializeDeadGrid() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                grid[row][column] = new Cell();
            }
        }
    }

    public void initializeFirstGeneration(List<Position> positions) {
        for (Position position : positions) {
            grid[position.y][position.x].updateState(ALIVE);
        }
    }

    public String nextGeneration(Cell[][] grid) {
        countAllNeighbours();
        updateAllCells(grid);
        return gridAsString();
    }

    private void countAllNeighbours() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                countNeighbours(grid, row, column);
            }
        }
    }

    public int countNeighbours(Cell[][] grid, int y, int x) {
        var cell = grid[y][x];
        var neighbours = getNeighbours(y, x);
        for (Position neighbour : neighbours) {
            if (neighbour.x >= ZERO && neighbour.x < WIDTH && neighbour.y >= ZERO && neighbour.y < HEIGHT && grid[neighbour.y][neighbour.x].isAlive() == ALIVE) {
                cell.addAliveNeighbour();
            }
        }
        return cell.countAliveNeighbours();
    }

    public List<Position> getNeighbours(int y, int x) {
        Position[] neighbours = new Position[]{
                new Position(x + MINUS_ONE, y + MINUS_ONE),
                new Position(x + MINUS_ONE, y),
                new Position(x + MINUS_ONE, y + ONE),

                new Position(x, y + MINUS_ONE),
                new Position(x, y + ONE),

                new Position(x + ONE, y + MINUS_ONE),
                new Position(x + ONE, y),
                new Position(x + ONE, y + ONE)
        };
        return Arrays.asList(neighbours);
    }

    private void updateAllCells(Cell[][] grid) {
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                updateCell(cell);
            }
        }
    }

    private void updateCell(Cell cell) {
        if (cell.isAlive() == ALIVE) {
            if (cell.countAliveNeighbours() < MIN_NEIGHBOURS || cell.countAliveNeighbours() > MAX_NEIGHBOURS)
                cell.updateState(DEAD);
        } else {
            if (cell.countAliveNeighbours() == MAX_NEIGHBOURS)
                cell.updateState(ALIVE);
        }
        cell.resetAliveNeighbour();
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public String gridAsString() {
        StringBuilder s = new StringBuilder();
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                if (cell.isAlive() == DEAD)
                    s.append("." + "\t");
                else
                    s.append("x" + "\t");
            }
            s.append("\n");
        }
        return s.toString();
    }

}