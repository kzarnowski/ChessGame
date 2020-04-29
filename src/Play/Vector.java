package Play;

public class Vector {

    public final int x;
    public final int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean equals (Object other){
        if (this == other) return true;
        if (!(other instanceof Vector)) return false;

        Vector otherVector = (Vector)other;
        return (this.x == otherVector.x && this.y == otherVector.y);
    }

    public boolean isOnBoard() {
        return this.x >=0 && this.x <= 7 && this.y >= 0 && this.y <= 7;
    }

    public boolean isDiagonal(Vector other) {
        return Math.abs(this.x - other.x) == Math.abs(this.y - other.y);
    }

    public boolean isStraight(Vector other) {
        return this.x == other.x || this.y == other.y;
    }
}
