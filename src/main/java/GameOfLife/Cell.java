package GameOfLife;

public class Cell {

    private boolean alive;
    private int neighbours;

    public Cell(boolean alive) {
        this.alive = alive;
    }

    public Cell() {
        this.alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void updateState(boolean alive) {
        this.alive = alive;
    }

    public void addNeighbour() {
        neighbours++;
    }

    public void resetNeighbour() {
        neighbours = 0;
    }

    public int getNeighbours() {
        return neighbours;
    }

}