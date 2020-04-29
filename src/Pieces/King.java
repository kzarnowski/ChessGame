package Pieces;
import Play.Chessboard;
import Play.Vector;
import Play.Sides;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class King extends Piece {

    public King (Chessboard board, Vector initialPosition, Sides side) throws FileNotFoundException {
        super(board, initialPosition, side);
    }


    @Override
    public boolean canMove(Vector destination) {
        if (this.position.equals(new Vector(4,0)) || this.position.equals(new Vector(4,7))) {
            if (this.canCastle(destination)) {
                return true;
            }
        }

        return this.position != destination
                && Math.abs(destination.y - this.position.y) <= 1
                && Math.abs(destination.x - this.position.x) <= 1;
    }

    private boolean canCastle(Vector destination) {
        if (this.side == Sides.WHITE) {
            return (destination.equals(new Vector(6,0)) || destination.equals(new Vector(2,0)))
                    && this.board.isStraightFree(this.position, destination);
        } else {
            return (destination.equals(new Vector(6,7)) || destination.equals(new Vector(2,7)))
                    && this.board.isStraightFree(this.position, destination);
        }
    }
}
