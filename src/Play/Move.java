package Play;
import Pieces.*;

public class Move {

    private Chessboard board;
    private Vector moveFrom;
    private Vector moveTo;
    private Piece piece;
    private Piece captured;

    public Move(Chessboard board, Vector moveFrom, Vector moveTo) {
        this.board = board;
        this.moveFrom = moveFrom;
        this.moveTo = moveTo;
        this.piece = board.getPiece(moveFrom);
        this.captured = null;
    }

    public Piece getCaptured() {
        return this.captured;
    }

    public boolean isCapture() {
        return this.captured != null;
    }

    public void make() {
        if (moveFrom.equals(moveTo))
            return;

        if (!board.isLegalMove(moveFrom, moveTo)) {
            throw new IllegalArgumentException();
        }

        if (board.isOccupied(moveTo)) {
            // capture case
            this.captured = board.getPiece(moveTo);
            this.board.removePiece(board.getPiece(moveTo));
        }

        this.board.removePiece(piece);
        this.piece.setPosition(moveTo);
        this.board.putPiece(piece);



       // this.end.setPiece(this.start.getPiece());
      //  this.start.deletePiece();
    }

}

