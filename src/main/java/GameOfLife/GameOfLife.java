package GameOfLife;

import java.util.List;

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
            grid[position.y][position.x].updateState(true);
        }
    }

    public String gridString() {
        StringBuilder s = new StringBuilder();
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                if (!cell.isAlive())
                    s.append("." + "\t");
                else
                    s.append("x" + "\t");
            }
            s.append("\n");
        }
        return s.toString();
    }

    //TODO add a state?
    public Cell[][] getGrid() { //TODO rename
        return grid;
    }

    public String nextGeneration(Cell[][] grid) {
        checkAllNeighbours();
        updateCells(grid);
        return gridString();
    }

    private void checkAllNeighbours() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                checkNeighbours(row, column, grid);
            }
        }
    }

    public int checkNeighbours(int y, int x, Cell[][] grid) {
        Position pos = new Position(x, y);
        var cell = grid[pos.y][pos.x];
        var neighbours = getNeighbours(pos.y, pos.x);
        for (Position neighbour : neighbours) {
            if (neighbour.x >= ZERO && neighbour.x < WIDTH && neighbour.y >= ZERO && neighbour.y < HEIGHT && grid[neighbour.y][neighbour.x].isAlive()) {
                cell.addNeighbour();
            }
        }
        return cell.getNeighbours(); //TODO Remove and test against cell instead?
    }

    public Position[] getNeighbours(int y, int x) {
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

    private void updateCells(Cell[][] grid) {
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                updateCell(cell);
            }
        }
    }

    private void updateCell(Cell cell) {
        if (cell.isAlive()) {
            if (cell.getNeighbours() < MIN_NEIGHBOURS || cell.getNeighbours() > MAX_NEIGHBOURS)
                cell.updateState(false);
        } else {
            if (cell.getNeighbours() == MAX_NEIGHBOURS)
                cell.updateState(true);
        }
        cell.resetNeighbour();
    }

}