package GameOfLife;

import java.util.List;

import static GameOfLife.State.ALIVE;
import static GameOfLife.State.DEAD;

public class GameOfLife {

    private final int MINUS_ONE = -1;
    private final int ZERO = 0;
    private final int ONE = 1;
    private final int HEIGHT;
    private final int WIDTH;
    private final int MAX_NEIGHBOURS = 3;
    private final int MIN_NEIGHBOURS = 2;
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

    public String gridString() {
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

    public Cell[][] getGrid() { //TODO rename
        return grid;
    }

    public String nextGeneration(Cell[][] grid) {
        countAllNeighbours();
        updateAllCells(grid);
        return gridString();
    }

    private void countAllNeighbours() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                countNeighbours(grid, row, column);
            }
        }
    }

    public int countNeighbours(Cell[][] grid, int y, int x) {
        Position pos = new Position(x, y);
        var cell = grid[pos.y][pos.x];
        var neighbours = neighboursArray(pos.y, pos.x);
        for (Position neighbour : neighbours) {
            if (neighbour.x >= ZERO && neighbour.x < WIDTH && neighbour.y >= ZERO && neighbour.y < HEIGHT && grid[neighbour.y][neighbour.x].isAlive() == ALIVE) {
                cell.addNeighbour();
            }
        }
        return cell.countNeighbours();
    }

    public Position[] neighboursArray(int y, int x) { //TODO rename
        return new Position[]{
                new Position(x + MINUS_ONE, y + MINUS_ONE),
                new Position(x + MINUS_ONE, y),
                new Position(x + MINUS_ONE, y + ONE),

                new Position(x, y + MINUS_ONE),
                new Position(x, y + ONE),

                new Position(x + ONE, y + MINUS_ONE),
                new Position(x + ONE, y),
                new Position(x + ONE, y + ONE)
        };
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
            if (cell.countNeighbours() < MIN_NEIGHBOURS || cell.countNeighbours() > MAX_NEIGHBOURS)
                cell.updateState(DEAD);
        } else {
            if (cell.countNeighbours() == MAX_NEIGHBOURS)
                cell.updateState(ALIVE);
        }
        cell.resetNeighbour();
    }

}