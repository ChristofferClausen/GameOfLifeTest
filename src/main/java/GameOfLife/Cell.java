package GameOfLife;

import static GameOfLife.State.*;

public class Cell {

    private State state;
    private int neighbours; //TODO aliveNeighbours

    public Cell(State state) {
        this.state = state;
    }

    public Cell() {
        this.state = DEAD;
    }

    public State isAlive() {
        return state;
    }

    public void updateState(State state) {
        this.state = state;
    }

    public void addNeighbour() {
        neighbours++;
    }

    public void resetNeighbour() {
        neighbours = 0;
    }

    public int countNeighbours() {
        return neighbours;
    }

}