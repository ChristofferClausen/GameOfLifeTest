package GameOfLife;

public class GameOfLife {

    private final int height;
    private final int width;

    GameOfLife(int height, int width, Cell[] aliveCells) {
        this.height = height;
        this.width = width;
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

}
