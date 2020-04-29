package Pieces;
import Play.Chessboard;
import Play.Vector;
import Play.Sides;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Pawn extends Piece {


    public Pawn(Chessboard board, Vector initialPosition, Sides side) throws FileNotFoundException {
        super(board, initialPosition, side);
    }

    @Override
    public boolean canMove(Vector destination) {
        return canGoStraight1(destination) || canGoStraight2(destination) || canCapture(destination);
    }


    public boolean canGoStraight2(Vector destination) {
        // move forward by two grids from starting position
        if (this.side == Sides.BLACK) {
            return this.position.y == 1 && destination.y == 3 && this.position.x == destination.x
                    && !this.board.isOccupied(new Vector(destination.x, 2))
                    && !this.board.isOccupied(new Vector(destination.x, 3));
        } else {
            return this.position.x == destination.x && this.position.y == 6 && destination.y == 4
                    && !this.board.isOccupied(new Vector(destination.x, 5))
                    && !this.board.isOccupied(new Vector(destination.x,4));
        }
    }

    public boolean canGoStraight1(Vector destination) {
        // move forward by one grid
        if (board.isOccupied(destination))
            return false;

        if (this.side == Sides.BLACK) {
            return this.position.x == destination.x && this.position.y + 1 == destination.y;
        } else {
            return this.position.x == destination.x && this.position.y - 1 == destination.y;
        }
    }

    public boolean canCapture(Vector destination) {
        // capture by diagonal
        if (this.side == Sides.BLACK) {
            return Math.abs(this.position.x - destination.x) == 1
                    && this.position.y + 1 == destination.y
                    && board.isOccupied(destination);
        } else {
            return Math.abs(this.position.x - destination.x) == 1
                    && this.position.y - 1 == destination.y
                    && board.isOccupied(destination);
        }
    }

    public boolean canCaptureEnPassant() {
        // TO-DO: bicie w przelocie
        return true;
    }
}
