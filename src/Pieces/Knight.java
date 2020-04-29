package Pieces;
import Play.Chessboard;
import Play.Vector;
import Play.Sides;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Knight extends Piece {

    public Knight(Chessboard board, Vector initialPosition, Sides side) throws FileNotFoundException {
        super(board, initialPosition, side);
    }

    @Override
    public boolean canMove(Vector destination) {
        int[] xMov = new int[]{1,2,2,1,-1,-2,-2,-1};
        int[] yMov = new int[]{2,1,-1,-2,-2,-1,1,2};

        for (int i=0; i<8; i++) {
            if (this.position.x + xMov[i] == destination.x && this.position.y + yMov[i] == destination.y) {
                return true;
            }
        }
        return false;
    }
}
