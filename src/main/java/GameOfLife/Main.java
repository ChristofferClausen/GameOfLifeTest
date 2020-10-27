package GameOfLife;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife(10,10);
        List<Position> positions;
        positions = aliveCells();
        gameOfLife.initializeFirstGeneration(positions);
        var grid = gameOfLife.getGrid();
        loopGenerations(gameOfLife, grid);
    }

    private static List<Position> aliveCells() {
        List<Position> positions;
        positions = new ArrayList<>();
        positions.add(new Position(0,0));
        positions.add(new Position(4, 4));
        positions.add(new Position(4, 5));
        positions.add(new Position(4, 6));
        positions.add(new Position(4, 1));
        positions.add(new Position(8, 8));
        return positions;
    }

    private static void loopGenerations(GameOfLife gameOfLife, Cell[][] grid) {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            gameOfLife.nextGeneration(grid);
            System.out.println(gameOfLife.gridString());
        }, 0, 500, TimeUnit.MILLISECONDS);
    }
}