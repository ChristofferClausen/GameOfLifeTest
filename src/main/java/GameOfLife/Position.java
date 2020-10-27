package GameOfLife;

public class Position {

    public final int x;
    public final int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Position))
            return false;
        Position pos = (Position) o;
        return x == pos.x && y == pos.y;
    }
}