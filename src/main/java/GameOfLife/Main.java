package GameOfLife;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        var height = Integer.parseInt(args[0]);
        var width = Integer.parseInt(args[1]);
        var gameOfLife = initializeGame(height,width,args[2]);
        loopGenerations(gameOfLife);
    }

    private static GameOfLife initializeGame(int height, int width, String pattern) {
        GameOfLife gameOfLife = new GameOfLife(height,width);
        gameOfLife.initializeFirstGeneration(pattern(pattern));
        System.out.println(gameOfLife.gridString());
        return gameOfLife;
    }

    private static List<Position> pattern(String pattern) {
        switch (pattern) {
            case "glider":
                return glider();
            case "oscillator":
                return oscillator();
            default:
                throw new IllegalArgumentException();
        }
    }

    private static void loopGenerations(GameOfLife gameOfLife) {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            gameOfLife.nextGeneration(gameOfLife.getGrid());
            System.out.println(gameOfLife.gridString());
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    private static List<Position> oscillator() {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(1, 0));
        positions.add(new Position(1, 1));
        positions.add(new Position(1, 2));
        return positions;
    }

    private static List<Position> glider() {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(0,2));
        positions.add(new Position(1, 0));
        positions.add(new Position(1, 2));
        positions.add(new Position(2, 1));
        positions.add(new Position(2, 2));
        return positions;
    }
}