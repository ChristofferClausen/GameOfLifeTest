package GameOfLife;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameOfLife {

    private final int height;
    private final int width;
    Cell[][] grid;

    GameOfLife(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new Cell[height][width];
        initializeDeadGrid();
    }

    private void initializeDeadGrid() {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                grid[row][column] = new Cell();
            }
        }
    }

    public void initializeFirstGeneration(List<Position> positions) {
        for (Position position : positions) {
            grid[position.y][position.x].updateState(true);
        }
        System.out.println("First generation");
        printGrid();
    }

    public void printGrid() {
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                if (cell.isAlive())
                    System.out.print("x" + "\t");
                else if (!cell.isAlive()) //TODO Look into this. Double prints when something is alive without else if?
                    System.out.print("." + "\t");
            }
            System.out.println();
        }
    }

    //TODO add a state?
    public Cell[][] checkState() { //TODO rename
        return grid;
    }

    public void nextGeneration() {
        for (int row = 0; row <height; row++) {
            for (int column = 0; column < width; column++) {
                checkNeighbours(row,column);
            }
        }
        updateCells();
        System.out.println("New generation");
        printGrid();
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            updateCells();
            System.out.println("New generation");
            printGrid();
        }, 0, 1, TimeUnit.SECONDS);
        //TODO loop for more generations
    }

    public int checkNeighbours(int y, int x) {
        var cell = grid[y][x];
        Position[] neighbours = new Position[]{
                new Position(x - 1, y - 1),
                new Position(x - 1, y),
                new Position(x - 1, y + 1),

                new Position(x, y - 1),
                new Position(x, y + 1),

                new Position(x + 1, y - 1),
                new Position(x + 1, y),
                new Position(x + 1, y + 1),
        };
        for(Position neighbour: neighbours) {
            if (neighbour.x >= 0 && neighbour.x < width && neighbour.y >= 0 && neighbour.y < height && grid[neighbour.y][neighbour.x].isAlive()) {
                cell.addNeighbour();
            }
        }
        return cell.getNeighbours(); //TODO Remove and test against cell instead?
    }

    private void updateCells() {
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                if (cell.isAlive()) {
                    if (cell.getNeighbours() < 2 || cell.getNeighbours() > 3)
                        cell.updateState(false);
                } else {
                    if (cell.getNeighbours() == 3)
                    cell.updateState(true);
                }
            }
        }
    }
}
