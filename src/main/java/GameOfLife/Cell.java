package GameOfLife;

public class Cell {

    private boolean alive;
    public final int x;
    public final int y;

    Cell(int x, int y) throws IllegalArgumentException {
        if (x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Cell(int x, int y, boolean alive) {
        if (x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;
            this.alive = alive;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "alive=" + alive +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
