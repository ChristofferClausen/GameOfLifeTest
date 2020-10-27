package GameOfLife;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife(10,10);
        List<Position> positions;
        positions = new ArrayList<>();
        positions.add(new Position(0,0));
        positions.add(new Position(4, 4));
        positions.add(new Position(4, 5));
        positions.add(new Position(4, 6));
        positions.add(new Position(4, 1));
        positions.add(new Position(8, 8));

        gameOfLife.initializeFirstGeneration(positions);
        gameOfLife.nextGeneration();
    }
}
