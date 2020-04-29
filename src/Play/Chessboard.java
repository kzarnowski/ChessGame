package Play;
import Pieces.*;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Chessboard {

    private Tile[][] squares = new Tile[8][8];
    private LinkedList<Piece> whites = new LinkedList<>();
    private LinkedList<Piece> blacks = new LinkedList<>();
    private BoardVisualizer map = new BoardVisualizer(this);

    public Chessboard() {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                try {
                    squares[i][j] = new Tile(new Vector(i,j), null);
                } catch(Exception e) {
                    System.out.println("Error");
                }

            }
        }

        this.defaultSetup();
    }

    public LinkedList<Piece> getWhites() {
        return this.whites;
    }

    public LinkedList<Piece> getBlacks() {
        return this.blacks;
    }

    public Tile[][] getSquares() {
        return this.squares;
    }

    public Piece getPiece(Vector position) {
        return this.squares[position.x][position.y].getPiece();
    }

    public void removePiece(Piece piece) {
        if (piece != null) {
            Vector pos = piece.getPosition();
            this.squares[pos.x][pos.y].deletePiece();
            if (piece.isWhite()) {
                this.whites.remove(piece);
            } else {
                this.blacks.remove(piece);
            }
        }
    }

    public void putPiece(Piece piece) {
        this.squares[piece.getPosition().x][piece.getPosition().y].setPiece(piece);
        if (piece.isWhite()) {
            this.whites.add(piece);
        } else {
            this.blacks.add(piece);
        }
    }

    public boolean isOccupied(Vector position) {
        return (squares[position.x][position.y].getPiece() != null);
    }

    public void defaultSetup(){

        try {
            //WHITE
            for (int i=0; i<8; i++) {
                this.putPiece(new Pawn(this, new Vector(i,6), Sides.WHITE));
            }
            this.putPiece(new Rook     (this, new Vector(0,7), Sides.WHITE));
            this.putPiece(new Knight   (this, new Vector(1,7), Sides.WHITE));
            this.putPiece(new Bishop   (this, new Vector(2,7), Sides.WHITE));
            this.putPiece(new Queen    (this, new Vector(3,7), Sides.WHITE));
            this.putPiece(new King     (this, new Vector(4,7), Sides.WHITE));
            this.putPiece(new Bishop   (this, new Vector(5,7), Sides.WHITE));
            this.putPiece(new Knight   (this, new Vector(6,7), Sides.WHITE));
            this.putPiece(new Rook     (this, new Vector(7,7), Sides.WHITE));

            //BLACK
            for (int i=0; i<8; i++) {
                this.putPiece(new Pawn(this, new Vector(i,1), Sides.BLACK));
            }
            this.putPiece(new Rook     (this, new Vector(0,0), Sides.BLACK));
            this.putPiece(new Knight   (this, new Vector(1,0), Sides.BLACK));
            this.putPiece(new Bishop   (this, new Vector(2,0), Sides.BLACK));
            this.putPiece(new Queen    (this, new Vector(3,0), Sides.BLACK));
            this.putPiece(new King     (this, new Vector(4,0), Sides.BLACK));
            this.putPiece(new Bishop   (this, new Vector(5,0), Sides.BLACK));
            this.putPiece(new Knight   (this, new Vector(6,0), Sides.BLACK));
            this.putPiece(new Rook     (this, new Vector(7,0), Sides.BLACK));
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }


    public boolean isLegalMove(Vector moveFrom, Vector moveTo) {

        Tile start = this.squares[moveFrom.x][moveFrom.y];
        Tile end = this.squares[moveTo.x][moveTo.y];

        // TO-DO: Pawn capture
        if (!(start.isOccupied()
                && end.getPosition().isOnBoard()
                && start.getPiece().canMove(end.getPosition()))) {
            return false;
        }

        // TO-DO : is capture legal?
        if (!end.isOccupied()) {
            return true;
        } else return !end.getPiece().sameSide(start.getPiece());

    }


    public boolean isDiagonalFree(Vector vec1, Vector vec2) {
        if (!vec1.isDiagonal(vec2)) {
            throw new IllegalArgumentException("Not on diagonal: " + vec1.toString() + ", " + vec2.toString());
        }

        if ((vec2.x - vec1.x) * (vec2.y - vec1.y) > 0) {
            int x1 = Math.min(vec1.x, vec2.x);
            int y1 = Math.min(vec1.y, vec2.y);
            int x2 = Math.max(vec1.x, vec2.x);
            int y2 = Math.max(vec1.y, vec2.y);

            for (int i = 1; i < x2 - x1; i++) {
                if (squares[x1+i][y1+i].isOccupied()) {
                    return false;
                }
            }
            return true;
        } else {
            int x1 = Math.min(vec1.x, vec2.x);
            int y1 = Math.max(vec1.y, vec2.y);
            int x2 = Math.max(vec1.x, vec2.x);
            int y2 = Math.min(vec1.y, vec2.y);

            for (int i = 1; i < x2 - x1; i++) {
                if (squares[x1+i][y1-i].isOccupied()) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean isStraightFree(Vector vec1, Vector vec2) {
        if (!vec1.isStraight(vec2)) {
            throw new IllegalArgumentException("Not on straight: " + vec1.toString() + ", " + vec2.toString());
        }

        if (vec1.x == vec2.x) {
            int y1 = Math.min(vec1.y, vec2.y);
            int y2 = Math.max(vec1.y, vec2.y);
            for (int i = y1 + 1; i < y2; i++) {
                if (squares[vec1.x][i].isOccupied()) {
                    return false;
                }
            }
            return true;
        } else {
            int x1 = Math.min(vec1.x, vec2.x);
            int x2 = Math.max(vec1.x, vec2.x);
            for (int i = x1 + 1; i < x2; i++) {
                if (squares[i][vec1.y].isOccupied()) {
                    return false;
                }
            }
            return true;
        }
    }


    @Override
    public String toString() {
        return map.draw();
    }
}
