package Pieces;
import Play.Chessboard;
import Play.Vector;
import Play.Sides;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Queen extends Piece {

    public Queen(Chessboard board, Vector initialPosition, Sides side) throws FileNotFoundException {
        super(board, initialPosition, side);
    }

    @Override
    public boolean canMove(Vector destination) {
        return (this.position.isDiagonal(destination) && this.board.isDiagonalFree(this.position, destination))
                || (this.position.isStraight(destination) && this.board.isStraightFree(this.position, destination));
    }
}
