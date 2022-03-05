package Pieces;

import GUI.PieceGUI;
import Play.Chessboard;
import Play.Sides;
import Play.Vector;

public abstract class Piece {

    Vector position;
    final Sides side;
    protected Chessboard board;
    private PieceGUI view;

    public Piece(Chessboard board, Vector initialPosition, Sides side) {
        this.position = initialPosition;
        this.side = side;
        this.board = board;
    }

    //Getters
    public Vector getPosition() { return position; }

    public Sides getSide() {
        return side;
    }
    public PieceGUI getView() {
        return this.view;
    }

    public boolean isWhite() { return this.side == Sides.WHITE;}
    public boolean isBlack() { return this.side == Sides.BLACK;}


    //Setters
    public void setPosition(Vector newPosition) {
        this.position = newPosition;
    }

    abstract public boolean canMove(Vector destination);

    public boolean sameSide(Piece other) {
        return this.side.equals(other.getSide());
    }

    public void setView(PieceGUI view) {
        this.view = view;
    }
}
