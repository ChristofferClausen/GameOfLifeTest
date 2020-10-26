package GameOfLife;

public class Cell {

    private boolean alive;

    public Cell(boolean alive) {
        this.alive = alive;
    }

    public Cell() {
        this.alive = false;
    }

    public boolean isAlive() {
        return alive;
    }
}