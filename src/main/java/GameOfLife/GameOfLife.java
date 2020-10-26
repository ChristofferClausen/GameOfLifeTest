package GameOfLife;

public class GameOfLife {

    private final int height;
    private final int width;
    Cell[][] grid;

    GameOfLife(int height, int width) {
        this.height = height;
        this.width = width;
        initializeDeadGrid();
    }

    private void initializeDeadGrid() {
        grid = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                grid[row][column] = new Cell();
            }
        }
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public void printGrid() {
        for (Cell[] cells : grid) {
            for(Cell cell : cells) {
                if (cell.isAlive())
                    System.out.print("x" + "\t");
                System.out.print("." + "\t");
            }
            System.out.println();
        }
    }
}
