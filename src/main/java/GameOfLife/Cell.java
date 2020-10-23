package GameOfLife;

public class Cell {

    private boolean alive;
    public int x;
    public int y;

    Cell(int x, int y) throws IllegalArgumentException {
        if (x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Cell(int x, int y, boolean alive) {
        this.x = x;
        this.y = y;
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }
}
