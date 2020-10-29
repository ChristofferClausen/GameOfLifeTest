package GameOfLife;

import static GameOfLife.State.*;

public class Cell {

    private State state;
    private int aliveNeighbours;

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

    public void addAliveNeighbour() {
        aliveNeighbours++;
    }

    public void resetAliveNeighbour() {
        aliveNeighbours = 0;
    }

    public int countAliveNeighbours() {
        return aliveNeighbours;
    }

}